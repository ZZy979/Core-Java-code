package listRendering;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

/**
 * This frame contains a list with a set of fonts and a text area that is set to the selected font.
 */
public class ListRenderingFrame extends JFrame {
    private static final int TEXT_ROWS = 8;
    private static final int TEXT_COLUMNS = 20;

    private JTextArea text;
    private JList<Font> fontList;

    public ListRenderingFrame() {
        setTitle("ListRenderingTest");

        List<Font> fonts = new ArrayList<>();
        final int SIZE = 24;
        fonts.add(new Font("Serif", Font.PLAIN, SIZE));
        fonts.add(new Font("SansSerif", Font.PLAIN, SIZE));
        fonts.add(new Font("Monospaced", Font.PLAIN, SIZE));
        fonts.add(new Font("Dialog", Font.PLAIN, SIZE));
        fonts.add(new Font("DialogInput", Font.PLAIN, SIZE));

        fontList = new JList<>(fonts.toArray(new Font[0]));
        fontList.setVisibleRowCount(4);
        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fontList.setCellRenderer(new FontCellRenderer());
        JScrollPane scrollPane = new JScrollPane(fontList);

        JPanel p = new JPanel();
        p.add(scrollPane);
        fontList.addListSelectionListener(event -> {
            Font font = fontList.getSelectedValue();
            text.setFont(font);
        });

        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.SOUTH);
        text = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        text.setText("The quick brown fox jumps over the lazy dog");
        text.setFont(fonts.get(0));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        contentPane.add(text, BorderLayout.CENTER);
        pack();
    }
}
