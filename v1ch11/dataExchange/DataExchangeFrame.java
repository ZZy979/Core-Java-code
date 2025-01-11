package dataExchange;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A frame with a menu whose File->Connect action shows a password dialog.
 */
public class DataExchangeFrame extends JFrame {
    public static final int TEXT_ROWS = 20;
    public static final int TEXT_COLUMNS = 40;
    private PasswordChooser dialog = null;
    private JTextArea textArea;

    public DataExchangeFrame() {
        setTitle("DataExchangeTest");

        // construct a File menu
        var menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        var fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // add Connect and Exit menu items
        var connectItem = new JMenuItem("Connect");
        connectItem.addActionListener(new ConnectAction());
        fileMenu.add(connectItem);

        // the Exit item exits the program
        var exitItem = new JMenuItem("Exit");
        // exitItem.addActionListener(event -> System.exit(0));
        exitItem.addActionListener(event -> DataExchangeFrame.this.dispose());
        fileMenu.add(exitItem);

        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        pack();
    }

    /**
     * The Connect action pops up the password dialog.
     */
    private class ConnectAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // if first time, construct dialog
            if (dialog == null)
                dialog = new PasswordChooser();

            // set default values
            dialog.setUser(new User("yourname", null));

            // pop up dialog
            if (dialog.showDialog(DataExchangeFrame.this, "Connect")) {
                // if accepted, retrieve user input
                User u = dialog.getUser();
                textArea.append("username = " + u.getName() + ", password = " + new String(u.getPassword()) + "\n");
            }
        }
    }
}
