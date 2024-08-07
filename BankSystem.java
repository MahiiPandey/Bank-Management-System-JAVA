import Bank.Account;
import java.util.Scanner;

public class BankSystem {

    public static void main(String[] args) {

        System.out.println("Welcome to Bank System");
        System.out.println("----------------------------------");
        System.out.println();

        Account ac = new Account();
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
