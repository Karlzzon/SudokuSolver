import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    SudokuSolver board;
    JTextField[][] grid;
    JFrame frame;
    Container pane;
    JPanel buttonPanel;
    JButton solve;
    JButton clear;
    JPanel topPanel;
    Font font;

    /**
     * Creates user interface for the SudokuBoard class
     * 
     * @param board The sudokuBoard with grid and all its methods
     */
    public UI(SudokuSolver board) {
        this.board = board;
        this.grid = new JTextField[9][9];
        SwingUtilities.invokeLater(() -> createWindow("SudokuGame", 700, 700));
    }

    private void createWindow(String title, int width, int height) {
        this.frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane = frame.getContentPane();
        frame.setPreferredSize(new Dimension(width, height));

        createButtonPanel();

        solve.addActionListener(e -> {
            if (board.solve()) {
                updateBoard();
            } else {
                JOptionPane.showMessageDialog(frame, "Unsolvable Board");
            }
        });
        clear.addActionListener(e -> {
            board.clear();
            updateBoard();
        });

        setBorderAndColor();
        createGameGrid();

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        updateBoard();
    }

    private void createGameGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField temp = new JTextField();
                temp.setBorder(BorderFactory.createLineBorder(Color.black, 3));
                temp.setPreferredSize(new Dimension(100, 100));
                temp.setHorizontalAlignment(JTextField.CENTER);
                temp.setFont(font);
                SetAction setAction = new SetAction(row, col, temp, board);
                temp.addActionListener(setAction);

                temp.setInputVerifier(new InputVerifier() {
                    public boolean verify(JComponent input) {
                        setAction.actionPerformed(null);
                        return true;
                    }
                });

                if (((col < 3 || col > 5) && (row < 3 || row > 5)) || col > 2 && col < 6 && row > 2 && row < 6) {
                    temp.setBackground(new Color(91, 166, 209));
                } else {
                    temp.setBackground(new Color(181, 197, 209));
                }
                grid[row][col] = temp;
                topPanel.add(temp);
            }
        }
        pane.add(topPanel);
    }

    private void setBorderAndColor() {
        topPanel = new JPanel(new GridLayout(9, 9));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        font = new Font("Monospaced", Font.BOLD, 80);
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel();
        solve = new JButton("Solve");
        clear = new JButton("Clear");
        buttonPanel.add(solve);
        buttonPanel.add(clear);
        pane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateBoard() {
        int[][] temp = board.getMatrix();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (temp[row][col] == 0) {
                    grid[row][col].setText("");
                } else {
                    grid[row][col].setText(Integer.toString(temp[row][col]));
                }
            }
        }
    }

    private class SetAction implements ActionListener {
        private int row, col;
        private SudokuSolver board;
        private JTextField text;

        private SetAction(int r, int c, JTextField text, SudokuSolver board) {
            row = r;
            col = c;
            this.text = text;
            this.board = board;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String inp = text.getText();
            try {
                if (!inp.equals("")) {
                    if (Integer.parseInt(inp) == 0) {
                        throw new IllegalArgumentException();
                    } else {
                        board.set(row, col, Integer.parseInt(inp));
                    }
                } else {
                    board.set(row, col, 0);
                }
            } catch (IllegalArgumentException x) {
                text.setText("");
                board.set(row, col, 0);
                JOptionPane.showMessageDialog(frame, "Invalid Inputs");
            }
        }
    }
}