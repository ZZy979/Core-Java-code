package tabbedPane;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates the tabbed pane component organizer.
 * @version 1.04 2016-05-10
 * @author Cay Horstmann
 */
public class TabbedPaneTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new TabbedPaneFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
