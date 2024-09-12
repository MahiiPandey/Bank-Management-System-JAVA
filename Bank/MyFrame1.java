package Bank;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;

public class MyFrame1 {
    public MyFrame1() {
        JButton createAccountbutton = new JButton();
        createAccountbutton.setText("Create Account");
        createAccountbutton.setFocusable(false);

        createAccountbutton.addActionListener(e -> {
            Account ac = new Account();
            new MyFrame2();
        });

        JButton loginbutton = new JButton();
        loginbutton.setText("Log in");
        loginbutton.setFocusable(false);

        JButton exitbutton = new JButton();
        exitbutton.setText("Exit");
        exitbutton.setFocusable(false);

        exitbutton.addActionListener(e -> {
            System.exit(0);
        });

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setTitle("Bank Management System");

        frame.setLayout(new GridLayout(3, 1));

        frame.add(createAccountbutton);
        frame.add(loginbutton);
        frame.add(exitbutton);

        frame.setVisible(true);
    }
}
