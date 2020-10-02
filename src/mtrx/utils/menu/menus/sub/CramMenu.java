package mtrx.utils.menu.menus.sub;

import mtrx.matrix.Matrix;
import mtrx.methods.Crammer;
import mtrx.methods.SolutionExaminer;
import mtrx.utils.menu.XMenu;

public class CramMenu extends XMenu {

    public CramMenu(Matrix matrix) {
        this.matrixMenu = matrix;
        this.run();
    }

    @Override
    protected void run() {
        this.display();
        int sel = select(1,2);
        switch(sel) {
            case 2:
            this.toFile();
            default:
                this.statusprint("SPL dengan Crammer");
                Crammer cm = new Crammer(this.matrixMenu);
                if (!cm.hasSolution()){
                    print("Matriks ini tidak memiliki solusi atau tidak bisa memakai Crammer. Silakan coba pakai cara lain. Rekomendasi: Gauss/GaussJordan");
                } else {
                    SolutionExaminer se = new SolutionExaminer(cm);
                    se.showSolution();
                }
                print();
                break;
        }
        this.toConsole();
    }

    @Override
    protected void display() {
        print();
        print("Determinan - Reduksi Baris");
        print("> Lihat solusi di?");
        print("[1] Console");
        print("[2] File");
    }
    
}
