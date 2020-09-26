package mtrx.utils;

public class Menu {
    public static void mainMenu() {
        System.out.println("MENU");
        System.out.println("[1] Sistem Persamaan Linear");
        System.out.println("[2] Determinan");
        System.out.println("[3] Matriks Balikan");
        System.out.println("[4] Interpolasi Polinom");
        System.out.println("[5] Regresi Linier Berganda");
        System.out.println("[6] Keluar");
    }

    public static void menuSPL() {
        System.out.println("METODE");
        System.out.println("[1] Metode eliminasi Gauss");
        System.out.println("[2] Metode eliminasi Gauss-Jordan");
        System.out.println("[3] Metode matriks balikan");
        System.out.println("[4] Kaidah Cramer");
    }

    public static void menuDeterminan() {
        System.out.println("METODE");
        System.out.println("[1] Reduksi baris");
        System.out.println("[2] Ekspansi kofaktor");
    }
}