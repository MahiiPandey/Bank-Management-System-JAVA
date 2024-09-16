package com.miniproject.Bank.GUI;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import com.miniproject.Bank.Account;

public class TransactionFrame {

    public TransactionFrame(Account ac) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(6, 1));

        JTextField receiverAccountnumber = new JTextField();
        JTextField amountval = new JTextField();
        JLabel messageLabel = new JLabel();
        JPasswordField pf = new JPasswordField();

        JButton createButton = new JButton("Send");
        createButton.addActionListener(e -> {

            long receiver = Long.parseLong(receiverAccountnumber.getText());
            int amount = Integer.parseInt(amountval.getText());

            Account raccount = Account.getAccount(receiver);

            if (raccount == null) {
                messageLabel.setText("Receiver Account number doesn't exist");
            } else {
                if (ac.getpin() == 0) {
                    JOptionPane.showMessageDialog(null, "Set pin first ", null, 0);
                    frame.dispose();
                    new LoginFrame();
                    return;
                }
                int option = JOptionPane.showConfirmDialog(null, pf, "Enter pin", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                if (option == 0) {
                    int pin = Integer.parseInt(new String(pf.getPassword()));
                    if (pin != ac.getpin()) {
                        JOptionPane.showMessageDialog(createButton, "Invalid pin !!", null, JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (amount > ac.getBalance()) {
                            JOptionPane.showMessageDialog(frame, "Insufficient Balance!");
                        } else {
                            ac.withdraw(amount);
                            raccount.deposit(amount);
                            ac.transactionsave(receiver, amount);
                            frame.dispose();
                            new MainFrame();
                        }
                    }
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new MainFrame();
        });

        panel.add(new JLabel("Enter Receiver Account Number:"));
        panel.add(receiverAccountnumber);
        panel.add(new JLabel("Enter Amount:"));
        panel.add(amountval);
        panel.add(createButton);
        panel.add(backButton);
        panel.add(messageLabel);

        frame.add(panel);
        frame.setVisible(true);
    }
}