package clip;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates clipping shape.
 * @author ZZy
 */
public class ClippingTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ClippingTestFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
