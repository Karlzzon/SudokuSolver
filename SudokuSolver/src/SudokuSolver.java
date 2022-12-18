public interface SudokuSolver {

    /**
     * Tries to solve the current sudoku board using a recursive backtracking
     * method.
     * 
     * @return Returns true if the current board could be solved and false if the
     *         board is unsolvable.
     */
    boolean solve();

    /**
     * Checks to see if its legal to put digit in the designated row and
     * column
     * 
     * @param digit The digit to be placed
     * @param row   The row
     * @param col   The Column
     * @return Returns true if the placement is legal and false if digit is already
     *         in row,column or in the same 3x3 box.
     */
    boolean legal(int digit, int row, int col);

    /**
     * Puts digit in the box row, col.
     * 
     * @param row   The row
     * @param col   The column
     * @param digit The digit to insert in box row, col
     * @throws IllegalArgumentException if row, col or digit is outside the range
     *                                  [0..9]
     */
    void set(int row, int col, int digit);

    /**
     * Removes the digit in the box row, col.
     * 
     * @param row   The row
     * @param col   The column
     * @param digit The digit to insert in box row, col
     * @throws IllegalArgumentException if row, col or digit is outside the range
     *                                  [0..9]
     */
    void remove(int row, int col);

    /**
     * Empties the grid.
     */
    void clear();

    /**
     * Fills the grid with the digits in m. The digit 0 represents an empty box.
     * 
     * @param m the matrix with the digits to insert
     * @throws IllegalArgumentException if m has the wrong dimension or contains
     *                                  values outside the range [0..9]
     */
    void setMatrix(int[][] matrix);

    /**
     * Returns a matrix with the current values.
     * 
     * @return integer matrix with current values
     */
    int[][] getMatrix();
}
