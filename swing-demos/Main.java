import java.awt.EventQueue;

/**
 * Main class of Swing Demos Collection
 * @author ZZy
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
