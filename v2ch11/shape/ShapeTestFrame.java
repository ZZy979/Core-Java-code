package shape;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * This frame contains a combo box to select a shape and a component to draw it.
 */
public class ShapeTestFrame extends JFrame {
    public ShapeTestFrame() {
        setTitle("ShapeTest");

        var comp = new ShapeComponent();
        add(comp, BorderLayout.CENTER);

        var comboBox = new JComboBox<ShapeMaker>();
        comboBox.addItem(new LineMaker());
        comboBox.addItem(new RectangleMaker());
        comboBox.addItem(new RoundRectangleMaker());
        comboBox.addItem(new EllipseMaker());
        comboBox.addItem(new ArcMaker());
        comboBox.addItem(new PolygonMaker());
        comboBox.addItem(new QuadCurveMaker());
        comboBox.addItem(new CubicCurveMaker());
        comboBox.addActionListener(event -> {
            ShapeMaker shapeMaker = comboBox.getItemAt(comboBox.getSelectedIndex());
            comp.setShapeMaker(shapeMaker);
        });
        add(comboBox, BorderLayout.NORTH);
        comp.setShapeMaker(comboBox.getItemAt(0));
        pack();
    }
}
