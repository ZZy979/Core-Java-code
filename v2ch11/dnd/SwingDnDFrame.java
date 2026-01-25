package dnd;

import java.awt.BorderLayout;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class SwingDnDFrame extends JFrame {
    public SwingDnDFrame() {
        setTitle("SwingDnDTest");

        JTabbedPane tabbedPane = new JTabbedPane();

        JList<String> list = SampleComponents.list();
        tabbedPane.addTab("List", list);
        JTable table = SampleComponents.table();
        tabbedPane.addTab("Table", table);
        JTree tree = SampleComponents.tree();
        tabbedPane.addTab("Tree", tree);
        JFileChooser fileChooser = new JFileChooser();
        tabbedPane.addTab("File Chooser", fileChooser);
        JColorChooser colorChooser = new JColorChooser();
        tabbedPane.addTab("Color Chooser", colorChooser);

        JTextArea textArea = new JTextArea(8, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new TitledBorder(new EtchedBorder(), "Drag text here"));

        JTextField textField = new JTextField("Drag color here");
        textField.setTransferHandler(new TransferHandler("background"));

        tabbedPane.addChangeListener(event -> textArea.setText(""));

        tree.setDragEnabled(true);
        table.setDragEnabled(true);
        list.setDragEnabled(true);
        fileChooser.setDragEnabled(true);
        colorChooser.setDragEnabled(true);
        textField.setDragEnabled(true);

        add(tabbedPane, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);
        pack();
    }
}
