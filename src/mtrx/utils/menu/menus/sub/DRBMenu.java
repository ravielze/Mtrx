package mtrx.utils.menu.menus.sub;

import mtrx.matrix.Matrix;
import mtrx.utils.NUtils;
import mtrx.utils.menu.XMenu;

public class DRBMenu extends XMenu{

    public DRBMenu(Matrix matrix){
        this.matrixMenu = matrix;
        this.run();
    }

    @Override
    protected void run() {
        this.display();
        int s = select(1,2);
        switch (s){
            case 2:
                this.toFile();
            default:
                this.statusprint("Determinan dengan Reduksi Baris");
                double sol = this.matrixMenu.determinant();
                if (Double.isNaN(sol)){
                    print("Matriks ini tidak memiliki determinan.");
                } else {
                    print("%s", NUtils.TOSTRING(sol));
                }
                print();
                break;
        }
        this.toConsole();
    }

    @Override
    protected void display() {
        print();
        print("Determinan - Reduksi Baris");
        print("> Lihat solusi di?");
        print("[1] Console");
        print("[2] File");
    }
    
}
