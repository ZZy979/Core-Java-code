package area;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import area.AreaComponent.CombineType;

/**
 * This frame lets user choose constructive area geometry operation to combine areas.
 */
public class AreaTestFrame extends JFrame {
    private AreaComponent canvas;

    public AreaTestFrame() {
        setTitle("AreaTest");

        canvas = new AreaComponent();
        add(canvas, BorderLayout.CENTER);

        var comboBox = new JComboBox<CombineType>();
        comboBox.addItem(CombineType.ADD);
        comboBox.addItem(CombineType.SUBTRACT);
        comboBox.addItem(CombineType.INTERSECT);
        comboBox.addItem(CombineType.EXCLUSIVE_OR);
        comboBox.addActionListener(event ->
                canvas.setCombineType(comboBox.getItemAt(comboBox.getSelectedIndex())));
        add(comboBox, BorderLayout.NORTH);
        pack();
    }
}
