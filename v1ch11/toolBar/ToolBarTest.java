package toolBar;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @version 1.15 2018-04-10
 * @author Cay Horstmann
 */
public class ToolBarTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ToolBarFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
