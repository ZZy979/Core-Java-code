package writeOld;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program shows how to write an XML file. It saves a file describing a modern drawing in SVG
 * format.
 * @version 1.12 2016-04-27
 * @author Cay Horstmann
 */
public class XMLWriteTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new XMLWriteFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
