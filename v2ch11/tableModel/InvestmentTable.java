package tableModel;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * This program shows how to build a table from a table model.
 * @version 1.05 2021-09-09
 * @author Cay Horstmann
 */
public class InvestmentTable {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new InvestmentTableFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
