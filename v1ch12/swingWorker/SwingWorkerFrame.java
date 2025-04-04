package swingWorker;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 * This frame has a text area to show the contents of a text file, a menu to open a file and
 * cancel the opening process, and a status line to show the file loading progress.
 */
public class SwingWorkerFrame extends JFrame {
    private JFileChooser chooser;
    private JTextArea textArea;
    private JLabel statusLine;
    private JMenuItem openItem;
    private JMenuItem cancelItem;
    private TextReader textReader;
    public static final int TEXT_ROWS = 20;
    public static final int TEXT_COLUMNS = 60;

    public SwingWorkerFrame() {
        setTitle("SwingWorkerTest");

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(textArea));

        statusLine = new JLabel(" ");
        add(statusLine, BorderLayout.SOUTH);

        var menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        var menu = new JMenu("File");
        menuBar.add(menu);

        openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(event -> {
            // show file chooser dialog
            int result = chooser.showOpenDialog(null);

            // if file selected, set it as icon of the label
            if (result == JFileChooser.APPROVE_OPTION) {
                textArea.setText("");
                openItem.setEnabled(false);
                textReader = new TextReader(chooser.getSelectedFile());
                textReader.execute();
                cancelItem.setEnabled(true);
            }
        });

        cancelItem = new JMenuItem("Cancel");
        menu.add(cancelItem);
        cancelItem.setEnabled(false);
        cancelItem.addActionListener(event -> textReader.cancel(true));
        pack();
    }

    private class ProgressData {
        public int number;
        public String line;
    }

    private class TextReader extends SwingWorker<StringBuilder, ProgressData> {
        private File file;
        private StringBuilder text = new StringBuilder();

        public TextReader(File file) {
            this.file = file;
        }

        // the following method executes in the worker thread; it doesn't touch Swing components
        @Override
        protected StringBuilder doInBackground() throws IOException, InterruptedException {
            int lineNumber = 0;
            try (var in = new Scanner(new FileInputStream(file), StandardCharsets.UTF_8)) {
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    lineNumber++;
                    text.append(line).append("\n");
                    var data = new ProgressData();
                    data.number = lineNumber;
                    data.line = line;
                    publish(data);
                    Thread.sleep(1);  // to test cancellation; no need to do this in your programs
                }
            }
            return text;
        }

        // the following methods execute in the event dispatch thread
        @Override
        protected void process(List<ProgressData> data) {
            if (isCancelled()) return;
            var builder = new StringBuilder();
            statusLine.setText("" + data.get(data.size() - 1).number);
            for (ProgressData d : data)
                builder.append(d.line).append("\n");
            textArea.append(builder.toString());
        }

        @Override
        protected void done() {
            try {
                StringBuilder result = get();
                textArea.setText(result.toString());
                statusLine.setText("Done");
            }
            catch (InterruptedException e) {
            }
            catch (CancellationException e) {
                textArea.setText("");
                statusLine.setText("Cancelled");
            }
            catch (ExecutionException e) {
                statusLine.setText("" + e.getCause());
            }

            cancelItem.setEnabled(false);
            openItem.setEnabled(true);
        }
    }
}
