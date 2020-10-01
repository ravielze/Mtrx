package mtrx.utils.menu.menus.main;

import mtrx.utils.menu.XMenu;
import mtrx.utils.menu.menus.sub.AdjMenu;
import mtrx.utils.menu.menus.sub.GaussMenu;

public class InversMenu extends XMenu {

    public InversMenu() {
        this.run();
    }

    @Override
    protected void run() {
        this.inisiasiMatrix();
        this.display();
        int choice = select(1, 2);
        switch (choice) {
            case 1:
                new GaussMenu(this.matrixMenu, this);
                break;
            case 2:
                new AdjMenu(this.matrixMenu);
                break;
            default:
                break;
        }
    }

    @Override
    protected void display() {
        print("Matriks balikan");
        print("[1] Metode eliminasi Gauss Jordan");
        print("[2] Adjoin");
    }
    
}
