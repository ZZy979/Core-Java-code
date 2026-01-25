package splitPane;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates the split pane component organizer.
 * @version 1.04 2016-05-10
 * @author Cay Horstmann
 */
public class SplitPaneTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new SplitPaneFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
