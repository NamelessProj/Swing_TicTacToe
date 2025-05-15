import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    /**
     * Constructor for MyButton.
     * @param text The text to display on the button.
     */
    MyButton(String text) {
        this.setText(text);
        this.setFocusable(false);
        this.setFont(new Font("Arial", Font.BOLD, 20));
    }

    /**
     * This method is called when the button is clicked.
     * @param newText The new text to set on the button.
     */
    public void clicked(String newText) {
        this.setEnabled(false);
        this.setText(newText);
    }
}