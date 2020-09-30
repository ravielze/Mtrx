package mtrx.utils;

import java.util.Scanner;

import mtrx.MtrxMain;

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
        System.out.println("[5] Kembali");
    }

    public static void menuDeterminan() {
        System.out.println("METODE");
        System.out.println("[1] Reduksi baris");
        System.out.println("[2] Ekspansi kofaktor");
        System.out.println("[3] Kembali");
    }

    public static void runMenu() {
        Scanner keyboard = MtrxMain.scn;

        while (true) {
            mainMenu();
            int buttonVal = keyboard.nextInt();
            
            if (buttonVal == 1) {
                menuSPL();
                int buttonSPL = keyboard.nextInt();

                if (buttonSPL == 1) {
                    // Gauss
                    System.out.println("Processing Gauss...");
                }
                else if (buttonSPL == 2) {
                    // Gauss Jordan
                    System.out.println("Processing Gauss-Jordan...");
                }
                else if (buttonSPL == 3) {
                    // Metode Matriks balikan
                    System.out.println("Processing Matriks balikan...");
                }
                else if (buttonSPL == 4) {
                    // Cramer
                    System.out.println("Processing Cramer...");
                }
            }
            else if (buttonVal == 2){
                menuDeterminan();
                int buttonDet = keyboard.nextInt();

                if (buttonDet == 1) {
                    // Reduksi baris
                    System.out.println("Processing Reduksi baris...");
                }
                else if (buttonDet == 2) {
                    // Ekspansi kofaktor
                    System.out.println("Processing ekspansi kofaktor...");
                }
            }
            else if (buttonVal == 3) {
                // Matriks Balikan
                System.out.println("Processing matriks balikan...");
            }
            else if (buttonVal == 4) {
                // Interpolasi Polinom
                System.out.println("Processing interpolasi polinom...");
            }
            else if (buttonVal == 5) {
                // Regresi linier berganda
                System.out.println("Processing regresi linier berganda...");
            }
            else if (buttonVal == 6) {
                // Exit
                System.out.println("*****END OF PROCESS*****");
                break;
            }
            else {
                System.out.println("Input invalid. Silakan masukkan range angka sesuai menu.");
            }
        }
    }
}