package Bank;

import java.util.Random;

public class Account {
    private long AccountNumber;
    private String pin;

    public void setAccountNumber() {
        int size_number = 14; // 14 digit number
        Random rand = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size_number; i++) {
            sb.append(rand.nextInt(10));
        }
        AccountNumber = Long.parseLong(sb.toString());
    }

}
