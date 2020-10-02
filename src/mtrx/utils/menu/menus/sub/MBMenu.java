package mtrx.utils.menu.menus.sub;

import mtrx.matrix.Matrix;
import mtrx.methods.InverseGaussJordan;
import mtrx.methods.InverseSPL;
import mtrx.methods.SolutionExaminer;
import mtrx.utils.menu.XMenu;

public class MBMenu extends XMenu {

    public MBMenu(Matrix matrix){
        this.matrixMenu = matrix;
        this.run();
    }

    @Override
    protected void run() {
        this.display();
        int s = select(1,2);
        switch (s){
            case 2:
                this.toFile();
            default:
                this.statusprint("Sistem Persamaan Linear dengan Metode Matriks Balikan");
                InverseGaussJordan i = new InverseGaussJordan(this.matrixMenu);
                if (i.hasSolution()){
                    print("Matriks ini tidak memiliki invers.\nTidak bisa melanjutkan metode matriks balikan.");
                } else {
                    print("Matriks A Invers:");
                    Matrix A = i.getInvers();
                    A.show(false);
                    print();
                    print("Silakan masukkan matrix B");
                    this.inisiasiMatrix();
                    Matrix B = this.matrixMenu;
                    if (A.getColCount() != B.getRowCount()){
                        print("Matrix B tidak valid.");
                    } else {
                        print("Hasil: ");
                        InverseSPL ispl = new InverseSPL(A, B);
                        SolutionExaminer se = new SolutionExaminer(ispl);
                        se.showSolution();
                    }
                }
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
