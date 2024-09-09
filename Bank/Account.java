package Bank;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Account {
    private long AccountNumber;
    private String AccountName;
    private int pin;
    private double balance;
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

    public void AccountCreate(String Accountname) {
        this.AccountName = Accountname;
        setDate();
        setAccountNumber();
        System.out.println("Your Account number is " + AccountNumber);
    }

    public void setPin() {
        String p;
        Scanner sc = new Scanner(System.in);
        p = sc.nextLine();
        if (p.length() == 4)
            this.pin = Integer.parseInt(p);
        else {
            System.out.println("Set 4 digit pin");
            setPin();
        }
    }

    public double getMonthlyInterestRate() {
        return (annualInterestRate / 12);
    }

    public double getMonthlyInterest() {
        return balance * (getMonthlyInterest() / 100.0);
    }

    public void Withdraw() {
        System.out.println("Available Balance : " + balance);
        System.out.println("Enter amount that you want to withdraw : ");

        Scanner sc = new Scanner(System.in);
        Double n = sc.nextDouble();

        if (n > balance) {
            System.out.println("ERROR not enough balance ");
        } else {
            balance -= n;
        }

        System.out.println("Available Balance : " + balance);

    }

    public void Deposit() {

        System.out.println("Available Balance : " + balance);
        System.out.println("Enter amount that you want to deposit : ");

        Scanner sc = new Scanner(System.in);
        Double n = sc.nextDouble();

        balance += n;
        System.out.println("Available Balance : " + balance);

    }

    public void setDate() {
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
        dateCreated = ft.format(new Date());
    }

    public void getInfo() {
        System.out.println("Name : " + AccountName);
        System.out.println("Balance : " + balance);
    }
}
