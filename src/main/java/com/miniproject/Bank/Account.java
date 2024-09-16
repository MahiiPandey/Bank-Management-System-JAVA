package com.miniproject.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.miniproject.Bank.DataBase.DatabaseConnection;

public class Account extends DatabaseConnection {
    private long accountNumber;
    private String accountName;
    private String password;
    private int strength;
    private double balance = 2000d;
    private int pin;

    public Account() {
    }

    // Parameterized constructor
    public Account(String accountName, String password, long accountNumber, double balance, int pin) {
        this.accountName = accountName;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public void setaccountNumber() {
        int sizeNumber = 14;
        Random rand = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sizeNumber; i++) {
            sb.append(rand.nextInt(10));
        }
        this.accountNumber = Long.parseLong(sb.toString());
        if (accountExists(accountNumber)) {
            setaccountNumber();
        }
    }

    public void setpin(int pin) {
        this.pin = pin;
    }

    public int getpin() {
        return pin;
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
            save();
            return 1;
        }
    }

    public void deposit(double amount) {
        balance += amount;
        save();
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

        if (password.matches(".*\\s.*")) {
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
                    int pin = rs.getInt("pin");

                    return new Account(name, password, accountNumber, balance, pin);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Account getAccount(long accountNumber) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM accounts WHERE accountNumber = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, accountNumber);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {

                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    double balance = rs.getDouble("balance");
                    int pin = rs.getInt("pin");

                    return new Account(name, password, accountNumber, balance, pin);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean accountExists(long accountNumber) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM accounts WHERE accountNumber = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, accountNumber);
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

    public static boolean accountExists(String name, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM accounts WHERE name = ? AND password = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, password);
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
            if (accountExists(this.accountName, this.password)) {
                String updateSql = "UPDATE accounts SET balance = ?, pin = ? WHERE name = ? AND password = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setDouble(1, this.balance);
                    updateStmt.setInt(2, this.pin);
                    updateStmt.setString(3, this.accountName);
                    updateStmt.setString(4, this.password);
                    updateStmt.executeUpdate();
                }
                System.out.println("Account updated successfully.");
            } else {
                String insertSql = "INSERT INTO accounts (name, password, accountNumber, balance, pin) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, this.accountName);
                    insertStmt.setString(2, this.password);
                    insertStmt.setLong(3, this.accountNumber);
                    insertStmt.setDouble(4, this.balance);
                    insertStmt.setInt(5, this.pin);
                    insertStmt.executeUpdate();
                }
                System.out.println("Account saved successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void transactionsave(long receiver_account_num, int amount) {
        try (Connection conn = DatabaseConnection.getConnection()) {

            String insertSql = "INSERT INTO transactions (sender_account_num, receiver_account_num, amount) VALUES (?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setLong(1, this.accountNumber);
                insertStmt.setLong(2, receiver_account_num);
                insertStmt.setInt(3, amount);
                insertStmt.executeUpdate();

                updateBalance(accountNumber, -amount); // Debit sender
                updateBalance(receiver_account_num, amount); // Credit receiver
            }

            System.out.println("Transaction info saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBalance(long id, double Amount) {
        try (Connection conn = DatabaseConnection.getConnection()) {

            String query = "UPDATE accounts SET balance = balance + ? WHERE id = ?";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setDouble(1, Amount);
                ps.setLong(2, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static int deleteAccount(long accountNumber) {
        String deleteSql = "DELETE FROM accounts WHERE accountNumber = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {

            pstmt.setLong(1, accountNumber);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Account deleted successfully.");
                return 1;
            } else {
                System.out.println("Account not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while deleting the account.");
        }
        return 0;
    }
}
