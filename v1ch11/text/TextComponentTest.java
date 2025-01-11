package text;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.42 2018-04-10
 * @author Cay Horstmann
 */
public class TextComponentTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new TextComponentFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
