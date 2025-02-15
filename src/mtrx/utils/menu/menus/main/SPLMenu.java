package mtrx.utils.menu.menus.main;

import mtrx.utils.menu.XMenu;
import mtrx.utils.menu.menus.sub.CramMenu;
import mtrx.utils.menu.menus.sub.GaussMenu;
import mtrx.utils.menu.menus.sub.MBMenu;

public class SPLMenu extends XMenu {

    public SPLMenu(){
        this.run();
    }

    @Override
    protected void run() {
        this.inisiasiMatrix();
        this.display();
        int metode = select(1,5);
        switch(metode){
            case 1:
                new GaussMenu(this.matrixMenu);
                break;
            case 2:
                new GaussMenu(this.matrixMenu, this);
                break;
            case 3:
                new MBMenu(this.matrixMenu);
                break;
            case 4:
                new CramMenu(this.matrixMenu);
                break;
            case 5:
                MainMenu.end = true;
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
        print("[5] Keluar");

    }
    
}
