package swingWorker;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates a worker thread that runs a potentially time-consuming task.
 * @version 1.12 2018-03-17
 * @author Cay Horstmann
 */
public class SwingWorkerTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new SwingWorkerFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
