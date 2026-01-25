package tableCellRender;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates cell rendering and editing in a table.
 * @version 1.05 2018-05-01
 * @author Cay Horstmann
 */
public class TableCellRenderTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new TableCellRenderFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
