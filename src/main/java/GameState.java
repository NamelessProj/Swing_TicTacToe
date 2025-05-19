public class GameState {
    private final int size;
    private final char[][] board;
    private int turn;
    private char currentPlayer;
    private boolean gameOver = false;

    /**
     * Constructor to initialize the game state
     * @param size The size of the board (number of rows/columns)
     */
    GameState(int size) {
        this.size = size;
        this.turn = 0;
        this.currentPlayer = 'X';
        this.board = new char[size][size];
    }

    /**
     * Method to check if a move can be made
     * @param input The input number representing the cell
     * @return true if the move is valid, false otherwise
     */
    public boolean makeMove(int input) {
        int row = (input - 1) / size;
        int col = (input - 1) % size;

        // Check if the move is valid
        if (row < 0 || row >= size || col < 0 || col >= size || board[row][col] != '\0')
            return false; // Invalid move

        board[row][col] = currentPlayer;
        turn++;

        return true; // Move made successfully
    }

    /**
     * Method to change the current player
     */
    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
    }

    /**
     * Method to check if the game is over
     * @return true if the game is over, false otherwise
     */
    public boolean isDraw() {
        boolean isDraw = turn == size * size;
        if (isDraw)
            gameOver = true; // Set game over if it's a draw
        return isDraw;
    }

    /**
     * Method to check if the current player has won
     * @return true if the current player has won, false otherwise
     */
    public boolean checkWinner() {
        // Check rows and columns
        for (int i = 0; i < size; i++) {
            if (checkRow(i) || checkColumn(i)) {
                gameOver = true;
                return true;
            }
        }

        // Check if the game is over
        boolean won = checkDiagonal();

        if (won)
            gameOver = true;

        return won;
    }

    /**
     * Method to check if a row has the same player
     * @param row The row index
     * @return true if the row has the same player, false otherwise
     */
    private boolean checkRow(int row) {
        for (int j = 0; j < size; j++) {
            if (board[row][j] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if a column has the same player
     * @param col The column index
     * @return true if the column has the same player, false otherwise
     */
    private boolean checkColumn(int col) {
        for (int i = 0; i < size; i++) {
            if (board[i][col] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if a diagonal has the same player
     * @return true if a diagonal has the same player, false otherwise
     */
    private boolean checkDiagonal() {
        boolean leftDiagonal = true;
        boolean rightDiagonal = true;

        for (int i = 0; i < size; i++) {
            if (board[i][i] != currentPlayer) {
                leftDiagonal = false;
            }
            if (board[i][size - 1 - i] != currentPlayer) {
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

    /**
     * Method to get if the game is over
     * @return true if the game is over, false otherwise
     */
    public boolean getGameOver() {
        return gameOver;
    }

    /**
     * Method to get the size of the board
     * @return The size of the board
     */
    public int getSize() {
        return size;
    }
}