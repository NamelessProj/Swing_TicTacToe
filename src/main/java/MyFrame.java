import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    /**
     * Constructor for MyFrame class.
     * Initializes the game frame with a specified game size.
     *
     * @param gameSize The size of the Tic Tac Toe game (number of rows/columns).
     */
    MyFrame(int gameSize) {
        // Creating a new GameState object with the specified game size
        GameState gameState = new GameState(gameSize);

        // Setting the default close operation and title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TicTacToe");
        this.setSize(500, 500);
        this.setLayout(new BorderLayout());

        // Setting the icon of the frame
        ImageIcon icon = new ImageIcon("src/main/resources/icon.jpg");
        this.setIconImage(icon.getImage());

        // Setting the label saying which player turn it is
        JLabel label = new JLabel("Player X turn");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(Color.WHITE);

        // Creating all panels
        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelCenter = new JPanel();

        // Default dimensions for the panels
        Dimension dim = new Dimension(100, 100);

        // Setting the preferred size of the panels
        panelNorth.setPreferredSize(dim);
        panelSouth.setPreferredSize(new Dimension(100, 70));
        panelEast.setPreferredSize(dim);
        panelWest.setPreferredSize(dim);
        panelCenter.setPreferredSize(dim);

        // Setting the panel that will put the label in the center
        JPanel northWestPanel = new JPanel();
        northWestPanel.setPreferredSize(dim);

        // Adding the label to the north panel
        panelNorth.setLayout(new BorderLayout());
        panelNorth.add(northWestPanel, BorderLayout.WEST);
        panelNorth.add(label, BorderLayout.CENTER);

        // Setting the background colors of the panels
        Color bgColor = Color.DARK_GRAY;
        panelNorth.setBackground(bgColor);
        panelSouth.setBackground(bgColor);
        panelEast.setBackground(bgColor);
        panelWest.setBackground(bgColor);
        panelCenter.setBackground(bgColor);
        northWestPanel.setBackground(bgColor);

        // Adding panels to the frame
        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelSouth, BorderLayout.SOUTH);
        this.add(panelEast, BorderLayout.EAST);
        this.add(panelWest, BorderLayout.WEST);
        this.add(panelCenter, BorderLayout.CENTER);

        // Adding buttons to the center panel
        int gapSize = 10;
        panelCenter.setLayout(new GridLayout(gameSize, gameSize, gapSize, gapSize));
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                int number = i * gameSize + j + 1;
                MyButton button = getMyButton(number, gameState, label);
                panelCenter.add(button);
            }
        }

        // Adding the panels to the frame
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelSouth, BorderLayout.SOUTH);
        this.add(panelEast, BorderLayout.EAST);
        this.add(panelWest, BorderLayout.WEST);

        // Setting the visible property of the frame
        this.setVisible(true);
    }

    /**
     * Creates a button for the Tic Tac Toe game.
     *
     * @param number     The number associated with the button.
     * @param gameState  The current state of the game.
     * @param label      The label to update the player's turn.
     * @return          A MyButton object configured for the game.
     */
    private MyButton getMyButton(int number, GameState gameState, JLabel label) {
        String buttonLabel = String.valueOf(number);

        // Creating a new MyButton object with the button label
        MyButton button = new MyButton(buttonLabel);
        button.addActionListener(_ -> {
            // Checking if the game is over
            if (gameState.getGameOver()) {
                JOptionPane.showMessageDialog(this, "Game is over. Please restart the game.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            char currentPlayer = gameState.getCurrentPlayer();

            // Attempting to make a move in the game state
            if (gameState.makeMove(number)) {
                button.clicked(String.valueOf(currentPlayer));
                label.setText("Player " + gameState.getCurrentPlayer() + " turn");

                // Check if the current player has won or if the game is a draw
                if (gameState.checkWinner(currentPlayer)) {
                    // If the player wins, show a message and update the label
                    System.out.println("Player " + currentPlayer + " wins!");
                    label.setText("Player " + currentPlayer + " wins!");
                    JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                } else if (gameState.isDraw()) {
                    // If the game is a draw, show a message and update the label
                    label.setText("It's a draw!");
                    JOptionPane.showMessageDialog(this, "It's a draw!");
                }
            } else {
                // If the move is invalid, show an error message
                JOptionPane.showMessageDialog(this, "Invalid move", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        return button;
    }
}