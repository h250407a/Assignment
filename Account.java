// Abstract base class representing a bank account with shared deposit, balance tracking, and abstract operations.
public abstract class Account {
    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.printf("[%s] Deposit rejected: Amount must be greater than zero. (Attempted: $%.2f)%n", accountNumber, amount);
            return;
        }
        balance += amount;
        System.out.printf("[%s] Successfully deposited $%.2f. New balance: $%.2f%n", accountNumber, amount, balance);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public abstract void withdraw(double amount);

    public abstract void endOfMonth();
}
