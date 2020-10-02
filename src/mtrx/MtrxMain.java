package mtrx;

import java.util.Scanner;

import mtrx.utils.menu.menus.main.*;

public class MtrxMain {
    /**
     * Presisi yang akan digunakan oleh program.
     * Mohon diperbesar angkanya dengan mengganti integer dibawah ini
     * apabila menggunakan angka-angka yang besar.
     * Saran: terapkan angka penting.
     * Defaultnya 0.001 atau 3
     */
    public static final int PRECISION = 5;

    /**
     * tidak usah diubah, scanner IO untuk seluruh program.
     */
    public static Scanner scn;

    /**
     * Where the program starts and we have fun with it.
     * @param args is not used. Abaikan ini.
     */
    public static void main(String args[]){

        scn = new Scanner(System.in);
        MainMenu mm = new MainMenu();
        while (!MainMenu.end){
            mm.run();
            if (!MainMenu.end) promptEnterKey();
        }
        scn.close();
    }

    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}  
     }
}
