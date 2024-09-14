package com.miniproject.Bank.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable extends DatabaseConnection {
    public void AccountsTable() {
        String create_AccTable = "CREATE TABLE accounts("
                + "id INT PRIMARY KEY,"
                + "name VARCHAR(255) NOT NULL,"
                + "password VARCHAR(255) NOT NULL,"
                + " accountNumber BIGINT NOT NULL,"
                + " balance double not null"
                + ");";
        try {
            Connection Table_conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement table_stmt = Table_conn.createStatement();
        } catch (SQLException a) {
            System.out.println(a.getMessage());
        }

    }

    public void TransactionsTable() {
        String create_tranTable = "CREATE TABLE transactions("
                + " transaction_id INT primary key auto_increment,"
                + " sender_id int"
                + " sender_account_num bigint,"
                + " receiver_id"
                + " receiver_account_num bigint,"
                + " amount decimal(10,3),"
                + " transaction_time timestamp default current_timestamp,"
                + " foreign key (sender_account_num) references accounts(accountNumber),"
                + " foreign key(receiver_account_num) references accounts(accountNumber)"
                + ");";
        try {
            Connection Table_conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement table_stmt = Table_conn.createStatement();
        } catch (SQLException a) {
            System.out.println(a.getMessage());
        }
    }
}
