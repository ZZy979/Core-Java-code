package draw;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.34 2018-04-10
 * @author Cay Horstmann
 */
public class DrawTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new DrawFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
