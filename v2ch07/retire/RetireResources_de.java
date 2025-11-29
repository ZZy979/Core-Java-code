package retire;

import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * These are the German non-string resources for the retirement calculator.
 * @version 1.21 2001-08-27
 * @author Cay Horstmann
 */
public class RetireResources_de extends ListResourceBundle {
    private static final Object[][] contents = {
            {"colorPre", Color.YELLOW}, {"colorGain", Color.BLACK}, {"colorLoss", Color.RED}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
