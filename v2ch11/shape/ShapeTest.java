package shape;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates the various 2D shapes.
 * @version 1.04 2018-05-01
 * @author Cay Horstmann
 */
public class ShapeTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ShapeTestFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
