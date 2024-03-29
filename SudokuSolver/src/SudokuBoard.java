/**
 * Implementation of a Sudoku gameboard, aswell as a solve method able to solve
 * all solveable Sudoku variations.
 * 
 * @author Oskar Karlsson
 * @author Lucas Eriksson
 */
public class SudokuBoard implements SudokuSolver {
    private int[][] board;

    /**
     * Constructs an empty SudokuBoard
     */
    public SudokuBoard() {
        board = new int[9][9];
    }

    @Override
    public void set(int row, int col, int digit) {
        if (board[row][col] == digit) {
            return;
        }
        if (row >= 0 && row < 9 && col >= 0 && col < 9 && digit < 10 && digit >= 0) {
            board[row][col] = digit;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void remove(int row, int col) {
        if (row > 8 || col > 8 || row < 0 || col < 0) {
            throw new IllegalArgumentException();
        }
        board[row][col] = 0;

    }

    @Override
    public void clear() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }

    }

    @Override
    public void setMatrix(int[][] matrix) {
        for (int a = 0; a < 9; a++) {
            if (matrix.length != 9 || matrix[a].length != 9) {
                throw new IllegalArgumentException("Wrong size of matrix");
            } else {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (matrix[i][j] > 9 || matrix[i][j] < 0) {
                            throw new IllegalArgumentException("Matrix Contains invalid inputs");
                        }
                    }
                }
                board = matrix;
            }
        }

    }

    @Override
    public int[][] getMatrix() {
        int temp[][] = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                temp[i][j] = board[i][j];
            }
        }
        return temp;
    }

    @Override
    public boolean legal(int digit, int row, int col) {
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i] == digit) {
                return false;
            }
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == digit) {
                return false;
            }
        }

        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == digit) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean solve() {
        return recSolve(0, 0);
    }

    private boolean recSolve(int row, int col) {
        if (col > 8) {
            row++;
            col = 0;
        }
        if (row > 8) {
            return true;
        }
        if (board[row][col] != 0) {
            int digit = board[row][col];
            set(row, col, 0);
            if (legal(digit, row, col)) {
                set(row, col, digit);
                return recSolve(row, col + 1);
            }
            set(row, col, digit);
            return false;
        }
        for (int i = 1; i <= 9; i++) {
            if (legal(i, row, col)) {
                set(row, col, i);
                if (recSolve(row, col + 1)) {
                    return true;
                } else {
                    set(row, col, 0);
                }
            }
        }
        return false;
    }
}
