import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    MyButton(String text) {
        this.setText(text);
        this.setFocusable(false);
        this.setFont(new Font("Arial", Font.BOLD, 20));
    }

    public void clicked(String newText) {
        //this.setEnabled(false);
        this.setText(newText);
    }
}