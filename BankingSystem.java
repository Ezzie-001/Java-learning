import java.util.Scanner;

public class BankingSystem {
    static Scanner input = new Scanner(System.in);
    static BankAccount[] accounts = new BankAccount[100];
    static BankTransaction[] transactions = new BankTransaction[1000];
    static int accountCount = 0;
    static int transactionCount = 0;

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            showMenu();
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> openAccount();
                case 2 -> showAllAccounts();
                case 3 -> searchAccount();
                case 4 -> depositMoney();
                case 5 -> withdrawMoney();
                case 6 -> transferMoney();
                case 7 -> checkBalance();
                case 8 -> showTransactionSummary();
                case 9 -> showBankReport();
                case 10 -> applySavingsInterest();
                case 11 -> {
                    isRunning = false;
                    System.out.println("Goodbye.");
                }
                default -> System.out.println("Choose a number from 1 to 11.");
            }
        }
    }

    static void showMenu() {
        System.out.println("\n=== Banking and Account Management System ===");
        System.out.println("1. Open an account");
        System.out.println("2. View all accounts");
        System.out.println("3. Search for an account");
        System.out.println("4. Deposit money");
        System.out.println("5. Withdraw money");
        System.out.println("6. Transfer funds");
        System.out.println("7. Check account balance");
        System.out.println("8. View transaction summary");
        System.out.println("9. Bank report");
        System.out.println("10. Apply savings interest");
        System.out.println("11. Exit");
    }

    static void openAccount() {
        if (accountCount == accounts.length) {
            System.out.println("The account list is full.");
            return;
        }

        System.out.println("Account type: 1. Savings  2. Current");
        System.out.print("Choose an account type: ");
        int type = input.nextInt();
        input.nextLine();
        while (type < 1 || type > 2) {
            System.out.print("Enter 1 for Savings or 2 for Current: ");
            type = input.nextInt();
            input.nextLine();
        }

        System.out.print("Account number: ");
        String number = input.nextLine().trim();
        while (number.isEmpty()) {
            System.out.print("Account number cannot be empty: ");
            number = input.nextLine().trim();
        }
        if (findAccount(number) != -1) {
            System.out.println("An account with that number already exists.");
            return;
        }

        System.out.print("Account holder name: ");
        String name = input.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter it again: ");
            name = input.nextLine().trim();
        }

        System.out.print("Opening balance: $");
        double openingBalance = input.nextDouble();
        input.nextLine();
        while (openingBalance < 0) {
            System.out.print("Enter zero or a positive balance: $");
            openingBalance = input.nextDouble();
            input.nextLine();
        }

        if (type == 1) {
            accounts[accountCount] = new SavingsAccount(number, name, openingBalance);
        } else {
            accounts[accountCount] = new CurrentAccount(number, name, openingBalance);
        }
        accountCount++;
        System.out.println("Account opened successfully.");
    }

    static void showAllAccounts() {
        if (accountCount == 0) {
            System.out.println("No accounts have been registered.");
            return;
        }
        for (int i = 0; i < accountCount; i++) {
            accounts[i].printDetails();
            System.out.println();
        }
    }

    static void searchAccount() {
        int index = findAccountByPrompt();
        if (index != -1) {
            accounts[index].printDetails();
            System.out.println();
        }
    }

    static void depositMoney() {
        int index = findAccountByPrompt();
        if (index == -1) {
            return;
        }
        if (transactionCount == transactions.length) {
            System.out.println("Transaction list is full. Deposit was not made.");
            return;
        }

        System.out.print("Deposit amount: $");
        double amount = input.nextDouble();
        input.nextLine();
        while (amount <= 0) {
            System.out.print("Enter an amount greater than zero: $");
            amount = input.nextDouble();
            input.nextLine();
        }

        accounts[index].deposit(amount);
        addTransaction(accounts[index], "DEPOSIT", amount, "");
        System.out.printf("Deposit successful. New balance: $%.2f%n", accounts[index].balance);
    }

    static void withdrawMoney() {
        int index = findAccountByPrompt();
        if (index == -1) {
            return;
        }
        if (transactionCount == transactions.length) {
            System.out.println("Transaction list is full. Withdrawal was not made.");
            return;
        }

        System.out.print("Withdrawal amount: $");
        double amount = input.nextDouble();
        input.nextLine();
        while (amount <= 0) {
            System.out.print("Enter an amount greater than zero: $");
            amount = input.nextDouble();
            input.nextLine();
        }

        if (!accounts[index].canWithdraw(amount)) {
            System.out.println("Insufficient available funds. Withdrawal was not made.");
            return;
        }
        accounts[index].withdraw(amount);
        addTransaction(accounts[index], "WITHDRAWAL", amount, "");
        System.out.printf("Withdrawal successful. New balance: $%.2f%n", accounts[index].balance);
    }

    static void transferMoney() {
        System.out.println("From account");
        int sourceIndex = findAccountByPrompt();
        if (sourceIndex == -1) {
            return;
        }

        System.out.println("To account");
        int destinationIndex = findAccountByPrompt();
        if (destinationIndex == -1) {
            return;
        }
        if (sourceIndex == destinationIndex) {
            System.out.println("Choose two different accounts. Transfer was not made.");
            return;
        }
        if (transactions.length - transactionCount < 2) {
            System.out.println("Transaction list is full. Transfer was not made.");
            return;
        }

        System.out.print("Transfer amount: $");
        double amount = input.nextDouble();
        input.nextLine();
        while (amount <= 0) {
            System.out.print("Enter an amount greater than zero: $");
            amount = input.nextDouble();
            input.nextLine();
        }

        if (!accounts[sourceIndex].canWithdraw(amount)) {
            System.out.println("Insufficient available funds. Transfer was not made.");
            return;
        }

        accounts[sourceIndex].withdraw(amount);
        accounts[destinationIndex].deposit(amount);
        addTransaction(accounts[sourceIndex], "TRANSFER OUT", amount,
                accounts[destinationIndex].accountNumber);
        addTransaction(accounts[destinationIndex], "TRANSFER IN", amount,
                accounts[sourceIndex].accountNumber);
        System.out.println("Transfer successful.");
    }

    static void checkBalance() {
        int index = findAccountByPrompt();
        if (index != -1) {
            System.out.printf("Account %s balance: $%.2f%n",
                    accounts[index].getMaskedAccountNumber(), accounts[index].balance);
        }
    }

    static void showTransactionSummary() {
        int accountIndex = findAccountByPrompt();
        if (accountIndex == -1) {
            return;
        }

        double deposits = 0;
        double withdrawals = 0;
        double transfersIn = 0;
        double transfersOut = 0;
        double interest = 0;
        boolean found = false;

        System.out.println("--- Transactions for account "
                + accounts[accountIndex].getMaskedAccountNumber() + " ---");
        for (int i = 0; i < transactionCount; i++) {
            BankTransaction transaction = transactions[i];
            if (!transaction.accountNumber.equals(accounts[accountIndex].accountNumber)) {
                continue;
            }
            found = true;
            transaction.printDetails();
            System.out.println();

            switch (transaction.transactionType) {
                case "DEPOSIT" -> deposits += transaction.amount;
                case "WITHDRAWAL" -> withdrawals += transaction.amount;
                case "TRANSFER IN" -> transfersIn += transaction.amount;
                case "TRANSFER OUT" -> transfersOut += transaction.amount;
                case "INTEREST" -> interest += transaction.amount;
                default -> System.out.println("Unknown transaction type.");
            }
        }

        if (!found) {
            System.out.println("No transactions have been recorded for this account.");
        }
        System.out.printf("Deposits: $%.2f%n", deposits);
        System.out.printf("Withdrawals: $%.2f%n", withdrawals);
        System.out.printf("Transfers received: $%.2f%n", transfersIn);
        System.out.printf("Transfers sent: $%.2f%n", transfersOut);
        System.out.printf("Interest credited: $%.2f%n", interest);
    }

    static void showBankReport() {
        double totalFunds = 0;
        System.out.println("--- All Accounts ---");
        for (int i = 0; i < accountCount; i++) {
            accounts[i].printDetails();
            System.out.println();
            totalFunds += accounts[i].balance;
        }
        System.out.println("Total number of accounts: " + accountCount);
        System.out.printf("Total funds held: $%.2f%n", totalFunds);
    }

    static void applySavingsInterest() {
        int index = findAccountByPrompt();
        if (index == -1) {
            return;
        }
        if (!accounts[index].accountType.equals("Savings")) {
            System.out.println("Interest can only be applied to savings accounts.");
            return;
        }
        if (transactionCount == transactions.length) {
            System.out.println("Transaction list is full. Interest was not applied.");
            return;
        }

        double interest = accounts[index].calculateInterest();
        if (interest <= 0) {
            System.out.println("No interest is due on this balance.");
            return;
        }
        accounts[index].deposit(interest);
        addTransaction(accounts[index], "INTEREST", interest, "");
        System.out.printf("Interest credited: $%.2f%n", interest);
        System.out.printf("New balance: $%.2f%n", accounts[index].balance);
    }

    static void addTransaction(BankAccount account, String type,
                               double amount, String otherAccountNumber) {
        transactions[transactionCount] = new BankTransaction(account.accountNumber,
                type, amount, otherAccountNumber);
        transactionCount++;
    }

    static int findAccountByPrompt() {
        System.out.print("Account number: ");
        String number = input.nextLine().trim();
        int index = findAccount(number);
        if (index == -1) {
            System.out.println("Account not found.");
        }
        return index;
    }

    static int findAccount(String accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].accountNumber.equalsIgnoreCase(accountNumber)) {
                return i;
            }
        }
        return -1;
    }
}
