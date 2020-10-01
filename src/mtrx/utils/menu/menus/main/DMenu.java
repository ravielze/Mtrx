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
        int metode = select(1,2);
        switch(metode){
            case 1:
                new DRBMenu(this.matrixMenu);
                break;
            case 2:
                new DEKMenu(this.matrixMenu);
                break;
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
    }
    
}
