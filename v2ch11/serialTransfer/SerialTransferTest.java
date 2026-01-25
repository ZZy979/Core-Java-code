package serialTransfer;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program demonstrates the transfer of serialized objects between virtual machines.
 * @version 1.03 2016-05-10
 * @author Cay Horstmann
 */
public class SerialTransferTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new SerialTransferFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
