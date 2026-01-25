package textChange;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.41 2016-05-10
 * @author Cay Horstmann
 */
public class ChangeTrackingTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ColorFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
