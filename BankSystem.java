import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class BankSystem {

    public static void main(String[] args) {

        System.out.println("Welcome to Bank System");
        System.out.println("----------------------------------");
        System.out.println();

        Account ac = new Account(1, 3000);
        ac.setDate();

        while (true) {
            System.out.println("Enter 1 : Withdraw Amount");
            System.out.println("Enter 2 : Deposit Amount");
            System.out.println("Enter 3 : exit");

            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();

            if (n == 1) {
                System.out.println("----------------------------------");
                System.out.println();
                ac.Withdraw();
            }

            else if (n == 2) {
                System.out.println("----------------------------------");
                System.out.println();
                ac.Deposit();
            }

            else if (n == 3) {
                break;
            } else {
                System.out.println("Enter Valid number");
            }

        }

    }
}

class Account {
    private int id;
    private double balance;
    private double annualInterestRate;
    private String dateCreated;

    public Account() {
    }

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public void get() {
        System.out.println("ID : " + id);
        System.out.println("Balance : " + balance);
        System.out.println("Annual Interest Rate : " + annualInterestRate);
    }

    public double getMonthlyInterestRate() {
        return (annualInterestRate / 12);
    }

    public double getMonthlyInterest() {
        return balance * (getMonthlyInterest() / 100);
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
}
