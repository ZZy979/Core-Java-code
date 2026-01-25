package paint;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import paint.PaintComponent.PaintStyle;

/**
 * This frame lets user choose paint style to fill shape.
 */
public class PaintTestFrame extends JFrame {
    private PaintComponent canvas;

    public PaintTestFrame() {
        setTitle("PaintTest");

        canvas = new PaintComponent();
        add(canvas, BorderLayout.CENTER);

        var comboBox = new JComboBox<PaintStyle>();
        comboBox.addItem(PaintStyle.SOLID);
        comboBox.addItem(PaintStyle.GRADIENT);
        comboBox.addItem(PaintStyle.TEXTURE);
        comboBox.addActionListener(event ->
                canvas.setPaintStyle(comboBox.getItemAt(comboBox.getSelectedIndex())));
        add(comboBox, BorderLayout.NORTH);
        pack();
    }
}
