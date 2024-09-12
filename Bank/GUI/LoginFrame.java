package Bank.GUI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Bank.Account;

public class LoginFrame {
    public LoginFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(6, 1));

        JTextField nameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel messageLabel = new JLabel();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());

            // if (accounts.containsKey(name)) {
            Account account = accounts.get(name);
            if (account.checkPassword(password)) {
                currentAccount = account;
                messageLabel.setText("Login successful!");

                frame.dispose();
                new AccounntFrame(account);

            } else {
                messageLabel.setText("Invalid password. Try again.");
            }
            // } else {
            // messageLabel.setText("No account found with this name.");
            // }
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
        panel.add(loginButton);
        panel.add(backButton);
        panel.add(messageLabel);

        frame.add(panel);
        frame.setVisible(true);
    }
}
