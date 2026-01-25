package tableCellRender;

import java.awt.Color;

import javax.swing.table.AbstractTableModel;

import static util.ResourceUtil.getImageIcon;

/**
 * The planet table model specifies the values, rendering and editing properties for the
 * planet data.
 */
public class PlanetTableModel extends AbstractTableModel {
    public static final int PLANET_COLUMN = 0;
    public static final int MOONS_COLUMN = 2;
    public static final int GASEOUS_COLUMN = 3;
    public static final int COLOR_COLUMN = 4;

    private Object[][] cells = {
            {"Mercury", 2440.0, 0, false, Color.YELLOW, getImageIcon("/Mercury.gif")},
            {"Venus", 6052.0, 0, false, Color.YELLOW, getImageIcon("/Venus.gif")},
            {"Earth", 6378.0, 1, false, Color.BLUE, getImageIcon("/Earth.gif")},
            {"Mars", 3397.0, 2, false, Color.RED, getImageIcon("/Mars.gif")},
            {"Jupiter", 71492.0, 16, true, Color.ORANGE, getImageIcon("/Jupiter.gif")},
            {"Saturn", 60268.0, 18, true, Color.ORANGE, getImageIcon("/Saturn.gif")},
            {"Uranus", 25559.0, 17, true, Color.BLUE, getImageIcon("/Uranus.gif")},
            {"Neptune", 24766.0, 8, true, Color.BLUE, getImageIcon("/Neptune.gif")},
            {"Pluto", 1137.0, 1, false, Color.BLACK, getImageIcon("/Pluto.gif")}
    };

    private String[] columnNames = {"Planet", "Radius", "Moons", "Gaseous", "Color", "Image"};

    @Override
    public String getColumnName(int c) {
        return columnNames[c];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return cells[0][c].getClass();
    }

    @Override
    public int getRowCount() {
        return cells.length;
    }

    @Override
    public int getColumnCount() {
        return cells[0].length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        return cells[r][c];
    }

    @Override
    public void setValueAt(Object obj, int r, int c) {
        cells[r][c] = obj;
    }

    @Override
    public boolean isCellEditable(int r, int c) {
        return c == PLANET_COLUMN || c == MOONS_COLUMN || c == GASEOUS_COLUMN || c == COLOR_COLUMN;
    }
}
