package xpathOld;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program evaluates XPath expressions.
 * @version 1.02 2016-05-10
 * @author Cay Horstmann
 */
public class XPathTester {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new XPathFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
