package retire;

import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * These are the Chinese non-string resources for the retirement calculator.
 * @version 1.21 2001-08-27
 * @author Cay Horstmann
 */
public class RetireResources_zh extends ListResourceBundle {
    private static final Object[][] contents = {
            {"colorPre", Color.RED}, {"colorGain", Color.BLUE}, {"colorLoss", Color.YELLOW}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
