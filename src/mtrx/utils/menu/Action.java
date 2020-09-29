package mtrx.utils.menu;

import java.util.Optional;

import mtrx.matrix.Matrix;

public interface Action {

    Optional<Matrix> run(Matrix matrix, Menu menu);
}
