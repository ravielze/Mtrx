package mtrx.utils.menu.menus.main;

import mtrx.utils.menu.XMenu;

public class InversMenu extends XMenu {

    public InversMenu() {
        this.run();
    }

    @Override
    protected void run() {
        InputMenu im = new InputMenu();
        this.matrixMenu = im.getMatrix();
        this.display();
        int choice = select(1, 2);
        switch (choice) {
            case 1:

                break;
            default:

                break;
        }
    }

    @Override
    protected void display() {
        print("Matriks balikan");
        print("[1] Metode eliminasi Gauss-Jordan");
        print("[2] Adjoin");
    }
    
}
