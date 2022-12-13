import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    SudokuBoard board;
    JTextField[][] grid;
    JFrame frame;

    /**
     * Creates a user interface for SudokuSolver
     * 
     * @param board the SudokuBoard that the UI is created for
     */
    public UI(SudokuBoard board) {
        this.board = board;
        this.grid = new JTextField[9][9];
        SwingUtilities.invokeLater(() -> createWindow("SudokuGame", 800, 800));
    }

    private void createWindow(String title, int width, int height) {
        // creating main window
        this.frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        frame.setPreferredSize(new Dimension(width, height));

        // creating buttons
        JPanel buttonPanel = new JPanel();
        JButton solve = new JButton("Solve");
        JButton clear = new JButton("Clear");
        buttonPanel.add(solve);
        buttonPanel.add(clear);
        pane.add(buttonPanel, BorderLayout.SOUTH);
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

        // creating game grid
        JPanel topPanel = new JPanel(new GridLayout(9, 9));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Font font = new Font("Monospaced", Font.BOLD, 80);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField temp = new JTextField();
                temp.setBorder(BorderFactory.createLineBorder(Color.black, 3));
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

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        updateBoard();

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
        private SudokuBoard board;
        private JTextField text;

        private SetAction(int r, int c, JTextField text, SudokuBoard board) {
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
                        if (col < 8) {
                            grid[row][col + 1].grabFocus();
                        } else {
                            grid[row + 1][0].grabFocus();
                        }
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
