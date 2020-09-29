package mtrx.utils.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import mtrx.matrix.Matrix;

public class Menu {

    private String menuTitle;
    private static Menu mainMenu;
    private static List<Menu> allMenus = new ArrayList<>();
    private List<Action> actions = new ArrayList<>(3);
    private List<String> displays = new ArrayList<>(5);

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

    public void addActions(Action... actions){
        for (Action a : actions){
            if (a != null){
                this.actions.add(a);
            }
        }
    }

    public void addDisplay(String... displays){
        for (String d : displays){
            if (d.trim().length() != 0){
                this.displays.add(d);
            }
        }
    }

    public void display(){
        //TODO
    }

    public Optional<Matrix> runAction(int selection, Matrix matrix, Menu menu){
        if (selection > this.actions.size() && selection < 0){
            this.display();
            return Optional.empty();
        }
        return (this.actions.size() != 0) ? 
            this.actions.get(selection).run(matrix, menu)
                :
            Optional.empty();
    }    
}
