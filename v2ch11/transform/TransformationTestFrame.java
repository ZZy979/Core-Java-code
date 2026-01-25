package transform;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * This frame lets user apply coordinate transformations to shape.
 */
public class TransformationTestFrame extends JFrame {
    private TransformationComponent canvas;

    private JList<AffineTransform> transformList;
    private DefaultListModel<AffineTransform> transformListModel;

    private JRadioButton scaleButton;
    private JRadioButton rotateButton;
    private JRadioButton translateButton;
    private JRadioButton shearButton;

    private JTextField xField;
    private JTextField yField;
    private JTextField angleField;

    public TransformationTestFrame() {
        setTitle("TransformationTest");

        var transformPanel = new JPanel(new BorderLayout());
        transformListModel = new DefaultListModel<AffineTransform>();
        transformList = new JList<>(transformListModel);
        transformList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        transformPanel.add(new JScrollPane(transformList), BorderLayout.CENTER);

        var optionPanel = new JPanel(new GridLayout(3, 1));
        var radioButtonPanel = new JPanel();
        var buttonGroup = new ButtonGroup();
        scaleButton = makeRadioButton("Scale", buttonGroup, radioButtonPanel);
        rotateButton = makeRadioButton("Rotate", buttonGroup, radioButtonPanel);
        translateButton = makeRadioButton("Translate", buttonGroup, radioButtonPanel);
        shearButton = makeRadioButton("Shear", buttonGroup, radioButtonPanel);
        optionPanel.add(radioButtonPanel);

        var textFieldPanel = new JPanel();
        xField = new JTextField(5);
        yField = new JTextField(5);
        angleField = new JTextField(5);
        textFieldPanel.add(new JLabel("x"));
        textFieldPanel.add(xField);
        textFieldPanel.add(new JLabel("y"));
        textFieldPanel.add(yField);
        textFieldPanel.add(new JLabel("degree"));
        textFieldPanel.add(angleField);
        optionPanel.add(textFieldPanel);

        var buttonPanel = new JPanel();
        var addButton = new JButton("Add");
        addButton.addActionListener(event -> addTransformation());
        buttonPanel.add(addButton);
        var removeButton = new JButton("Remove");
        removeButton.addActionListener(event -> removeTransformation());
        buttonPanel.add(removeButton);
        var clearButton = new JButton("Clear");
        clearButton.addActionListener(event -> clearTransformations());
        buttonPanel.add(clearButton);
        optionPanel.add(buttonPanel);
        transformPanel.add(optionPanel, BorderLayout.NORTH);
        add(transformPanel, BorderLayout.WEST);

        canvas = new TransformationComponent();
        add(canvas, BorderLayout.CENTER);
        pack();
    }

    private JRadioButton makeRadioButton(String label, ButtonGroup group, JPanel panel) {
        boolean selected = group.getButtonCount() == 0;
        var radioButton = new JRadioButton(label, selected);
        radioButton.addActionListener(event -> {});
        group.add(radioButton);
        panel.add(radioButton);
        return radioButton;
    }

    public AffineTransform getTransform() {
        var transform = new AffineTransform();
        var e = transformListModel.elements();
        while (e.hasMoreElements())
            transform.concatenate(e.nextElement());
        return transform;
    }

    private void updateCanvas() {
        canvas.setTransform(getTransform());
    }

    private void addTransformation() {
        try {
            double x = Double.parseDouble(xField.getText());
            double y = Double.parseDouble(yField.getText());

            AffineTransform newTransform;
            if (scaleButton.isSelected())
                newTransform = AffineTransform.getScaleInstance(x, y);
            else if (rotateButton.isSelected()) {
                double degree = Double.parseDouble(angleField.getText());
                newTransform = AffineTransform.getRotateInstance(Math.toRadians(degree), x, y);
            }
            else if (translateButton.isSelected())
                newTransform = AffineTransform.getTranslateInstance(x, y);
            else
                newTransform = AffineTransform.getShearInstance(x, y);
            transformListModel.addElement(newTransform);
            updateCanvas();
        }
        catch (NumberFormatException e) {
        }
    }

    private void removeTransformation() {
        int selected = transformList.getSelectedIndex();
        if (selected >= 0)
            transformListModel.removeElementAt(selected);
        else if (transformListModel.getSize() > 0)
            transformListModel.removeElementAt(transformListModel.size() - 1);
        updateCanvas();
    }

    private void clearTransformations() {
        transformListModel.clear();
        updateCanvas();
    }
}
