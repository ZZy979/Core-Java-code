package desktopApp;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates the desktop app API.
 * @version 1.01 2016-05-10
 * @author Cay Horstmann
 */
public class DesktopAppTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new DesktopAppFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
