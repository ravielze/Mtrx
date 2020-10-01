package mtrx.utils.menu.menus.sub;

import mtrx.matrix.Matrix;
import mtrx.utils.menu.XMenu;

public class AdjMenu extends XMenu {

    public AdjMenu(Matrix matrix) {
        this.matrixMenu = matrix;
        this.run();
    }

    @Override
    protected void run() {
        Matrix adj = this.matrixMenu.inverse();
        int select = select(1,2);
        this.display();
        switch (select){
            case 2:
                this.toFile();
            default:
                adj.show(false);
                break;
        }
        this.toConsole();
    }

    @Override
    protected void display() {
        print("Matriks balikan - Adjoin");
        print("> Lihat solusi di?");
        print("[1] Console");
        print("[2] File");
    }
    
}
