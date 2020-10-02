package mtrx.utils.menu.menus.main;

import mtrx.utils.menu.XMenu;
import mtrx.utils.menu.menus.sub.MRMenu;

public class MainMenu extends XMenu{

    public static boolean end = false;

    @Override
    public void run() {
        this.display();
        int choice = select(1, 6);
        switch (choice) {
            case 1:
                new SPLMenu();
                break;
            case 2:
                new DMenu();
                break;
            case 3:
                new InversMenu();
                break;
            case 4:
                new IntMenu();
                break;
            case 5:
                new MRMenu();
                break;
            default:
                end = true;
                break;
        }
    }

    @Override
    protected void display() {
        System.out.println("Main Menu");
        System.out.println("[1] Sistem Persamaan Linear");
        System.out.println("[2] Determinan");
        System.out.println("[3] Inverse");
        System.out.println("[4] Interpolasi Polinom");
        System.out.println("[5] Regresi Linier Berganda");
        System.out.println("[6] Keluar");
    }
    
}
