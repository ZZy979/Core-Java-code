package tree;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program shows a simple tree.
 * @version 1.04 2018-05-01
 * @author Cay Horstmann
 */
public class SimpleTree {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new SimpleTreeFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
