package com.miniproject;

import com.miniproject.Bank.DataBase.CreateTable;
import com.miniproject.Bank.GUI.MainFrame;

public class BankSystem {

    public static void main(String[] args) {
        CreateTable c = new CreateTable();
        c.AccountsTable();
        c.TransactionsTable();
        new MainFrame();
    }
}