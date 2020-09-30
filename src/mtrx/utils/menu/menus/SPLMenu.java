package mtrx.utils.menu.menus;

import mtrx.utils.menu.XMenu;

public class SPLMenu extends XMenu {

    @Override
    protected void run() {
        InputMenu im = new InputMenu();
        this.matrixMenu = im.getMatrix();
        this.display();
        int metode = select(1,4);
        switch(metode){
            case 1:
                new GaussMenu(this.matrixMenu);
            default:
                break;
        }
    }

    @Override
    protected void display() {
        print("Metode Sistem Persamaan Linear");
        print("[1] Metode eliminasi Gauss");
        print("[2] Metode eliminasi Gauss-Jordan");
        print("[3] Metode matriks balikan");
        print("[4] Kaidah Cramer");

    }
    
}
