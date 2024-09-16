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

    public TransactionFrame() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(6, 1));

        JTextField senderAccountnumber = new JTextField();
        JTextField receiverAccountnumber = new JTextField();
        JLabel messageLabel = new JLabel();
        JPasswordField pf = new JPasswordField();

        JButton createButton = new JButton("Send");
        createButton.addActionListener(e -> {

            long sender = Long.parseLong(senderAccountnumber.getText());
            long receiver = Long.parseLong(receiverAccountnumber.getText());

            Account saccount = Account.getAccount(sender);
            Account raccount = Account.getAccount(receiver);

            if (saccount == null) {
                messageLabel.setText("Sender Account number doesn't exist");
            } else if (raccount == null) {
                messageLabel.setText("Receiver Account number doesn't exist");
            } else {
                int option = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (option == 0) {
                    int password = Integer.parseInt(new String(pf.getPassword()));
                    if (password != saccount.getpin()) {
                        JOptionPane.showMessageDialog(createButton, "Invalid pin !!", null, JOptionPane.ERROR_MESSAGE);
                    } else {
                        
                    }
                }
            }

        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new MainFrame();
        });

        panel.add(new JLabel("Enter Sender Account Number:"));
        panel.add(senderAccountnumber);
        panel.add(new JLabel("Enter Receiver Account Number:"));
        panel.add(receiverAccountnumber);
        panel.add(createButton);
        panel.add(backButton);
        panel.add(messageLabel);

        frame.add(panel);
        frame.setVisible(true);
    }
}
