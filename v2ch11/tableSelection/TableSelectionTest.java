package tableSelection;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates selection, addition, and removal of rows and columns.
 * @version 1.04 2016-05-10
 * @author Cay Horstmann
 */
public class TableSelectionTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new TableSelectionFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
