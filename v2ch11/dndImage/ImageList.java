package dndImage;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JList;

public class ImageList extends JList<ImageIcon> {
    public ImageList(ImageIcon[] images) {
        DefaultListModel<ImageIcon> model = new DefaultListModel<>();
        for (ImageIcon image : images)
            model.addElement(image);

        setModel(model);
        setVisibleRowCount(0);
        setLayoutOrientation(JList.HORIZONTAL_WRAP);
        setDragEnabled(true);
        setDropMode(DropMode.ON_OR_INSERT);
        setTransferHandler(new ImageListTransferHandler());
    }
}
