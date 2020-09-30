package mtrx.utils.menu;

import java.io.File;
import java.io.PrintStream;

import mtrx.MtrxMain;
import mtrx.matrix.Matrix;

public abstract class XMenu {

    /**
     * Matrix pada menu ini, nilainya null jika tidak ada
     */
    protected Matrix matrixMenu;

    /**
     * Untuk menjalankan menu ini.
     */
    abstract protected void run();
    
    /**
     * Untuk memperlihatkan pilihan yang ada
     */
    abstract protected void display();

    protected int select(int min, int max){
        System.out.print("> ");
        int result = MtrxMain.scn.nextInt();
        while (result > max || result < min){
            print("Pilihan tidak ada. Silakan input ulang!");
            System.out.print("> ");
            result = MtrxMain.scn.nextInt();
        }
        return result;
    }

    protected void print(String s){
        System.out.println(s);
    }

    protected void toFile(){
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
    }

    protected void toConsole(){
        System.setOut(System.out);
    }

    protected String inputNamaFile(){
        System.out.print("Masukkan nama file: ");
        String filename = MtrxMain.scn.next();
        return filename;
    }
    
}
