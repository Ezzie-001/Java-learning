public class SavingsAccount extends BankAccount {
    double interestRate;

    SavingsAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = "Savings";
        this.interestRate = 0.03;
    }

    double calculateInterest() {
        if (balance <= 0) {
            return 0;
        }
        return Math.round(balance * interestRate * 100) / 100.0;
    }

    void printDetails() {
        System.out.printf("Account: %s | Holder: %s | Type: %s | Balance: $%.2f | Annual interest rate: %.0f%%",
                getMaskedAccountNumber(), accountHolderName, accountType,
                balance, interestRate * 100);
    }
}
