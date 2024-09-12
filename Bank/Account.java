package Bank;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Account {
    private long AccountNumber;
    private String AccountName;
    private String password;
    private int strength;
    private double balance = 2000d;
    private double annualInterestRate;
    private String dateCreated;

    public void setAccountNumber() {
        int size_number = 14; // 14 digit number
        Random rand = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size_number; i++) {
            sb.append(rand.nextInt(10));
        }
        AccountNumber = Long.parseLong(sb.toString());
    }

    public long getAccountNumber() {
        return AccountNumber;
    }

    public double getbalance() {
        return balance;
    }

    public void setPassword(String password) {
        checkPassword(password);
        this.password = password;
    }

    public void AccountCreate(String Accountname) {
        this.AccountName = Accountname;
        setAccountNumber();
        System.out.println("Your Account number is " + AccountNumber);
    }

    public int Withdraw(double n) {
        if (n > balance) {
            return 0;
        } else {
            balance -= n;
            return 1;
        }
    }

    public void Deposit(Double n) {
        balance += n;
    }

    public String checkPassword(String password) {
        if (password.matches(".*\\d.*")) {
            strength++;
        }

        if (password.matches(".*[a-z].*")) {
            strength++;
        }

        if (password.matches(".*[A-Z].*")) {
            strength++;
        }

        if (password.matches(".*[!@#$%^&*()\\-+].*")) {
            strength++;
        }

        if (password.trim().isEmpty()) {
            System.out.println("Password cannot be blank or only spaces.");
        } else if (password.matches(".*\\s.*")) {
            System.out.println("Password contains blank spaces.");
        }

        if (strength <= 1) {
            return "Password level: Weak";
        } else if (strength == 2 || 3 == strength) {
            return "Password level: Medium";
        } else {
            return "Password level: Strong";
        }
    }
}
