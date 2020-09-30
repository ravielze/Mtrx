package mtrx.methods;

import mtrx.augmatrix.AugMatrix;
import mtrx.matrix.Matrix;
import mtrx.trait.MatrixTrait;
import mtrx.utils.Base26;
import mtrx.utils.NUtils;

public class SolutionExaminer {

    private Solutions result = Solutions.NO_SOLUTION;
    private AugMatrix aug;
    private Matrix originalMatrix;
    private boolean notGaussJordan;

    public SolutionExaminer(GaussMethod g){
        if (g.hasSolution()){
            this.aug = g.getResult();
            this.originalMatrix = g.getInitialMatrix();
            this.result = Solutions.SINGLE;
            if (this.checkMultiSolution()) this.result = Solutions.MULTI;
            if (this.checkNoSolution()) this.result = Solutions.NO_SOLUTION;
        }

        this.notGaussJordan = (g instanceof Gauss) ? true : false;
    }

    private boolean checkNoSolution(){
        boolean noSolution = false;
        for (int i = 0; i < this.aug.getRowCount(); i ++){
            noSolution = noSolution || ((!this.aug.isAllXinRow(i, 0.0D)) && (this.aug.getLeft().isAllXinRow(i, 0.0D)));
            if (noSolution) break;
        }
        return noSolution;
    }

    private boolean checkMultiSolution(){
        if (this.aug.getLeft().hasTrait(MatrixTrait.ZERO)) return true;
        if (this.aug.getRowCount() < this.aug.getLeft().getColCount()) return true;

        boolean result = false;
        for (int i = 0; i < this.aug.getRowCount(); i++){
            result = result || (this.aug.isAllXinRow(i, 0.0D) && this.aug.getLeft().isAllXinRow(i, 0.0D));
            if (result) break;
        }
        return result;
    }

    public Solutions getResult(){
        return this.result;
    }

    public void showSolution(){
        double[] values = new double[this.aug.getLeft().getColCount()];
        int col;
        int row = this.aug.getRowCount();
        switch (this.result){
            case SINGLE:
                for (int i = 0; i < values.length; i++){
                    values[i] = 0;
                }

                while (true){
                    row--;
                    if (row < 0) break;
                    col = this.aug.getLeft().findFirstXinRow(row, 1.0D);
                    if (col == -1) continue;
                    if (col+1 == this.aug.getColCount()){
                        values[col] = this.aug.getRight().getElement(row, 0);
                        continue;
                    }
                    values[col] = this.aug.getRight().getElement(row, 0);
                    for (int i=col+1; i < this.aug.getLeft().getColCount(); i++){
                        values[col] -= this.aug.getLeft().getElement(row, i)*values[i];
                    }
                }

                for (int i = 0; i < values.length; i++){
                    System.out.printf("%s = %.3f\n", Base26.toBase26(i), values[i]);
                }
                break;
            case MULTI:
                if (this.notGaussJordan){
                    this.aug = new GaussJordan(this.originalMatrix).getResult();
                }
                int[] fill = new int[this.aug.getColCount()-1];
                int idx = 0;
                for (int j = 0; j < this.aug.getColCount()-2; j++) {
                    int i = 0;
                    while (i < this.aug.getRowCount()) {
                        if (j < this.aug.getColCount()-2) {
                            if (NUtils.ISEQUAL(this.aug.getLeft().getElement(0, j), 1)) {
                                for (int l = 0; l < this.aug.getRowCount(); l++) { 
                                    if (NUtils.ISNOTEQUAL(this.aug.getLeft().getElement(l, j), 0) && l != i) {
                                        idx++;
                                        fill[j] = idx;
                                        break;
                                    }
                                }
                                break;
                            }
                            else if (NUtils.ISEQUAL(this.aug.getLeft().getElement(0, j), 0)) i++;
                            else {
                                idx++;
                                fill[j] = idx;
                                break;
                            }
                        }
                        else {
                            if (NUtils.ISEQUAL(this.aug.getRight().getElement(0, 0), 0)) i++;
                            else {
                                idx++;
                                fill[j] = idx;
                                break;
                            }
                        }
                    }
                }
                
                for (int j = 0; j < this.aug.getColCount()-2; j++) {
                    int i = (j < this.aug.getColCount()-2 ? this.aug.getLeft().findFirstXinCol(j, 1) : 
                    this.aug.getRight().findFirstXinCol(0, 1));

                    if (NUtils.ISEQUAL(fill[j], 0)) {
                        System.out.printf("X%d = ", j+1);
                        boolean found = false;
                        for (int k = j+1; k < this.aug.getColCount()-1; k++) {
                            if (k != this.aug.getColCount()-2) {
                                if (NUtils.ISNOTEQUAL(this.aug.getLeft().getElement(i, k), 0)) {
                                    if (found && this.aug.getLeft().getElement(i, k) < 0) System.out.printf(" + ");
                                    System.out.printf("%.3f", this.aug.getLeft().getElement(i, k)*-1);
                                    if (NUtils.ISNOTEQUAL(fill[k], 0)) System.out.printf("a" + fill[k] + " ");
                                    found = true;
                                }
                            }
                            else {
                                if (NUtils.ISNOTEQUAL(this.aug.getRight().getElement(i, 0), 0)) {
                                    if (found) System.out.printf("+ ");
                                    System.out.printf("%.3f", this.aug.getRight().getElement(i, 0));
                                    if (NUtils.ISNOTEQUAL(fill[k], 0)) System.out.printf("a" + fill[k]);
                                }
                            }
                        }
                    }
                    else {
                        System.out.printf("X%d = ", j+1);
                        System.out.printf("a" + fill[j]);
                    }
                    System.out.printf("\n");
                }
                break;
            default:
                System.out.println("Tidak ada solusi untuk SPL ini.");
                break;
        }
    }
}
/**
 * x1 = –3r – 4s – 2t
 * x2 = r
 * x3 = –2s
 * x4 = s
 * x5 = t
 * x6 = 1/3 
 */