package dndImage;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import static util.ResourceUtil.getImageIcon;

public class ImageListDnDFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 500;

    private ImageList list1;
    private ImageList list2;

    public ImageListDnDFrame() {
        setTitle("ImageListDnDTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        list1 = new ImageList(new ImageIcon[] {
                getImageIcon("/Mercury.gif"),
                getImageIcon("/Venus.gif"),
                getImageIcon("/Mars.gif"),
                getImageIcon("/Jupiter.gif"),
                getImageIcon("/Uranus.gif"),
                getImageIcon("/Neptune.gif"),
                getImageIcon("/Pluto.gif"),
        });
        list2 = new ImageList(new ImageIcon[] {
                getImageIcon("/Earth.gif"),
                getImageIcon("/Saturn.gif")
        });

        setLayout(new GridLayout(2, 1));
        add(new JScrollPane(list1));
        add(new JScrollPane(list2));
    }
}
