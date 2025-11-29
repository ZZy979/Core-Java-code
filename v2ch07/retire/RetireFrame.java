package retire;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gridbag.GBC;

public class RetireFrame extends JFrame {
    private JTextField savingsField = new JTextField(10);
    private JTextField contribField = new JTextField(10);
    private JTextField incomeField = new JTextField(10);
    private JTextField currentAgeField = new JTextField(4);
    private JTextField retireAgeField = new JTextField(4);
    private JTextField deathAgeField = new JTextField(4);
    private JTextField inflationPercentField = new JTextField(6);
    private JTextField investPercentField = new JTextField(6);
    private JTextArea retireText = new JTextArea(10, 25);
    private RetireComponent retireCanvas = new RetireComponent();
    private JButton computeButton = new JButton();
    private JLabel languageLabel = new JLabel();
    private JLabel savingsLabel = new JLabel();
    private JLabel contribLabel = new JLabel();
    private JLabel incomeLabel = new JLabel();
    private JLabel currentAgeLabel = new JLabel();
    private JLabel retireAgeLabel = new JLabel();
    private JLabel deathAgeLabel = new JLabel();
    private JLabel inflationPercentLabel = new JLabel();
    private JLabel investPercentLabel = new JLabel();

    private RetireInfo info = new RetireInfo();
    private Locale[] locales = {Locale.US, Locale.CHINA, Locale.GERMANY};
    private Locale currentLocale;
    private JComboBox<Locale> localeCombo = new LocaleCombo(locales);

    private ResourceBundle res;
    private ResourceBundle resStrings;
    private NumberFormat currencyFmt;
    private NumberFormat numberFmt;
    private NumberFormat percentFmt;

    public RetireFrame() {
        setTitle("Retirement Calculator");
        setLayout(new GridBagLayout());
        add(languageLabel, new GBC(0, 0).setAnchor(GBC.EAST));
        add(savingsLabel, new GBC(0, 1).setAnchor(GBC.EAST));
        add(contribLabel, new GBC(2, 1).setAnchor(GBC.EAST));
        add(incomeLabel, new GBC(4, 1).setAnchor(GBC.EAST));
        add(currentAgeLabel, new GBC(0, 2).setAnchor(GBC.EAST));
        add(retireAgeLabel, new GBC(2, 2).setAnchor(GBC.EAST));
        add(deathAgeLabel, new GBC(4, 2).setAnchor(GBC.EAST));
        add(inflationPercentLabel, new GBC(0, 3).setAnchor(GBC.EAST));
        add(investPercentLabel, new GBC(2, 3).setAnchor(GBC.EAST));
        add(localeCombo, new GBC(1, 0, 3, 1));
        add(savingsField, new GBC(1, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(contribField, new GBC(3, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(incomeField, new GBC(5, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(currentAgeField, new GBC(1, 2).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(retireAgeField, new GBC(3, 2).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(deathAgeField, new GBC(5, 2).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(inflationPercentField, new GBC(1, 3).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(investPercentField, new GBC(3, 3).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(retireCanvas, new GBC(0, 4, 4, 1).setWeight(100, 100).setFill(GBC.BOTH));
        add(new JScrollPane(retireText), new GBC(4, 4, 2, 1).setWeight(0, 100).setFill(GBC.BOTH));

        computeButton.setName("computeButton");
        computeButton.addActionListener(event -> {
            getInfo();
            updateData();
            updateGraph();
        });
        add(computeButton, new GBC(5, 3));

        retireText.setEditable(false);
        retireText.setFont(new Font("Monospaced", Font.PLAIN, 10));

        info.setSavings(0);
        info.setContrib(9000);
        info.setIncome(60000);
        info.setCurrentAge(35);
        info.setRetireAge(65);
        info.setDeathAge(85);
        info.setInvestPercent(0.1);
        info.setInflationPercent(0.05);

        int localeIndex = 0;  // US locale is default selection
        for (int i = 0; i < locales.length; i++) {
            // if current locale one of the choices, select it
            if (getLocale().equals(locales[i]))
                localeIndex = i;
        }
        setCurrentLocale(locales[localeIndex]);

        localeCombo.addActionListener(event -> {
            setCurrentLocale((Locale) localeCombo.getSelectedItem());
            validate();
        });

        pack();
    }

    /**
     * Sets the current locale.
     * @param locale the desired locale
     */
    public void setCurrentLocale(Locale locale) {
        currentLocale = locale;
        localeCombo.setLocale(currentLocale);
        localeCombo.setSelectedItem(currentLocale);

        res = ResourceBundle.getBundle("retire.RetireResources", currentLocale);
        resStrings = ResourceBundle.getBundle("retire.RetireStrings", currentLocale);
        currencyFmt = NumberFormat.getCurrencyInstance(currentLocale);
        numberFmt = NumberFormat.getNumberInstance(currentLocale);
        percentFmt = NumberFormat.getPercentInstance(currentLocale);

        updateDisplay();
        updateInfo();
        updateData();
        updateGraph();
    }

    /**
     * Updates all labels in the display.
     */
    public void updateDisplay() {
        languageLabel.setText(resStrings.getString("language"));
        savingsLabel.setText(resStrings.getString("savings"));
        contribLabel.setText(resStrings.getString("contrib"));
        incomeLabel.setText(resStrings.getString("income"));
        currentAgeLabel.setText(resStrings.getString("currentAge"));
        retireAgeLabel.setText(resStrings.getString("retireAge"));
        deathAgeLabel.setText(resStrings.getString("deathAge"));
        inflationPercentLabel.setText(resStrings.getString("inflationPercent"));
        investPercentLabel.setText(resStrings.getString("investPercent"));
        computeButton.setText(resStrings.getString("computeButton"));
    }

    /**
     * Updates the information in the text fields.
     */
    public void updateInfo() {
        savingsField.setText(currencyFmt.format(info.getSavings()));
        contribField.setText(currencyFmt.format(info.getContrib()));
        incomeField.setText(currencyFmt.format(info.getIncome()));
        currentAgeField.setText(numberFmt.format(info.getCurrentAge()));
        retireAgeField.setText(numberFmt.format(info.getRetireAge()));
        deathAgeField.setText(numberFmt.format(info.getDeathAge()));
        investPercentField.setText(percentFmt.format(info.getInvestPercent()));
        inflationPercentField.setText(percentFmt.format(info.getInflationPercent()));
    }

    /**
     * Updates the data displayed in the text area.
     */
    public void updateData() {
        retireText.setText("");
        var retireMsg = new MessageFormat("");
        retireMsg.setLocale(currentLocale);
        retireMsg.applyPattern(resStrings.getString("retire"));

        for (int i = info.getCurrentAge(); i <= info.getDeathAge(); i++) {
            Object[] args = {i, info.getBalance(i)};
            retireText.append(retireMsg.format(args) + "\n");
        }
    }

    /**
     * Updates the graph.
     */
    public void updateGraph() {
        retireCanvas.setColorPre((Color) res.getObject("colorPre"));
        retireCanvas.setColorGain((Color) res.getObject("colorGain"));
        retireCanvas.setColorLoss((Color) res.getObject("colorLoss"));
        retireCanvas.setInfo(info);
        repaint();
    }

    /**
     * Reads the user input from the text fields.
     */
    public void getInfo() {
        try {
            info.setSavings(currencyFmt.parse(savingsField.getText()).doubleValue());
            info.setContrib(currencyFmt.parse(contribField.getText()).doubleValue());
            info.setIncome(currencyFmt.parse(incomeField.getText()).doubleValue());
            info.setCurrentAge(numberFmt.parse(currentAgeField.getText()).intValue());
            info.setRetireAge(numberFmt.parse(retireAgeField.getText()).intValue());
            info.setDeathAge(numberFmt.parse(deathAgeField.getText()).intValue());
            info.setInvestPercent(percentFmt.parse(investPercentField.getText()).doubleValue());
            info.setInflationPercent(percentFmt.parse(inflationPercentField.getText()).doubleValue());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
