package mtrx.utils.menu.menus;

import java.io.File;
import java.io.PrintStream;

import mtrx.MtrxMain;
import mtrx.matrix.Matrix;
import mtrx.methods.Gauss;
import mtrx.methods.SolutionExaminer;
import mtrx.utils.menu.XMenu;

public class GaussMenu extends XMenu{

    public GaussMenu(Matrix matrix){
        this.matrixMenu = matrix;
        this.postRun();
    }

    private void postRun(){
        Gauss g = new Gauss(this.matrixMenu);
        SolutionExaminer se = new SolutionExaminer(g);
        int select = select(1,2);
        switch (select){
            case 1:
                try {
                    File file = new File(new File("../output/"+inputNamaFile()).getCanonicalPath());
                    if (!file.exists()){
                        file.createNewFile();
                    }
                    PrintStream f = new PrintStream(file);
                    System.setOut(f);
                } catch (Exception ex){
                    print("Terjadi kesalahan saat ingin menyimpan ke dalam file.");
                    ex.printStackTrace();
                }
            default:
                g.getResult().show(false);
                se.showSolution();
                break;
        }
        System.setOut(System.out);
    }

    @Override
    protected void run() {
        this.display();
    }

    private String inputNamaFile(){
        System.out.print("Masukkan nama file: ");
        String filename = MtrxMain.scn.nextLine();
        return filename;
    }

    @Override
    protected void display() {
        print("Sistem Persamaan Linear - Gauss");
        print("> Lihat solusi di?");
        print("[1] Console");
        print("[2] File");
    }
    
}
