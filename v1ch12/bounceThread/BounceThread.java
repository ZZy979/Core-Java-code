package bounceThread;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * Shows animated bouncing balls.
 * @version 1.34 2015-06-21
 * @author Cay Horstmann
 */
public class BounceThread {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new BounceFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
