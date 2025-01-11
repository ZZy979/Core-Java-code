package fileChooser;

import java.io.File;

import javax.swing.Icon;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

/**
 * A file view that displays an icon for all files that match a file filter.
 */
public class FileIconView extends FileView {
    private FileFilter filter;
    private Icon icon;

    /**
     * Constructs a FileIconView.
     * @param filter a file filter--all files that this filter accepts will be shown
     * with the icon.
     * @param icon the icon shown with all accepted files.
     */
    public FileIconView(FileFilter filter, Icon icon) {
        this.filter = filter;
        this.icon = icon;
    }

    @Override
    public Icon getIcon(File f) {
        if (!f.isDirectory() && filter.accept(f))
            return icon;
        else
            return null;
    }
}
