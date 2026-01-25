package progressMonitor;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * A program to test a progress monitor dialog.
 * @version 1.05 2016-05-10
 * @author Cay Horstmann
 */
public class ProgressMonitorTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ProgressMonitorFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
