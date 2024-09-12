package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import Bank.DataBase.DatabaseConnection;

public class Account {
    private long accountNumber;
    private String accountName;
    private String password;
    private int strength;
    private double balance = 2000d;

    // Default constructor
    public Account() {
    }

    // Parameterized constructor
    public Account(String accountName, String password, long accountNumber, double balance) {
        this.accountName = accountName;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void setaccountNumber() {
        int sizeNumber = 14;
        Random rand = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sizeNumber; i++) {
            sb.append(rand.nextInt(10));
        }
        this.accountNumber = Long.parseLong(sb.toString());
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void createAccount(String accountName) {
        this.accountName = accountName;
        setaccountNumber();
    }

    public int withdraw(double amount) {
        if (amount > balance) {
            return 0;
        } else {
            balance -= amount;
            return 1;
        }
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public String checkPassword(String password) {

        this.strength = 0;

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
            return "Password cannot be blank or only spaces.";
        } else if (password.matches(".*\\s.*")) {
            return "Password contains blank spaces.";
        }

        if (strength <= 1) {
            return "Password level: Weak";
        } else if (strength == 2 || strength == 3) {
            return "Password level: Medium";
        } else {
            return "Password level: Strong";
        }
    }

    public static Account getAccount(String name, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM accounts WHERE name = ? AND password = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {

                    long accountNumber = rs.getLong("accountNumber");
                    double balance = rs.getDouble("balance");

                    return new Account(name, password, accountNumber, balance);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean accountExists(String name) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM accounts WHERE name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void save() {
        try (Connection conn = DatabaseConnection.getConnection()) {

            if (accountExists(this.accountName)) {
                return;
            }

            String insertSql = "INSERT INTO accounts (name, password, accountNumber, balance) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, this.accountName);
                insertStmt.setString(2, this.password);
                insertStmt.setLong(3, this.accountNumber);
                insertStmt.setDouble(4, this.balance);
                insertStmt.executeUpdate();
            }

            System.out.println("Account saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
