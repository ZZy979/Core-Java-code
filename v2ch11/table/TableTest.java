package table;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates how to show a simple table.
 * @version 1.14 2018-05-01
 * @author Cay Horstmann
 */
public class TableTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new PlanetTableFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
