package readOld;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class FontFrame extends JFrame {
    private GridBagPane gridbag;
    private JComboBox<String> face;
    private JComboBox<String> size;
    private JCheckBox bold;
    private JCheckBox italic;

    /**
     * This frame contains a font selection dialog that is described by an XML file.
     * @param file the file containing the user interface components for the dialog.
     */
    @SuppressWarnings("unchecked")
    public FontFrame(File file) {
        setTitle("GridBagTest");

        gridbag = new GridBagPane(file);
        add(gridbag);

        face = (JComboBox<String>) gridbag.get("face");
        size = (JComboBox<String>) gridbag.get("size");
        bold = (JCheckBox) gridbag.get("bold");
        italic = (JCheckBox) gridbag.get("italic");

        face.setModel(new DefaultComboBoxModel<>(
                new String[] {"Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput"}));

        size.setModel(new DefaultComboBoxModel<>(
                new String[] {"8", "10", "12", "15", "18", "24", "36", "48"}));

        ActionListener listener = event -> setSample();
        face.addActionListener(listener);
        size.addActionListener(listener);
        bold.addActionListener(listener);
        italic.addActionListener(listener);

        setSample();
        pack();
    }

    /**
     * This method sets the text sample to the selected font.
     */
    public void setSample() {
        String fontFace = face.getItemAt(face.getSelectedIndex());
        int fontSize = Integer.parseInt(size.getItemAt(size.getSelectedIndex()));
        JTextArea sample = (JTextArea) gridbag.get("sample");
        int fontStyle = (bold.isSelected() ? Font.BOLD : 0) + (italic.isSelected() ? Font.ITALIC : 0);
        sample.setFont(new Font(fontFace, fontStyle, fontSize));
        sample.repaint();
    }
}
