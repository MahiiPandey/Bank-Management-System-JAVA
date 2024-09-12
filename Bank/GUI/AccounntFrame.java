package Bank.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Bank.Account;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.GridLayout;

public class AccounntFrame {

    public AccounntFrame(Account ac) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setTitle("Bank Management System");

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton withdrawbutton = new JButton("Withdraw Amount");
        withdrawbutton.setFocusable(false);
        withdrawbutton.addActionListener(e -> {
            Double d = Double.parseDouble(JOptionPane.showInputDialog("Amount of withdraw"));
            int i = ac.Withdraw(d);
            if (i == 0) {
                JOptionPane.showMessageDialog(frame, "Insufficient Balance!");
            }
        });

        JButton depositbutton = new JButton("Deposit Amount");
        depositbutton.setFocusable(false);
        depositbutton.addActionListener(e -> {
            Double d = Double.parseDouble(JOptionPane.showInputDialog("Amount of deposit"));
            ac.Deposit(d);
        });

        JButton checkbalance = new JButton("Check Balance");
        checkbalance.setFocusable(false);
        checkbalance.addActionListener(e -> {
            label.setText("Current Balance : " + String.valueOf(ac.getbalance()));
        });

        JButton backbutton = new JButton("Back");
        backbutton.setFocusable(false);
        backbutton.addActionListener(e -> {
            frame.dispose();
            new MainFrame();
        });

        panel.add(withdrawbutton);
        panel.add(depositbutton);
        panel.add(checkbalance);
        panel.add(backbutton);
        panel.add(label);

        frame.add(panel);

        frame.setVisible(true);
    }
}
