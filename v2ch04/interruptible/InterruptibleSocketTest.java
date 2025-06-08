package interruptible;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program shows how to interrupt a socket channel.
 * @author Cay Horstmann
 * @version 1.05 2018-03-17
 */
public class InterruptibleSocketTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new InterruptibleSocketFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
