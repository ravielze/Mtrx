package mtrx.methods;

import mtrx.augmatrix.AugMatrix;
import mtrx.trait.MatrixTrait;
import mtrx.utils.Base26;
import mtrx.utils.NUtils;

public class SolutionExaminer {

    private Solutions result = Solutions.NO_SOLUTION;
    private AugMatrix aug;

    public SolutionExaminer(Gauss g){
        if (g.hasSolution()){
            this.aug = g.getResult();
            this.result = Solutions.SINGLE;
            if (this.checkMultiSolution()) this.result = Solutions.MULTI;
            if (this.checkNoSolution()) this.result = Solutions.NO_SOLUTION;
        }
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
                    System.out.printf("X%d = %.3f\n", i, values[i]);
                }
                break;
            case MULTI:
                boolean[] colHasPivot = new boolean[this.aug.getLeft().getColCount()];
                int lastPivotRow = -1;
                for (int i = 0; i < values.length; i++){
                    values[i] = 0;
                    colHasPivot[i] = false;
                    for (int p = this.aug.getRowCount()-1; p >= 0; p--){
                        if (NUtils.ISEQUAL(this.aug.getLeft().getElement(p, i), 0.0D)) continue;
                        if (NUtils.ISEQUAL(this.aug.getLeft().getElement(p, i), 1.0D) && p > lastPivotRow){
                            lastPivotRow = p;
                            colHasPivot[i] = true;
                        }
                    }
                }

                while (true){
                    row--;
                    if (row < 0) break;
                    col = this.aug.getLeft().findFirstXinRow(row, 1.0D);
                    if (col == -1) continue;
                    if (col+1 == this.aug.getColCount() && colHasPivot[col]){
                        values[col] = this.aug.getRight().getElement(row, 0);
                        continue;
                    }
                    if (colHasPivot[col]){
                        values[col] = this.aug.getRight().getElement(row, 0);
                        for (int i=col+1; i < this.aug.getLeft().getColCount(); i++){
                            if (colHasPivot[i])
                                values[col] -= this.aug.getLeft().getElement(row, i)*values[i];
                        }
                    }
                }

                row = this.aug.getRowCount();
                while (true){
                    row--;
                    if (row < 0) break;
                    col = this.aug.getLeft().findFirstXinRow(row, 1.0D);
                    if (col == -1) continue;

                    System.out.printf("X%d = %.3f", col, values[col]);
                    for (int i = col; i < this.aug.getLeft().getColCount(); i++){
                        double nowSubElement = this.aug.getLeft().getElement(row, i)*-1.0D;
                        if (!colHasPivot[i]){
                            System.out.printf(" %s%.3f%s", nowSubElement > 0.0D ? "+" : "", nowSubElement, Base26.toBase26(i));
                        }
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
 * x1 = –3r – 2s – 2t 
x2 = r
x3 = –s
x4 = s
x5 = t
x6 = 1/3 
 */