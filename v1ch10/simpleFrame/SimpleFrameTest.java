package simpleFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.34 2018-04-10
 * @author Cay Horstmann
 */
public class SimpleFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new SimpleFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
