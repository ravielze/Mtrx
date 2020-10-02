package mtrx.utils.menu;

import java.io.File;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import mtrx.MtrxMain;
import mtrx.matrix.Matrix;
import mtrx.utils.menu.menus.main.InputMenu;

public abstract class XMenu {

    private static PrintStream console;

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

    protected void statusprint(String namametode){
        statusprint(namametode, false);
    }

    protected void statusprint(String namametode, boolean SPLformat){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String dt = LocalDateTime.now().format(formatter);
        print("> " + namametode + " [" + dt + "]");
        print((SPLformat ? "Sistem Persamaan" : "Matriks Awal"));
        this.matrixMenu.show(SPLformat);
        print();
        print("Hasil");
    }

    protected void print(){
        System.out.println();
    }

    protected void print(String s){
        System.out.println(s);
    }

    protected void print(String format, Object... args){
        System.out.printf(format, args);
    }

    protected void toFile(){
        console = System.out;
        try {
            File folder = new File(new File("../output").getCanonicalPath());
            if (!folder.exists()){
                folder.mkdir();
            }

            File file;
            while (true){
                System.out.print("Masukkan nama file: ");
                String filename = MtrxMain.scn.next();

                if (!filename.endsWith(".txt")) filename = filename + ".txt";

                file = new File(new File("../output/"+filename).getCanonicalPath());

                if (!file.exists())break;

                System.out.println("File tersebut sudah ada, silakan input nama file yang lain.");
            }
            file.createNewFile();
            PrintStream f = new PrintStream(file);
            System.setOut(f);
        } catch (Exception ex){
            print("Terjadi kesalahan saat ingin menyimpan ke dalam file.");
            ex.printStackTrace();
        }
    }

    protected void toConsole(){
        System.setOut(console);
    }

    protected void inisiasiMatrix(){
        if (this instanceof InputMenu) return;

        InputMenu im = new InputMenu();
        this.matrixMenu = im.getMatrix();
    }
    
}
