package mtrx.utils.menu;

import mtrx.MtrxMain;
import mtrx.matrix.Matrix;

public abstract class XMenu {

    /**
     * Matrix pada menu ini, nilainya null jika tidak ada
     */
    protected Matrix matrixMenu;

    public XMenu(){
        this.run();
    }

    /**
     * Untuk menjalankan menu ini.
     */
    abstract protected void run();
    
    /**
     * Untuk memperlihatkan pilihan yang ada
     */
    abstract protected void display();

    protected int select(int min, int max){
        int result = MtrxMain.scn.nextInt();
        return (result > max || result < min) ? -1 : result;
    }

    protected void print(String s){
        System.out.println(s);
    }
    
}
