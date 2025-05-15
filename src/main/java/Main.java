import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        int MAX_SIZE = 10;
        int MIN_SIZE = 2;
        int gameSize;

        while (true) {
            String input = JOptionPane.showInputDialog("Enter the size of the game board (between " + MIN_SIZE + " and " + MAX_SIZE + "):");
            if (input == null) {
                System.exit(0);
            }
            try {
                gameSize = Integer.parseInt(input);
                if (gameSize < MIN_SIZE || gameSize > MAX_SIZE) {
                    JOptionPane.showMessageDialog(null, "Invalid size. Please enter a number between " + MIN_SIZE + " and " + MAX_SIZE + ".", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        new MyFrame(gameSize);
    }
}