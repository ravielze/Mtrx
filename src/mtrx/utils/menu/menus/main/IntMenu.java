package mtrx.utils.menu.menus.main;

import mtrx.MtrxMain;
import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.methods.GaussJordan;
import mtrx.methods.SolutionExaminer;
import mtrx.utils.NUtils;
import mtrx.utils.menu.XMenu;

public class IntMenu extends XMenu {

    public IntMenu(){
        this.run();
    }

    @Override
    protected void run() {
        this.interpolasi();
        this.display();
        int s = select(1,2);
        switch (s){
            case 2:
                this.toFile();
            default:
                GaussJordan gj = new GaussJordan(this.matrixMenu);
                SolutionExaminer se = new SolutionExaminer(gj);
                this.statusprint("Interpolasi", true);
                se.showSolution();
                switch (se.getResult()){
                    case SINGLE:
                        Matrix constant = new MatrixBuilder(gj.getResult().getRight()).cutColoumn(1).build();
                        System.out.print("Masukkan x untuk di interpolasikan ke F(x): ");
                        double n = MtrxMain.scn.nextDouble();
                        Matrix x = (new MatrixBuilder()).buildXPow(n, constant.getRowCount());
                        print("F(%s) = %s", NUtils.TOSTRING(n), NUtils.TOSTRING(x.multiply(constant).determinant()));
                        break;
                    case MULTI:
                        print("Tidak bisa melakukan interpolasi pada persamaan yang memiliki solusi banyak.");
                        break;
                    case NO_SOLUTION:
                        print("Tidak bisa melakukan interpolasi pada persamaan yang tidak memiliki solusi.");
                        break;
                }
                print();
                break;
        }
        this.toConsole();
    }

    private void interpolasi(){
        this.inisiasiMatrix();
        try {
            this.matrixMenu = new MatrixBuilder(this.matrixMenu).buildAsInterpolation();
        } catch (Exception ex) {
            if (ex.getMessage().contains("size cannot")){
                print("Terjadi kesalahan. Silakan input ulang matrix.");
                this.interpolasi();
            }
        }
    }

    @Override
    protected void display() {
        print();
        print("Interpolasi");
        print("> Lihat solusi di?");
        print("[1] Console");
        print("[2] File");
    }
    
}
