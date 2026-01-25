package imageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This frame displays the loaded images. The menu has items for loading and saving files.
 */
public class ImageIOFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    private static Set<String> writerFormats = getWriterFormats();

    private BufferedImage[] images;

    public ImageIOFrame() {
        setTitle("ImageIOTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        var fileMenu = new JMenu("File");
        var openItem = new JMenuItem("Open");
        openItem.addActionListener(event -> openFile());
        fileMenu.add(openItem);

        var saveMenu = new JMenu("Save");
        fileMenu.add(saveMenu);
        Iterator<String> iter = writerFormats.iterator();
        while (iter.hasNext()) {
            String formatName = iter.next();
            var formatItem = new JMenuItem(formatName);
            saveMenu.add(formatItem);
            formatItem.addActionListener(event -> saveFile(formatName));
        }

        var exitItem = new JMenuItem("Exit");
        // exitItem.addActionListener(event -> System.exit(0));
        exitItem.addActionListener(event ->dispose());
        fileMenu.add(exitItem);

        var menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    /**
     * Open a file and load the images.
     */
    public void openFile() {
        var chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        String[] extensions = ImageIO.getReaderFileSuffixes();
        chooser.setFileFilter(new FileNameExtensionFilter("Image files", extensions));

        int r = chooser.showOpenDialog(this);
        if (r != JFileChooser.APPROVE_OPTION) return;
        File f = chooser.getSelectedFile();
        Box box = Box.createVerticalBox();
        try {
            String name = f.getName();
            String suffix = name.substring(name.lastIndexOf(".") + 1);
            Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
            ImageReader reader = iter.next();
            ImageInputStream imageIn = ImageIO.createImageInputStream(f);
            reader.setInput(imageIn);

            int count = reader.getNumImages(true);
            images = new BufferedImage[count];
            for (int i = 0; i < count; i++) {
                images[i] = reader.read(i);
                box.add(new JLabel(new ImageIcon(images[i])));
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, e);
        }

        setContentPane(new JScrollPane(box));
        validate();
    }

    /**
     * Save the current image in a file.
     * @param formatName the file format
     */
    public void saveFile(String formatName) {
        if (images == null) return;
        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(formatName);
        ImageWriter writer = iter.next();
        var chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        String[] extensions = writer.getOriginatingProvider().getFileSuffixes();
        chooser.setFileFilter(new FileNameExtensionFilter("Image files", extensions));

        int r = chooser.showSaveDialog(this);
        if (r != JFileChooser.APPROVE_OPTION) return;
        File f = chooser.getSelectedFile();
        try {
            ImageOutputStream imageOut = ImageIO.createImageOutputStream(f);
            writer.setOutput(imageOut);

            writer.write(new IIOImage(images[0], null, null));
            for (int i = 1; i < images.length; i++) {
                var iioImage = new IIOImage(images[i], null, null);
                if (writer.canInsertImage(i))
                    writer.writeInsert(i, iioImage, null);
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    /**
     * Gets a set of "preferred" format names of all image writers. The preferred format name
     * is the first format name that a writer specifies.
     * @return the format name set
     */
    public static Set<String> getWriterFormats() {
        var writerFormats = new TreeSet<String>();
        var formatNames = new TreeSet<String>(List.of(ImageIO.getWriterFormatNames()));
        while (!formatNames.isEmpty()) {
            String name = formatNames.iterator().next();
            Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(name);
            ImageWriter writer = iter.next();
            String[] names = writer.getOriginatingProvider().getFormatNames();
            String format = names[0];
            if (format.equals(format.toLowerCase()))
                format = format.toUpperCase();
            writerFormats.add(format);
            formatNames.removeAll(List.of(names));
        }
        return writerFormats;
    }
}
