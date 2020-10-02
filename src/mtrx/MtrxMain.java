package mtrx;

import java.util.Scanner;

import mtrx.utils.menu.menus.main.*;

public class MtrxMain {

    public static final int PRECISION = 3;
    public static Scanner scn;

    public static void main(String args[]){

        scn = new Scanner(System.in);
        MainMenu mm = new MainMenu();
        while (!MainMenu.end){
            mm.run();
        }
        scn.close();
    }
}
