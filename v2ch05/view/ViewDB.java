package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program uses metadata to display arbitrary tables in a database.
 * @version 1.35 2021-06-17
 * @author Cay Horstmann
 */
public class ViewDB {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ViewDBFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
