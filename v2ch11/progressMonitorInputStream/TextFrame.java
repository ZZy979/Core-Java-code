package progressMonitorInputStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ProgressMonitorInputStream;
import javax.swing.SwingWorker;

/**
 * A frame with a menu to load a text file and a text area to display its contents. The text area is
 * constructed when the file is loaded and set as the content pane of the frame when the loading is
 * complete. That avoids flicker during loading.
 */
public class TextFrame extends JFrame {
    public static final int TEXT_ROWS = 10;
    public static final int TEXT_COLUMNS = 40;

    private JMenuItem openItem;
    private JMenuItem exitItem;
    private JTextArea textArea;
    private JFileChooser chooser;

    public TextFrame() {
        setTitle("ProgressMonitorInputStreamTest");

        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(textArea));

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        openItem = new JMenuItem("Open");
        openItem.addActionListener(event -> {
            try {
                openFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });

        fileMenu.add(openItem);
        exitItem = new JMenuItem("Exit");
        // exitItem.addActionListener(event -> System.exit(0));
        exitItem.addActionListener(event -> dispose());
        fileMenu.add(exitItem);
        pack();
    }

    /**
     * Prompts the user to select a file, loads the file into a text area, and sets it as the content
     * pane of the frame.
     */
    public void openFile() throws IOException {
        int r = chooser.showOpenDialog(this);
        if (r != JFileChooser.APPROVE_OPTION)
            return;
        File f = chooser.getSelectedFile();

        // set up stream and reader filter sequence
        InputStream fileIn = Files.newInputStream(f.toPath());
        ProgressMonitorInputStream progressIn = new ProgressMonitorInputStream(
                this, "Reading " + f.getName(), fileIn);

        textArea.setText("");

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                try (Scanner in = new Scanner(progressIn, StandardCharsets.UTF_8)) {
                    while (in.hasNextLine()) {
                        String line = in.nextLine();
                        textArea.append(line);
                        textArea.append("\n");
                    }
                }
                return null;
            }
        };
        worker.execute();
    }
}
