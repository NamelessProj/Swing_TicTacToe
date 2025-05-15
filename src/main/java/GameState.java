public class GameState {
    private int size;
    private int turn;
    private char currentPlayer;
    private char[][] board;

    /**
     * Constructor to initialize the game state
     * @param size The size of the board (number of rows/columns)
     */
    GameState(int size) {
        this.size = size;
        this.turn = 0;
        this.currentPlayer = 'X';
        this.board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Method to check if a move can be made
     * @param input The input number representing the cell
     * @return true if the move is valid, false otherwise
     */
    public boolean makeMove(int input) {
        int row = (input - 1) / size;
        int col = (input - 1) % size;

        if (row < 0 || row >= size || col < 0 || col >= size || board[row][col] != ' ')
            return false; // Invalid move

        board[row][col] = currentPlayer;
        turn++;

        if (turn % 2 == 0) {
            currentPlayer = 'X';
        } else
            currentPlayer = 'O';

        return true; // Move made successfully
    }

    /**
     * Method to check if the game is over
     * @return true if the game is over, false otherwise
     */
    public boolean isDraw() {
        return turn == size * size;
    }

    /**
     * Method to check if the current player has won
     * @param player The current player ('X' or 'O')
     * @return true if the current player has won, false otherwise
     */
    public boolean checkWinner(char player) {
        // Check rows and columns
        for (int i = 0; i < size; i++) {
            if (checkRow(i, player) || checkColumn(i, player)) {
                return true;
            }
        }

        // Check diagonals
        return checkDiagonal(player);
    }

    /**
     * Method to check if a row has the same player
     * @param row The row index
     * @param player The current player ('X' or 'O')
     * @return true if the row has the same player, false otherwise
     */
    private boolean checkRow(int row, char player) {
        for (int j = 0; j < size; j++) {
            if (board[row][j] != player) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if a column has the same player
     * @param col The column index
     * @param player The current player ('X' or 'O')
     * @return true if the column has the same player, false otherwise
     */
    private boolean checkColumn(int col, char player) {
        for (int i = 0; i < size; i++) {
            if (board[i][col] != player) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if a diagonal has the same player
     * @param player The current player ('X' or 'O')
     * @return true if a diagonal has the same player, false otherwise
     */
    private boolean checkDiagonal(char player) {
        boolean leftDiagonal = true;
        boolean rightDiagonal = true;

        for (int i = 0; i < size; i++) {
            if (board[i][i] != player) {
                leftDiagonal = false;
            }
            if (board[i][size - 1 - i] != player) {
                rightDiagonal = false;
            }
        }

        return leftDiagonal || rightDiagonal;
    }

    /**
     * Method to get the current player
     * @return The current player ('X' or 'O')
     */
    public char getCurrentPlayer() {
        return currentPlayer;
    }
}