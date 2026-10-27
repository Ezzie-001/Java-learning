public class CurrentAccount extends BankAccount {
    double overdraftLimit;

    CurrentAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = "Current";
        this.overdraftLimit = 500.00;
    }

    boolean canWithdraw(double amount) {
        return amount > 0 && balance + overdraftLimit >= amount;
    }

    void printDetails() {
        System.out.printf("Account: %s | Holder: %s | Type: %s | Balance: $%.2f | Overdraft limit: $%.2f",
                getMaskedAccountNumber(), accountHolderName, accountType,
                balance, overdraftLimit);
    }
}
