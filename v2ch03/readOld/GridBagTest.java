package readOld;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * This program shows how to use an XML file to describe a gridbag layout
 * @version 1.13 2021-05-30
 * @author Cay Horstmann
 */
public class GridBagTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFileChooser chooser = new JFileChooser(".");
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            JFrame frame = new FontFrame(file);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
