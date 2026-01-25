package composite;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates the Porter-Duff composition rules.
 * @version 1.05 2018-05-01
 * @author Cay Horstmann
 */
public class CompositeTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new CompositeTestFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
