package mtrx.utils.menu.menus.sub;

import mtrx.matrix.Matrix;
import mtrx.methods.GaussJordan;
import mtrx.methods.SolutionExaminer;
import mtrx.utils.menu.XMenu;

public class GJMenu extends XMenu {

    public GJMenu(Matrix matrix) {
        this.matrixMenu = matrix;
        this.run();
    }

    @Override
    protected void run() {
        GaussJordan g = new GaussJordan(this.matrixMenu);
        SolutionExaminer se = new SolutionExaminer(g);
        int select = select(1,2);
        this.display();
        switch (select){
            case 2:
                this.toFile();
            default:
                g.getResult().show(false);
                se.showSolution();
                break;
        }
        this.toConsole();
    }

    @Override
    protected void display() {
        print("Matriks balikan - Gauss-Jordan");
        print("> Lihat solusi di?");
        print("[1] Console");
        print("[2] File");
    }
    
}
