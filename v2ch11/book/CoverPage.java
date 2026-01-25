package book;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 * This class prints a cover page with a title.
 */
public class CoverPage implements Printable {
    private String title;

    /**
     * Constructs a cover page.
     * @param t the title
     */
    public CoverPage(String t) {
        title = t;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
       if (page >= 1)
           return Printable.NO_SUCH_PAGE;
       var g2 = (Graphics2D) g;
       g2.setPaint(Color.BLACK);
       g2.translate(pf.getImageableX(), pf.getImageableY());

        FontRenderContext context = g2.getFontRenderContext();
        Font f = g2.getFont();
        var layout = new TextLayout(title, f, context);
        float ascent = layout.getAscent();
        g2.drawString(title, 0, ascent);
        return Printable.PAGE_EXISTS;
    }
}
