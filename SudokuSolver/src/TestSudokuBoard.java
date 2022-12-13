import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSudokuBoard {
    private SudokuBoard board;

    @BeforeEach
    void setUp() {
        board = new SudokuBoard();
    }

    @AfterEach
    void tearDown() {
        board = null;
    }

    @Test
    void testEmpty() {
        assertEquals(true, board.solve(), "Solve was not true for empty board");
    }

    @Test
    void testFigure1() {
        int[][] figure1 = {
                { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
                { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
                { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
                { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
                { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
                { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 4, 0, 0 }
        };
        board.setMatrix(figure1);
        assertEquals(true, board.solve(), "Solve was not true for figure 1");
    }

    @Test
    void testUnsolvable() {
        int[][] figure1 = {
                { 0, 9, 8, 0, 0, 9, 0, 6, 2 }, // 2 nior i samma rad
                { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
                { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
                { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
                { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
                { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
                { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 4, 0, 0 }
        };
        board.setMatrix(figure1);
        assertEquals(false, board.solve(), "Solve was not false for unsolvable board, same row");

        int[][] figure2 = {
                { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
                { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
                { 0, 5, 0, 0, 0, 0, 6, 0, 5 }, // 2 femmor i samma kolumn
                { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
                { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
                { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 4, 0, 0 }
        };
        board.setMatrix(figure2);
        assertEquals(false, board.solve(), "Solve was not false for unsolvable board, same column");

        int[][] figure3 = {
                { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
                { 0, 8, 0, 0, 0, 0, 0, 0, 5 }, // 2 åttor samma region
                { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
                { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
                { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
                { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
                { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 4, 0, 0 }
        };
        board.setMatrix(figure3);
        assertEquals(false, board.solve(), "Solve was not false for unsolvable board, same box");
    }
}
