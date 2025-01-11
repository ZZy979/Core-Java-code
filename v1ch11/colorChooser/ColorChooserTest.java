package colorChooser;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.04 2015-06-12
 * @author Cay Horstmann
 */
public class ColorChooserTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ColorChooserFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
