// Subclass of Account enforcing a minimum balance on withdrawals and paying monthly interest.
public class SavingsAccount extends Account {
    private double minimumBalance;
    private double interestRate;

    public SavingsAccount(String accountNumber, double balance, double minimumBalance, double interestRate) {
        super(accountNumber, balance);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }

    public SavingsAccount(String accountNumber, double balance) {
        this(accountNumber, balance, 100.0, 0.035);
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.printf("[%s] Withdrawal rejected: Amount must be positive.%n", accountNumber);
            return;
        }

        if (balance - amount < minimumBalance) {
            System.out.printf("[%s] WITHDRAWAL REJECTED: Attempted to withdraw $%.2f, but balance would drop below minimum balance of $%.2f (Current balance: $%.2f).%n",
                    accountNumber, amount, minimumBalance, balance);
        } else {
            balance -= amount;
            System.out.printf("[%s] Withdrew $%.2f. New balance: $%.2f%n", accountNumber, amount, balance);
        }
    }

    @Override
    public void endOfMonth() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.printf("[%s] End of Month: Applied interest of $%.2f (Rate: %.1f%%). New balance: $%.2f%n",
                accountNumber, interest, interestRate * 100, balance);
    }
}
