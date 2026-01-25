import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 * Main frame of Swing Demos Collection
 * @author ZZy
 */
public class MainFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 600;

    private JPanel buttonPanel;

    public MainFrame() {
        setTitle("Swing Demos Collection");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        var demos = new LinkedHashMap<String, Class<? extends JFrame>>();
        demos.put("Hello World", simpleFrame.SimpleFrame.class);
        demos.put("Get screen size & set icon", sizedFrame.SizedFrame.class);
        demos.put("Draw string", notHelloWorld.NotHelloWorldFrame.class);
        demos.put("Draw geometric shapes", draw.DrawFrame.class);
        demos.put("Fill geometric shapes", fill.FillFrame.class);
        demos.put("Draw baseline and string bounds", font.FontFrame.class);
        demos.put("Display a tiled image", image.ImageFrame.class);
        demos.put("Change background color (Event handling)", button.ButtonFrame.class);
        demos.put("Change look-and-feel", plaf.PlafFrame.class);
        demos.put("Change background color (Swing actions)", action.ActionFrame.class);
        demos.put("Simple graphics editor (Mouse events)", mouse.MouseFrame.class);
        demos.put("Image viewer (Preferences API)", preferences.ImageViewerFrame.class);
        demos.put("Layout manager (Flow layout)", layoutManager.FlowLayoutFrame.class);
        demos.put("Layout manager (Border layout)", layoutManager.BorderLayoutFrame.class);
        demos.put("Simple calculator (Grid layout)", calculator.CalculatorFrame.class);
        demos.put("Text components", text.TextComponentFrame.class);
        demos.put("Checkboxes", checkBox.CheckBoxFrame.class);
        demos.put("Radio Buttons", radioButton.RadioButtonFrame.class);
        demos.put("Borders", border.BorderFrame.class);
        demos.put("Combo boxes", comboBox.ComboBoxFrame.class);
        demos.put("Sliders", slider.SliderFrame.class);
        demos.put("Menus", menu.MenuFrame.class);
        demos.put("Toolbars", toolBar.ToolBarFrame.class);
        demos.put("Font selector (Grid bag layout)", gridbag.FontFrame.class);
        demos.put("Custom layout manager", circleLayout.CircleLayoutFrame.class);
        demos.put("Option dialogs", optionDialog.OptionDialogFrame.class);
        demos.put("Custom dialogs", dialog.DialogFrame.class);
        demos.put("Login dialog (Data exchange)", dataExchange.DataExchangeFrame.class);
        demos.put("Image viewer (File chooser dialogs)", fileChooser.ImageViewerFrame.class);
        demos.put("Color chooser dialogs", colorChooser.ColorChooserFrame.class);
        demos.put("Event tracer", eventTracer.EventTracerFrame.class);
        demos.put("Lists", list.ListFrame.class);
        demos.put("List model", longList.LongListFrame.class);
        demos.put("List cell renderer", listRendering.ListRenderingFrame.class);
        demos.put("Simple table", table.PlanetTableFrame.class);
        demos.put("Investment Growth (Table model)", tableModel.InvestmentTableFrame.class);
        demos.put("Table rows and columns", tableRowColumn.PlanetTableFrame.class);
        demos.put("Table selection", tableSelection.TableSelectionFrame.class);
        demos.put("Table cell renderer and editor", tableCellRender.TableCellRenderFrame.class);
        demos.put("Simple tree", tree.SimpleTreeFrame.class);
        demos.put("Tree editing", treeEdit.TreeEditFrame.class);
        demos.put("Class browser (Tree renderer and events)", treeRender.ClassTreeFrame.class);
        // demos.put("Object inspector (Tree model)", treeModel.ObjectInspectorFrame.class);
        demos.put("Document listener", textChange.ColorFrame.class);
        demos.put("Formatted text fields", textFormat.FormatTestFrame.class);
        demos.put("Spinners", spinner.SpinnerFrame.class);
        demos.put("Simple Web browser (Editor pane)", editorPane.EditorPaneFrame.class);
        demos.put("Progress bars", progressBar.ProgressBarFrame.class);
        demos.put("Progress monitors", progressMonitor.ProgressMonitorFrame.class);
        demos.put("Progress monitor input streams", progressMonitorInputStream.TextFrame.class);
        demos.put("Split panes", splitPane.SplitPaneFrame.class);
        demos.put("Tabbed panes", tabbedPane.TabbedPaneFrame.class);
        demos.put("Desktop panes and internal frames", internalFrame.DesktopFrame.class);
        demos.put("Layers", layer.ColorFrame.class);
        demos.put("Shapes", shape.ShapeTestFrame.class);
        demos.put("Areas", area.AreaTestFrame.class);
        demos.put("Strokes", stroke.StrokeTestFrame.class);
        demos.put("Paint", paint.PaintTestFrame.class);
        demos.put("Coordinate transformations", transform.TransformationTestFrame.class);
        demos.put("Clipping", clip.ClippingTestFrame.class);
        demos.put("Transparency", transparency.TransparencyTestFrame.class);
        demos.put("Transparency and composition", composite.CompositeTestFrame.class);
        demos.put("Rendering hint", renderQuality.RenderQualityTestFrame.class);
        demos.put("Image I/O", imageIO.ImageIOFrame.class);
        demos.put("Mandelbrot set (Raster images)", rasterImage.RasterImageFrame.class);
        demos.put("Image processing", imageProcessing.ImageProcessingFrame.class);
        demos.put("Graphics printing", print.PrintTestFrame.class);
        demos.put("Multiple-page printing and preview", book.BookTestFrame.class);
        demos.put("Clipboard: transferring text", transferText.TextTransferFrame.class);
        demos.put("Clipboard: transferring image", imageTransfer.ImageTransferFrame.class);
        demos.put("Clipboard: transferring Java objects", serialTransfer.SerialTransferFrame.class);
        demos.put("Drag and Drop", dnd.SwingDnDFrame.class);
        demos.put("DnD image (Drag sources and drop targets)", dndImage.ImageListDnDFrame.class);
        demos.put("Launching sesktop applications", desktopApp.DesktopAppFrame.class);

        JLabel label = new JLabel(getTitle());
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(demos.size(), 1, 1, 1));
        demos.forEach(this::addButton);
        add(new JScrollPane(buttonPanel), BorderLayout.CENTER);

        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addButton(String name, Class<? extends JFrame> clazz) {
        JButton button = new JButton(name);
        button.addActionListener(event -> {
            try {
                JFrame frame = clazz.getDeclaredConstructor().newInstance();
                frame.setVisible(true);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        buttonPanel.add(button);
    }
}
