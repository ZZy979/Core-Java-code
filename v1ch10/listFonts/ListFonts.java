package listFonts;

import java.awt.GraphicsEnvironment;

/**
 * Prints the names of all fonts on your system.
 */
public class ListFonts {
    public static void main(String[] args) {
        String[] fontNames = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();
        for (String fontName : fontNames)
            System.out.println(fontName);
    }
}
