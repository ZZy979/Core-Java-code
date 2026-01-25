package stroke;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates different stroke types.
 * @version 1.05 2018-05-01
 * @author Cay Horstmann
 */
public class StrokeTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new StrokeTestFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
