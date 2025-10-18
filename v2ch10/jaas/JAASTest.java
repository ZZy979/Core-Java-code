package jaas;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program authenticates a user via a custom login
 * @version 1.04 2021-05-30
 * @author Cay Horstmann
 */
public class JAASTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new JAASFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
