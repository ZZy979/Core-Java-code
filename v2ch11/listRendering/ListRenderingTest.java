package listRendering;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates the use of cell renderers in a list box.
 * @version 1.25 2016-05-10
 * @author Cay Horstmann
 */
public class ListRenderingTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ListRenderingFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
