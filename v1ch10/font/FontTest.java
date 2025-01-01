package font;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.35 2018-04-10
 * @author Cay Horstmann
 */
public class FontTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new FontFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}