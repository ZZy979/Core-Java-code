package domOld;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program displays an XML document as a tree.
 * @version 1.13 2016-04-27
 * @author Cay Horstmann
 */
public class TreeViewer {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new DOMTreeFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
