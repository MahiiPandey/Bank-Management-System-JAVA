package GUI;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Account;

public class CreateAccounntFrame {

    public CreateAccounntFrame() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(6, 1));

        JTextField nameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel messageLabel = new JLabel();

        JButton createButton = new JButton("Create");
        createButton.addActionListener(e -> {

            String name = nameField.getText();
            String password = new String(passwordField.getPassword());

            Account account = new Account();

            int option = JOptionPane.showConfirmDialog(frame,
                    account.checkPassword(password) + "\nDo you want to continue?",
                    "Confirm Account Creation",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {

                if (Account.accountExists(name)) {
                    JOptionPane.showMessageDialog(panel, "Account with this name already exists. Please login.");
                    frame.dispose();
                    new MainFrame();
                } else {
                    account.createAccount(name);
                    account.setPassword(password);
                    account.save();
                    messageLabel.setText("Account created successfully!\n");
                    JOptionPane.showMessageDialog(panel, "Your Account Number is : " + account.getAccountNumber());
                }
            } else {
                messageLabel.setText("Account creation cancelled.");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new MainFrame();
        });

        panel.add(new JLabel("Enter Account Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Enter Password:"));
        panel.add(passwordField);
        panel.add(createButton);
        panel.add(backButton);
        panel.add(messageLabel);

        frame.add(panel);
        frame.setVisible(true);
    }
}
