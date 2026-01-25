package treeRender;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates cell rendering and listening to tree selection events.
 * @version 1.05 2018-05-01
 * @author Cay Horstmann
 */
public class ClassTree {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ClassTreeFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
