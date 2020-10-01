package mtrx.utils.menu.menus.sub;

import mtrx.matrix.Matrix;
import mtrx.methods.Gauss;
import mtrx.methods.GaussJordan;
import mtrx.methods.GaussMethod;
import mtrx.methods.InverseGaussJordan;
import mtrx.methods.SolutionExaminer;
import mtrx.utils.menu.XMenu;
import mtrx.utils.menu.menus.main.InversMenu;
import mtrx.utils.menu.menus.main.SPLMenu;

public class GaussMenu extends XMenu{

    private XMenu parentMenu;

    /**
     * Untuk gauss saja.
     * parentMenu = null.
     * @param matrix
     */
    public GaussMenu(Matrix matrix){
        this.matrixMenu = matrix;
        this.run();
    }

    /**
     * Untuk gausss jordan saja.
     * parentMenu antara InversMenu atau SPLMenu.
     * @param matrix
     * @param parentMenu
     */
    public GaussMenu(Matrix matrix, XMenu parentMenu){
        this.matrixMenu = matrix;
        this.parentMenu = parentMenu;
        this.run();
    }

    @Override
    protected void run() {
        this.display();
        int select = select(1,2);

        GaussMethod g;
        if (this.parentMenu == null){
            g = new Gauss(this.matrixMenu);
        } else if (this.parentMenu instanceof SPLMenu){
            g = new GaussJordan(this.matrixMenu);
        } else if (this.parentMenu instanceof InversMenu){
            g = new InverseGaussJordan(this.matrixMenu);
        } else {
            return;
        }

        switch (select){
            case 2:
                this.toFile();
            default:
                if (this.parentMenu == null || this.parentMenu instanceof SPLMenu){
                    SolutionExaminer se = new SolutionExaminer(g);
                    this.statusprint("SPL dengan Gauss" + ((this.parentMenu == null) ? "" : " Jordan"), true);
                    se.showSolution();
                } else if (g instanceof InverseGaussJordan){
                    this.statusprint("Invers dengan Gauss Jordan");
                    InverseGaussJordan i = (InverseGaussJordan) g;
                    if (i.hasSolution()){
                        print("Matriks ini tidak memiliki invers.");
                    } else {
                        i.getInvers().show(false);
                    }
                    print();
                    print("Augmented Matriks:");
                    i.getResult().show();
                }
                print();
                break;
        }
        this.toConsole();
    }

    @Override
    protected void display() {
        if (this.parentMenu == null){
            print("Sistem Persamaan Linear - Gauss");
        } else if (this.parentMenu instanceof InversMenu){
            print("Invers - Gauss Jordan");
        } else if (this.parentMenu instanceof SPLMenu){
            print("Sistem Persamaan Linear - Gauss Jordan");
        }
        print("> Lihat solusi di?");
        print("[1] Console");
        print("[2] File");
    }
    
}
