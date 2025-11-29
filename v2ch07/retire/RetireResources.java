package retire;

import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * These are the English non-string resources for the retirement calculator.
 * @version 1.21 2001-08-27
 * @author Cay Horstmann
 */
public class RetireResources extends ListResourceBundle {
    private static final Object[][] contents = {
            {"colorPre", Color.BLUE}, {"colorGain", Color.WHITE}, {"colorLoss", Color.RED}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
