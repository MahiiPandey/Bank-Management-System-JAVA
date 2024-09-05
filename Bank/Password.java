package Bank;

public class Password {
    private String password;
    private int strength;

    public void setPassword(String password) {
        this.password = password;
    }

    public String checkPassword(String password) {
        if (password.matches(".*\\d.*")) {
            strength++;
        }

        // Regex for at least one lowercase letter
        if (password.matches(".*[a-z].*")) {
            strength++;
        }

        // Regex for at least one uppercase letter
        if (password.matches(".*[A-Z].*")) {
            strength++;
        }

        // Regex for at least one special character (you can add more special chars if
        // needed)
        if (password.matches(".*[!@#$%^&*()\\-+].*")) {
            strength++;
        }
        if (password.trim().isEmpty()) {
            System.out.println("Password cannot be blank or only spaces.");
        } else if (password.matches(".*\\s.*")) {
            System.out.println("Password contains blank spaces.");
        }
        
        // Determine password strength based on score
        if (strength <= 1) {
            return "Weak";
        } else if (strength == 2 || 3 == strength) {
            return "Medium";
        } else {
            return "Strong";
        }
    }
}
