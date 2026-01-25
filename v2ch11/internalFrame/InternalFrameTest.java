package internalFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates the use of internal frames.
 * @version 1.12 2016-05-10
 * @author Cay Horstmann
 */
public class InternalFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new DesktopFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
