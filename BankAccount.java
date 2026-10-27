public class BankAccount {
    String accountNumber;
    String accountHolderName;
    double balance;
    String accountType;

    BankAccount() {
    }

    boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    boolean canWithdraw(double amount) {
        return amount > 0 && balance >= amount;
    }

    boolean withdraw(double amount) {
        if (!canWithdraw(amount)) {
            return false;
        }
        balance -= amount;
        return true;
    }

    double calculateInterest() {
        return 0;
    }

    String getMaskedAccountNumber() {
        if (accountNumber.length() <= 4) {
            String masked = "";
            for (int i = 0; i < accountNumber.length(); i++) {
                masked += "*";
            }
            return masked;
        }

        String masked = "";
        for (int i = 0; i < accountNumber.length() - 4; i++) {
            masked += "*";
        }
        return masked + accountNumber.substring(accountNumber.length() - 4);
    }

    void printDetails() {
        System.out.printf("Account: %s | Holder: %s | Type: %s | Balance: $%.2f",
                getMaskedAccountNumber(), accountHolderName, accountType, balance);
    }
}
