package mtrx.utils.menu.menus.sub;

import mtrx.matrix.Matrix;
import mtrx.utils.menu.XMenu;

public class AdjMenu extends XMenu {

    public AdjMenu(Matrix matrix) {
        this.matrixMenu = matrix;
        this.run();
    }

    @Override
    protected void run() {
        Matrix adj = this.matrixMenu.inverse();
        this.display();
        int select = select(1,2);
        switch (select){
            case 2:
                this.toFile();
            default:
                this.statusprint("Invers dengan Adjoin");
                if (adj == null){
                    print("Matriks ini tidak memiliki invers.");
                } else {
                    adj.show(false);
                }
                print();
                break;
        }
        this.toConsole();
    }

    @Override
    protected void display() {
        print();
        print("Invers - Adjoin");
        print("> Lihat solusi di?");
        print("[1] Console");
        print("[2] File");
    }
    
}
