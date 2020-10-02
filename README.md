# mtrx
Tugas Besar Algeo I | 2020

`Tugas Besar Algeo I` is a matrice calculator which calculates Linear Equation System, Determinant, Inverse, Polynomial Interpolation, and Multiple Linear Regression. Linear Equation System can be calculated by using Gaussian Elimination, Gauss-Jordan Elimination, Cramer's Rule, and Inverse, while Determinant can be calculated by using Line Reduction and Cofactor Expansion. Lastly, Inverse can be calculated by using Gauss-Jordan Elimination and Adjoint. These calculations may round up to `n` decimal places.

# Project Structure
```sh
mtrx/
├── bin/                                                  # Contains java byte code (.class)
├── output/                                               # Contains output of each calculations (.txt)
├── src/                                                  # Contains source code (.java)
      ├── augmatrix/                                      # Contains Augmented Matrice forms
              ├── AugMatrix                               # Augmented Matrice Builder and useful functions for Augmented Matrice
              ├── IAugMatrix                              # Interface of AugMatrix
      ├── matrix/                                         # Contains Matrice forms
              ├── IMatrix                                 # Interface of Matrix
              ├── Matrix                                  # Useful functions for Matrice
              ├── MatrixBuilder                           # Matrice Builder
              ├── MatrixOperation                         # Interface of MatrixOperation
      ├── methods/                                        # Contains operations of every calculations
              ├── Crammer                                 # Operates Cramer's Rule
              ├── Gauss                                   # Operates Gaussian Elimination
              ├── GaussJordan                             # Operates Gauss-Jordan Elimination
              ├── GaussMethod                             # Interface of Gauss, GaussJordan, and InverseGaussJordan
              ├── InverseGaussJordan                      # Operates Inverse using Gauss-Jordan Elimination
              ├── InverseSPL                              # Operates Inverse using Adjoint
              ├── SolutionExaminer                        # Gives output of Linear Equation System after using one of those calculations
              ├── Solutions                               # Linear Equation System types, including single solution, multiple solution, and no solution
              ├── SPLMethods                              # Interface of Crammer and InverseSPL
      ├── trait/                                          # Contains MatrixTrait
              ├── MatrixTrait                             # Matrice types, including square matrice (N x N), zero matrice (0 for all elements), and identity matrice
      ├── utils/                                          # Contains menus, Base26, and NUtils
              ├── menu/                                   # Contains menus and the interface
                    ├── menus/                            # Contains menus
                           ├── main/                      # Contains main menus
                                  ├── DMenu               # Main menu for Determinant
                                  ├── InputMenu           # Menu for input (choose between input from console or file)
                                  ├── IntMenu             # Menu for Polynomial Interpolation
                                  ├── InversMenu          # Main menu for Inverse
                                  ├── MainMenu            # Main menu
                                  ├── SPLMenu             # Main menu for Linear Equation System
                           ├── sub/                       # Contains sub menus
                                  ├── AdjMenu             # Sub menu of InversMenu (Adjoint)
                                  ├── CramMenu            # Sub menu of Linear Equation System (Cramer's Rule)
                                  ├── DEKMenu             # Sub menu of Determinant (Cofactor Expansion)
                                  ├── DRBMenu             # Sub menu of Determinant (Line Reduction)
                                  ├── GaussMenu           # Sub menu of Linear Equation System (Gaussian Elimination, Gauss-Jordan Elimination, and Inverse)
                    ├── XMenu                             # Interface and functions for menus
             ├── Base26                                   # To change integer to string
             ├── NUtils                                   # Functions for better precision
     ├── MtrxMain                                         # To start main menu
├── test/                                                 # Contains all test cases given
```

# How to run?

## Run Manually

1. Goto Folder src using this command : `cd src`
2. Compile all files using this command: `javac -d ../bin ./mtrx/*.java`
3. Goto Folder bin using this command: `cd bin`
4. Run the main program using this command `java mtrx.MtrxMain`

## Run Using Windows Batch File

Windows:
1. Clone this project
2. Go inside the directory on the terminal (cd ../mtrx)
2. Type menu.bat or ./menu.bat on the terminal

Linux/MacOS: There's currently no shell script for Linux/MacOs, please run manually.

# Programmer's Note

Before calculating very big/small number, change decimal precision in the main menu (section 6).