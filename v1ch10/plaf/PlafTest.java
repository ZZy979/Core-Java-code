package plaf;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.32 2015-06-12
 * @author Cay Horstmann
 */
public class PlafTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new PlafFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
