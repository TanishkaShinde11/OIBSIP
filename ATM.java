import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM extends JFrame {
    private static final String DEFAULT_USER_ID = "123";
    private static final String DEFAULT_PIN = "456";
    private static double accountBalance = 1000;

    public ATM() {
        setTitle("ATM System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User ID:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(10, 50, 80, 25);
        panel.add(pinLabel);

        JPasswordField pinText = new JPasswordField(20);
        pinText.setBounds(100, 50, 165, 25);
        panel.add(pinText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = userText.getText();
                String pin = new String(pinText.getPassword());

                if (authenticateUser(userId, pin)) {
                    JOptionPane.showMessageDialog(null, "Authentication successful. Welcome, " + userId + "!");
                    performTransactions();
                } else {
                    JOptionPane.showMessageDialog(null, "Authentication failed. Exiting...");
                    System.exit(0);
                }
            }
        });
    }

    private boolean authenticateUser(String userId, String pin) {
        return userId.equals(DEFAULT_USER_ID) && pin.equals(DEFAULT_PIN);
    }

    private void performTransactions() {
        JFrame frame = new JFrame("ATM Menu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeTransactionComponents(panel);

        frame.setVisible(true);
    }

    private void placeTransactionComponents(JPanel panel) {
        panel.setLayout(null);

        JButton historyButton = new JButton("Transactions History");
        historyButton.setBounds(10, 20, 160, 25);
        panel.add(historyButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(10, 50, 160, 25);
        panel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(10, 80, 160, 25);
        panel.add(depositButton);

        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(10, 110, 160, 25);
        panel.add(transferButton);

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(10, 140, 160, 25);
        panel.add(quitButton);

        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Transaction History: (Not implemented in this example)");
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performWithdrawal();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performDeposit();
            }
        });

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performTransfer();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Thank you for using the ATM. Goodbye!");
                System.exit(0);
            }
        });
    }

    private void performWithdrawal() {
        String amountStr = JOptionPane.showInputDialog("Enter the withdrawal amount:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount > 0 && amount <= accountBalance) {
                accountBalance -= amount;
                JOptionPane.showMessageDialog(null, "Withdrawal successful. Remaining balance: $" + accountBalance);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid amount or insufficient funds.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
        }
    }

    private void performDeposit() {
        String amountStr = JOptionPane.showInputDialog("Enter the deposit amount:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount > 0) {
                accountBalance += amount;
                JOptionPane.showMessageDialog(null, "Deposit successful. New balance: $" + accountBalance);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid amount.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
        }
    }

    private void performTransfer() {
        String amountStr = JOptionPane.showInputDialog("Enter the transfer amount:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount > 0 && amount <= accountBalance) {
                String recipientAccount = JOptionPane.showInputDialog("Enter the recipient's account number:");
                JOptionPane.showMessageDialog(null, "Transfer of $" + amount + " to account " +
                        recipientAccount + " successful.\nRemaining balance: $" + accountBalance);
                accountBalance -= amount;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid amount or insufficient funds.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        new ATM();
    }
}

