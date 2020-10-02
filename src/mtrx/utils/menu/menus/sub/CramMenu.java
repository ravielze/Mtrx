package mtrx.utils.menu.menus.sub;

import mtrx.matrix.Matrix;
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
                break;
            default:
                break;
        }

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
