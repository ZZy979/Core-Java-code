package editorPane;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates how to display HTML documents in an editor pane.
 * @version 1.05 2016-05-10
 * @author Cay Horstmann
 */
public class EditorPaneTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new EditorPaneFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
