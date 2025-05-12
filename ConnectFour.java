import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final char EMPTY = ' ';
    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';

    public static void main(String[] args) {
        char[][] board = new char[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = EMPTY;
            }
        }

        boolean playerOneTurn = true;
        boolean gameWon = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameWon) {
            printBoard(board);
            int col;
            if (playerOneTurn) {
                System.out.print("Player 1 (X), choose a column (0-6): ");
            } else {
                System.out.print("Player 2 (O), choose a column (0-6): ");
            }

            while (true) {
                col = scanner.nextInt();
                if (col >= 0 && col < COLS && board[0][col] == EMPTY) {
                    break;
                } else {
                    System.out.print("Invalid column. Try again: ");
                }
            }

            for (int row = ROWS - 1; row >= 0; row--) {
                if (board[row][col] == EMPTY) {
                    board[row][col] = playerOneTurn ? PLAYER_ONE : PLAYER_TWO;
                    gameWon = checkWin(board, row, col);
                    break;
                }
            }

            if (gameWon) {
                printBoard(board);
                if (playerOneTurn) {
                    System.out.println("Player 1 (X) wins!");
                } else {
                    System.out.println("Player 2 (O) wins!");
                }
            }

            playerOneTurn = !playerOneTurn;
        }

        scanner.close();
    }

    private static void printBoard(char[][] board) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print("| " + board[row][col] + " ");
            }
            System.out.println("|");
        }
        for (int col = 0; col < COLS; col++) {
            System.out.print("----");
        }
        System.out.println("-");
    }

    private static boolean checkWin(char[][] board, int row, int col) {
        char player = board[row][col];
        return checkDirection(board, row, col, 1, 0, player) ||  // Horizontal
               checkDirection(board, row, col, 0, 1, player) ||  // Vertical
               checkDirection(board, row, col, 1, 1, player) ||  // Diagonal /
               checkDirection(board, row, col, 1, -1, player);   // Diagonal \
    }

    private static boolean checkDirection(char[][] board, int row, int col, int rowDir, int colDir, char player) {
        int count = 0;
        for (int i = -3; i <= 3; i++) {
            int r = row + i * rowDir;
            int c = col + i * colDir;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLS && board[r][c] == player) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }
}
