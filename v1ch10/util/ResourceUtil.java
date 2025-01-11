package util;

import javax.swing.ImageIcon;

public class ResourceUtil {
    public static ImageIcon getImageIcon(String name) {
        var url = ResourceUtil.class.getResource(name);
        return url != null ? new ImageIcon(url) : null;
    }
}
