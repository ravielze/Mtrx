package mtrx.utils.menu.menus;

import mtrx.utils.menu.XMenu;

public class DMenu extends XMenu{

    public DMenu(){
        this.run();
    }

    @Override
    protected void run() {
        InputMenu im = new InputMenu();
        this.matrixMenu = im.getMatrix();
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
        print("Metode Determinan");
        print("[1] Metode Reduksi Baris");
        print("[2] Metode Ekpansi Kofaktor");
    }
    
}
