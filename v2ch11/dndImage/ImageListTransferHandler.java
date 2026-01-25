package dndImage;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

import imageTransfer.ImageTransferable;

public class ImageListTransferHandler extends TransferHandler {
    // support for drag

    @Override
    public int getSourceActions(JComponent source) {
        return COPY_OR_MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent source) {
        ImageList list = (ImageList) source;
        int index = list.getSelectedIndex();
        if (index < 0) return null;
        ImageIcon icon = list.getModel().getElementAt(index);
        return new ImageTransferable(icon.getImage());
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        if (action == MOVE) {
            ImageList list = (ImageList) source;
            int index = list.getSelectedIndex();
            if (index < 0) return;
            DefaultListModel<?> model = (DefaultListModel<?>) list.getModel();
            model.remove(index);
        }
    }

    // support for drop

    @Override
    public boolean canImport(TransferSupport support) {
        if (support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            if (support.getUserDropAction() == MOVE)
                support.setDropAction(COPY);
            return true;
        }
        else
            return support.isDataFlavorSupported(DataFlavor.imageFlavor);
    }

    @Override
    public boolean importData(TransferSupport support) {
        ImageList list = (ImageList) support.getComponent();
        DefaultListModel<ImageIcon> model = (DefaultListModel<ImageIcon>) list.getModel();

        Transferable transferable = support.getTransferable();
        List<DataFlavor> flavors = Arrays.asList(transferable.getTransferDataFlavors());

        List<Image> images = new ArrayList<>();
        try {
            if (flavors.contains(DataFlavor.javaFileListFlavor)) {
                @SuppressWarnings("unchecked") List<File> fileList =
                        (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                for (File f : fileList) {
                    try {
                        images.add(ImageIO.read(f));
                    }
                    catch (IOException e) {
                        // couldn't read image--skip
                    }
                }
            }
            else if (flavors.contains(DataFlavor.imageFlavor)) {
                images.add((Image) transferable.getTransferData(DataFlavor.imageFlavor));
            }

            int index;
            if (support.isDrop()) {
                JList.DropLocation location = (JList.DropLocation) support.getDropLocation();
                index = location.getIndex();
                if (!location.isInsert())
                    model.remove(index);  // replace location
            }
            else
                index = model.size();

            for (Image image : images) {
                model.add(index, new ImageIcon(image));
                index++;
            }
            return true;
        }
        catch (IOException | UnsupportedFlavorException e) {
            return false;
        }
    }
}
