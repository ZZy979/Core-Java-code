package tableModel;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * This frame contains the investment table.
 */
public class InvestmentTableFrame extends JFrame {
    public InvestmentTableFrame() {
        setTitle("InvestmentTable");
        var model = new InvestmentTableModel(30, 5, 10);
        var table = new JTable(model);
        add(new JScrollPane(table));
        pack();
    }
}
