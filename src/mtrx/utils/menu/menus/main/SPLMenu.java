package mtrx.utils.menu.menus.main;

import mtrx.utils.menu.XMenu;
import mtrx.utils.menu.menus.sub.GaussMenu;

public class SPLMenu extends XMenu {

    public SPLMenu(){
        this.run();
    }

    @Override
    protected void run() {
        this.inisiasiMatrix();
        this.display();
        int metode = select(1,4);
        switch(metode){
            case 1:
                new GaussMenu(this.matrixMenu);
                break;
            case 2:
                new GaussMenu(this.matrixMenu, this);
                break;
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
