public class BankTransaction {
    String accountNumber;
    String transactionType;
    double amount;
    String otherAccountNumber;

    BankTransaction(String accountNumber, String transactionType,
                    double amount, String otherAccountNumber) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.otherAccountNumber = otherAccountNumber;
    }

    String maskAccountNumber(String number) {
        if (number.length() <= 4) {
            String masked = "";
            for (int i = 0; i < number.length(); i++) {
                masked += "*";
            }
            return masked;
        }

        String masked = "";
        for (int i = 0; i < number.length() - 4; i++) {
            masked += "*";
        }
        return masked + number.substring(number.length() - 4);
    }

    void printDetails() {
        System.out.printf("%s | $%.2f", transactionType, amount);
        if (!otherAccountNumber.isEmpty()) {
            System.out.printf(" | Other account: %s", maskAccountNumber(otherAccountNumber));
        }
    }
}
