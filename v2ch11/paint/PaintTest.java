package paint;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates different paint styles.
 * @author ZZy
 */
public class PaintTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new PaintTestFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
