package mtrx.utils.menu.menus.sub;

import mtrx.matrix.Matrix;
import mtrx.methods.Gauss;
import mtrx.methods.SolutionExaminer;
import mtrx.utils.menu.XMenu;

public class GaussMenu extends XMenu{

    public GaussMenu(Matrix matrix){
        this.matrixMenu = matrix;
        this.run();
    }

    @Override
    protected void run() {
        Gauss g = new Gauss(this.matrixMenu);
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
        print("Sistem Persamaan Linear - Gauss");
        print("> Lihat solusi di?");
        print("[1] Console");
        print("[2] File");
    }
    
}
