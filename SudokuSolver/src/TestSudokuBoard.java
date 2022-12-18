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

    @Test
    void testLegal() {
        board.set(0, 0, 1);
        assertEquals(false, board.legal(1, 2, 2), "Legal gave wrong value for illegal inputs");
        assertEquals(true, board.legal(1, 3, 3), "Legal gave wrong value for legal inputs");
    }

    @Test
    void testGetMatrix() {
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

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                assertEquals(figure1[row][col], board.getMatrix()[row][col], "Wrong return for getMatrix");
            }
        }
    }

    @Test
    void testClear() {
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
        board.clear();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                assertEquals(0, board.getMatrix()[row][col], "Wrong return for getMatrix");
            }
        }
    }

    @Test
    void testRemove() {
        board.set(1, 1, 1);
        board.remove(1, 1);
        assertEquals(0, board.getMatrix()[1][1], "Return working improperly");
    }

    @Test
    void bugInLegal() {
        board.set(3, 3, 4);
        assertEquals(false, board.legal(4, 5, 5), "Wrong return from legal");
    }
}
