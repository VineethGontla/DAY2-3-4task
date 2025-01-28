package snippet;

import java.util.Scanner;

public class BankingApplication {

    // Abstract Account class
    static abstract class Account {
        String accountNumber;
        double balance;

        public Account(String accountNumber, double balance) {
            this.accountNumber = accountNumber;
            this.balance = balance;
        }

        // Deposit method
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: $" + amount);
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        }

        // Withdraw method
        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Withdrew: $" + amount);
            } else if (amount <= 0) {
                System.out.println("Withdrawal amount must be positive.");
            } else {
                System.out.println("Insufficient balance.");
            }
        }

        // Transfer method
        public void transfer(Account targetAccount, double amount) {
            if (amount > 0 && amount <= balance) {
                this.balance -= amount;
                targetAccount.deposit(amount);
                System.out.println("Transferred: $" + amount + " to account " + targetAccount.accountNumber);
            } else if (amount <= 0) {
                System.out.println("Transfer amount must be positive.");
            } else {
                System.out.println("Insufficient balance for transfer.");
            }
        }

        // Print account balance
        public void printBalance() {
            System.out.println("Account " + accountNumber + " balance: $" + balance);
        }
    }

    // Savings Account class (inherits from Account)
    static class SavingsAccount extends Account {
        public SavingsAccount(String accountNumber, double balance) {
            super(accountNumber, balance);
        }
    }

    // Current Account class (inherits from Account)
    static class CurrentAccount extends Account {
        public CurrentAccount(String accountNumber, double balance) {
            super(accountNumber, balance);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create two accounts: one Savings and one Current
        Account savingsAccount = new SavingsAccount("SA101", 1000.0);
        Account currentAccount = new CurrentAccount("CA101", 5000.0);

        // Perform deposit, withdraw, and transfer operations
        boolean exit = false;
        while (!exit) {
            System.out.println("\nBanking Operations:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an operation: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Deposit
                    System.out.print("Enter account number to deposit into (SA101 or CA101): ");
                    String depositAccount = scanner.next();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    if (depositAccount.equals("SA101")) {
                        savingsAccount.deposit(depositAmount);
                    } else if (depositAccount.equals("CA101")) {
                        currentAccount.deposit(depositAmount);
                    } else {
                        System.out.println("Invalid account number.");
                    }
                    break;

                case 2: // Withdraw
                    System.out.print("Enter account number to withdraw from (SA101 or CA101): ");
                    String withdrawAccount = scanner.next();
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (withdrawAccount.equals("SA101")) {
                        savingsAccount.withdraw(withdrawAmount);
                    } else if (withdrawAccount.equals("CA101")) {
                        currentAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Invalid account number.");
                    }
                    break;

                case 3: // Transfer
                    System.out.print("Enter source account number (SA101 or CA101): ");
                    String sourceAccount = scanner.next();
                    System.out.print("Enter target account number (SA101 or CA101): ");
                    String targetAccount = scanner.next();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    if (sourceAccount.equals("SA101") && targetAccount.equals("CA101")) {
                        savingsAccount.transfer(currentAccount, transferAmount);
                    } else if (sourceAccount.equals("CA101") && targetAccount.equals("SA101")) {
                        currentAccount.transfer(savingsAccount, transferAmount);
                    } else {
                        System.out.println("Invalid account numbers for transfer.");
                    }
                    break;

                case 4: // Check Balance
                    System.out.print("Enter account number to check balance (SA101 or CA101): ");
                    String balanceAccount = scanner.next();
                    if (balanceAccount.equals("SA101")) {
                        savingsAccount.printBalance();
                    } else if (balanceAccount.equals("CA101")) {
                        currentAccount.printBalance();
                    } else {
                        System.out.println("Invalid account number.");
                    }
                    break;

                case 5: // Exit
                    exit = true;
                    System.out.println("Exiting the Banking Application.");
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }

        scanner.close();
    }
}

