package colorChooser;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * A panel with buttons to pop up three types of color choosers
 */
public class ColorChooserPanel extends JPanel {
    public ColorChooserPanel() {
        var modalButton = new JButton("Modal");
        modalButton.addActionListener(new ModalListener());
        add(modalButton);

        var modelessButton = new JButton("Modeless");
        modelessButton.addActionListener(new ModelessListener());
        add(modelessButton);

        var immediateButton = new JButton("Immediate");
        immediateButton.addActionListener(new ImmediateListener());
        add(immediateButton);
    }

    /**
     * This listener pops up a modal color chooser
     */
    private class ModalListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            var defaultColor = getBackground();
            var selected = JColorChooser.showDialog(ColorChooserPanel.this, "Set background", defaultColor);
            if (selected != null)
                setBackground(selected);
        }
    }

    /**
     * This listener pops up a modeless color chooser. The panel color is changed when the user
     * clicks the OK button.
     */
    private class ModelessListener implements ActionListener {
        private JDialog dialog;
        private JColorChooser chooser;

        public ModelessListener() {
            chooser = new JColorChooser();
            dialog = JColorChooser.createDialog(ColorChooserPanel.this, "Background Color",
                    false, chooser, event -> setBackground(chooser.getColor()), null);
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            chooser.setColor(getBackground());
            dialog.setVisible(true);
        }
    }

    /**
     * This listener pops up a modeless color chooser. The panel color is changed immediately when
     * the user picks a new color.
     */
    private class ImmediateListener implements ActionListener {
        private JDialog dialog;
        private JColorChooser chooser;

        public ImmediateListener() {
            chooser = new JColorChooser();
            chooser.getSelectionModel().addChangeListener(event -> setBackground(chooser.getColor()));

            dialog = new JDialog((Frame) null, false);
            dialog.add(chooser);
            dialog.pack();
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            chooser.setColor(getBackground());
            dialog.setVisible(true);
        }
    }
}
