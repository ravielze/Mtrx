package mtrx.utils.menu.menus.main;

import mtrx.MtrxMain;
import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.utils.menu.XMenu;

public class InputMenu extends XMenu {

    public InputMenu(){
        this.run();
    }

    @Override
    protected void run() {
        this.display();
        int sel = select(1, 2);
        switch(sel){
            case 1:
                this.matrixMenu = createMatrixfromConsole();
                break;
            case 2:
                this.matrixMenu = createMatrixfromFile();
                break;
            default:
                break;
        }
    }

    private Matrix createMatrixfromConsole(){
        return (new MatrixBuilder()).consoleInput().build();
    }

    private Matrix createMatrixfromFile(){
        System.out.println();
        System.out.print("Masukkan nama file: ");
        String filename = MtrxMain.scn.next();
        if (!filename.endsWith(".txt"))filename = filename+".txt";
        Matrix created = (new MatrixBuilder()).fileInput(filename).build();
        return created;
    }

    public Matrix getMatrix(){
        return this.matrixMenu;
    }

    @Override
    protected void display() {
        print("Input Matrix");
        print("[1] Console Input");
        print("[2] File Input");
    }
    
}
