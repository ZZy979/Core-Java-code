package tableCellRender;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import static util.ResourceUtil.getImageIcon;

/**
 * This frame contains a table of planet data.
 */
public class TableCellRenderFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;

    public TableCellRenderFrame() {
        setTitle("TableCellRenderTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        var model = new PlanetTableModel();
        var table = new JTable(model);
        table.setRowSelectionAllowed(false);

        // set up renderers and editors
        table.setDefaultRenderer(Color.class, new ColorTableCellRenderer());
        table.setDefaultEditor(Color.class, new ColorTableCellEditor());

        var moonCombo = new JComboBox<Integer>();
        for (int i = 0; i <= 20; i++)
            moonCombo.addItem(i);

        TableColumnModel columnModel = table.getColumnModel();
        TableColumn moonColumn = columnModel.getColumn(PlanetTableModel.MOONS_COLUMN);
        moonColumn.setCellEditor(new DefaultCellEditor(moonCombo));
        moonColumn.setHeaderRenderer(table.getDefaultRenderer(ImageIcon.class));
        moonColumn.setHeaderValue(getImageIcon("/Moons.gif"));

        // show table
        table.setRowHeight(100);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
