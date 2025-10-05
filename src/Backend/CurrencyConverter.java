package Backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Backend.ExchangeRates.exchangeRates;

public class CurrencyConverter extends JFrame {

    // GUI Components
    private JComboBox<String> fromCurrencyBox;
    private JComboBox<String> toCurrencyBox;
    private JTextField amountField;
    private JLabel resultLabel;

    public CurrencyConverter() {

        setTitle("Currency Converter");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns, with gaps
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        String[] currencies = exchangeRates.keySet().toArray(new String[0]);
        fromCurrencyBox = new JComboBox<>(currencies);
        toCurrencyBox = new JComboBox<>(currencies);
        amountField = new JTextField();
        resultLabel = new JLabel("Result will be shown here");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JButton convertButton = new JButton("Convert");
        JButton swapButton = new JButton("Swap");

        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel("From:"));
        panel.add(fromCurrencyBox);
        panel.add(new JLabel("To:"));
        panel.add(toCurrencyBox);
        panel.add(swapButton);
        panel.add(new JLabel());
        panel.add(convertButton);
        panel.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        swapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapCurrencies();
            }
        });

        add(panel);
    }

    private void convertCurrency() {
        try {
            // 1. Get user input
            String amountStr = amountField.getText();
            double amount = Double.parseDouble(amountStr);

            String fromCurrency = (String) fromCurrencyBox.getSelectedItem();
            String toCurrency = (String) toCurrencyBox.getSelectedItem();

            // 2. Convert the amount to USD (our base currency)
            double amountInUSD = amount / exchangeRates.get(fromCurrency);

            // 3. Convert from USD to the target currency
            double convertedAmount = amountInUSD * exchangeRates.get(toCurrency);

            // 4. Display the result, formatted to 2 decimal places
            resultLabel.setText(String.format("%.2f %s", convertedAmount, toCurrency));

        } catch (NumberFormatException ex) {
            // Handle invalid number input
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            // Handle other potential errors
            JOptionPane.showMessageDialog(this, "An error occurred during conversion.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void swapCurrencies() {
        int fromIndex = fromCurrencyBox.getSelectedIndex();
        int toIndex = toCurrencyBox.getSelectedIndex();

        fromCurrencyBox.setSelectedIndex(toIndex);
        toCurrencyBox.setSelectedIndex(fromIndex);
    }
}
