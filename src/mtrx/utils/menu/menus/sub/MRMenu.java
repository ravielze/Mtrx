package mtrx.utils.menu.menus.sub;

import mtrx.MtrxMain;
import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.methods.GaussJordan;
import mtrx.methods.SolutionExaminer;
import mtrx.methods.Solutions;
import mtrx.utils.NUtils;
import mtrx.utils.menu.XMenu;

public class MRMenu extends XMenu{

    public MRMenu(){
        this.run();
    }

    @Override
    protected void run() {
        this.inisiasiMatrix();
        this.display();
        this.matrixMenu = (new MatrixBuilder(this.matrixMenu)).buildAsMR();
        int s = select(1,2);
        switch (s){
            case 2:
                this.toFile();
            default:
                GaussJordan gj = new GaussJordan(this.matrixMenu);
                SolutionExaminer se = new SolutionExaminer(gj);
                this.statusprint("Regresi Linear Berganda", true);
                se.showSolution();
                if (se.getResult() == Solutions.SINGLE){
                    Matrix constant = new MatrixBuilder(gj.getResult().getRight()).cutColoumn(1).build();
                    double[][] arrx = new double[1][constant.getRowCount()];
                    for (int i=0; i < constant.getRowCount(); i++){
                        if (i != 0){
                            System.out.print("Masukkan X" + i + ": ");
                            arrx[0][i] = MtrxMain.scn.nextDouble();
                        } else {
                            arrx[0][0] = 1.0D;
                        }
                    }
                    Matrix x = new Matrix(1, constant.getRowCount(), arrx);
                    print();
                    print("Taksiran/Estimasi Nilai:");
                    print("%s", NUtils.TOSTRING(x.multiply(constant).determinant()));
                } else if (se.getResult() == Solutions.MULTI) {
                    print("Program belum bisa menaksir/mengestimasi suatu nilai dari SPL yang memiliki solusi ganda.");
                } else {
                    print("Sistem Persamaan Linearnya tidak memiliki solusi.");
                }
                print();
                break;
        }
        this.toConsole();
    }

    @Override
    protected void display() {
        print();
        print("Sistem Persamaan Linear - Matriks Balikan");
        print("> Lihat solusi di?");
        print("[1] Console");
        print("[2] File");
    }
    
}
