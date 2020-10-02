package mtrx.utils.menu.menus.main;

import mtrx.utils.menu.XMenu;
import mtrx.utils.menu.menus.sub.DEKMenu;
import mtrx.utils.menu.menus.sub.DRBMenu;

public class DMenu extends XMenu{

    public DMenu(){
        this.run();
    }

    @Override
    protected void run() {
        this.inisiasiMatrix();
        this.display();
        int metode = select(1,3);
        switch(metode){
            case 1:
                new DRBMenu(this.matrixMenu);
                break;
            case 2:
                new DEKMenu(this.matrixMenu);
                break;
            case 3:
                MainMenu.end = true;
            default:
                break;
        }
    }

    @Override
    protected void display() {
        print();
        print("Metode Determinan");
        print("[1] Metode Reduksi Baris");
        print("[2] Metode Ekpansi Kofaktor");
        print("[3] Keluar");
    }
    
}
