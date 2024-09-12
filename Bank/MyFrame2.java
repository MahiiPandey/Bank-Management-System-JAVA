package Bank;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.GridLayout;

public class MyFrame2 {
    public MyFrame2() {

        JButton withdrawbutton = new JButton();
        withdrawbutton.setText("Withdraw Amount");
        withdrawbutton.setFocusable(false);

        withdrawbutton.addActionListener(e -> {

        });

        JButton depositbutton = new JButton();
        depositbutton.setText("Deposit Amount");
        depositbutton.setFocusable(false);

        JButton checkbalance = new JButton();
        checkbalance.setText("Check Balance");
        checkbalance.setFocusable(false);

        JButton backbutton = new JButton();
        backbutton.setText("Back");
        backbutton.setFocusable(false);

        backbutton.addActionListener(e -> {
            System.exit(0);
        });

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setTitle("Bank Management System");

        frame.setLayout(new GridLayout(4, 1));

        frame.add(withdrawbutton);
        frame.add(depositbutton);
        frame.add(backbutton);

        frame.setVisible(true);
    }
}