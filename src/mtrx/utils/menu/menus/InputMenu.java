package mtrx.utils.menu.menus;

import mtrx.MtrxMain;
import mtrx.matrix.Matrix;
import mtrx.matrix.MatrixBuilder;
import mtrx.utils.menu.XMenu;

public class InputMenu extends XMenu {

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
        }
    }

    private Matrix createMatrixfromConsole(){
        return (new MatrixBuilder()).consoleInput(true).build();
        //return (new MatrixBuilder()).fileInput("test2.txt").build();
    }

    private Matrix createMatrixfromFile(){
        System.out.println("Masukkan nama file: ");
        String filename = MtrxMain.scn.nextLine();
        Matrix created = (new MatrixBuilder()).fileInput(filename).build();
        return created;
    }

    public Matrix getMatrix(){
        return this.matrixMenu;
    }

    @Override
    protected void display() {
        print("[1] Console Input");
        print("[2] File Input");
    }
    
}
