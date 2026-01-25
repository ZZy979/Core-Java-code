package transform;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates coordinate transformations.
 * @author ZZy
 */
public class TransformationTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new TransformationTestFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
