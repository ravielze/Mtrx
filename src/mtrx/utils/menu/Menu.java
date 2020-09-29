package mtrx.utils.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import mtrx.matrix.Matrix;

public class Menu {

    private String menuTitle;
    private static Menu mainMenu;
    private static List<Menu> allMenus = new ArrayList<>();
    private List<Action> actions = new ArrayList<>(3);
    private List<String> displays = new ArrayList<>(5);

    static {
        mainMenu = (new Menu("Main Menu"))
        .addActions(new Action(){

            @Override
            public Optional<Matrix> run(Matrix matrix, Menu menu) {
                // TODO Auto-generated method stub
                return null;
            }

        })
        .addDisplay("[1] Sistem Persamaan Linear", "[2] Determinan", "[3] Matriks Balikan", "[4] Interpolasi Polinom", "[5] Regresi Linear Berganda", "[6] Keluar");

        Menu SPL = (new Menu("Sistem Persamaan Linear")).addActions(new Action(){

			@Override
			public Optional<Matrix> run(Matrix matrix, Menu menu) {
				// TODO Auto-generated method stub
				return null;
			}

        })
        .addDisplay("[1] Metode Gauss", "[2] Metode Gauss-Jordan", "[3] Metode Matriks Balikan", "[4] Kaidah Crammer", "[5] Kembali");
    }

    public Menu(String title){
        this.menuTitle = title;
        if (mainMenu == null){
            mainMenu = this;
        }
        allMenus.add(this);
    }

    public void destroy(){
        for (Menu m : allMenus){
            if (m == this){
                allMenus.remove(m);
                break;
            }
        }
    }

    public String getTitle(){
        return this.menuTitle;
    }

    public static Menu getMainMenu(){
        return mainMenu;
    }

    public static List<Menu> getAllMenus(){
        return Collections.unmodifiableList(allMenus);
    }

    public Menu addActions(Action... actions){
        for (Action a : actions){
            if (a != null){
                this.actions.add(a);
            }
        }
        return this;
    }

    public Menu addDisplay(String... displays){
        for (String d : displays){
            if (d.trim().length() != 0){
                this.displays.add(d);
            }
        }
        return this;
    }

    private void display(){
        System.out.println(this.menuTitle);
        for (String display : this.displays){
            System.out.println(display);
        }
    }

    public Optional<Matrix> runMenu(Matrix matrix, Menu menu){
        this.display();
        Scanner s = new Scanner(System.in);
        try {
            int select = s.nextInt();
            s.close();
            if (select > this.actions.size() && select < 0){
                System.out.println("Pilihan menu tidak valid.");
                return this.runMenu(matrix, menu);
            } else {
                return this.runAction(select, matrix, menu);
            }
        } catch (Exception ex) {
            System.out.println("Terjadi error. Ada kesalahan masukkan? Silakan input ulang!");
            return this.runMenu(matrix, menu);
        }
    }

    private Optional<Matrix> runAction(int selection, Matrix matrix, Menu menu){
        if (selection > this.actions.size() && selection < 0){
            return Optional.empty();
        }
        return (this.actions.size() != 0) ? 
            this.actions.get(selection).run(matrix, menu)
                :
            Optional.empty();
    }    
}
