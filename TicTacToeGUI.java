import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI {
    private JFrame frame;
    private JButton[][] buttons;
    private char currentPlayer;
    private boolean gameWon;

    public TicTacToeGUI() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        currentPlayer = 'X';
        gameWon = false;

        initializeButtons();

        frame.setVisible(true);
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                frame.add(buttons[row][col]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("") && !gameWon) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                gameWon = checkGameWon(row, col);
                if (gameWon) {
                    JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(frame, "The game is a tie!");
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    private boolean checkGameWon(int row, int col) {
        return (buttons[row][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[row][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[row][2].getText().equals(String.valueOf(currentPlayer))) ||
               (buttons[0][col].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][col].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][col].getText().equals(String.valueOf(currentPlayer))) ||
               (row == col &&
                buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) ||
               (row + col == 2 &&
                buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer)));
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
