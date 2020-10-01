package mtrx;

import java.util.Scanner;

import mtrx.utils.menu.menus.main.SPLMenu;

public class MtrxMain {

    public static Scanner scn;

    public static void main(String args[]){
        scn = new Scanner(System.in);
        new SPLMenu();
        scn.close();
    }
}
