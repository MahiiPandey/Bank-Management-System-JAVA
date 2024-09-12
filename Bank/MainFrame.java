package Bank;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;

public class MainFrame {

    public MainFrame() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setTitle("Bank Management System");

        JButton createAccountbutton = new JButton();
        createAccountbutton.setText("Create Account");
        createAccountbutton.setFocusable(false);

        createAccountbutton.addActionListener(e -> {
            new CreateAccounntFrame();
            frame.dispose();
        });

        JButton loginbutton = new JButton();
        loginbutton.setText("Log in");
        loginbutton.setFocusable(false);

        loginbutton.addActionListener(e -> {
            new LoginFrame();
            frame.dispose();
        });

        JButton exitbutton = new JButton();
        exitbutton.setText("Exit");
        exitbutton.setFocusable(false);

        exitbutton.addActionListener(e -> {
            System.exit(0);
        });

        frame.setLayout(new GridLayout(3, 1));

        frame.add(createAccountbutton);
        frame.add(loginbutton);
        frame.add(exitbutton);

        frame.setVisible(true);
    }
}
