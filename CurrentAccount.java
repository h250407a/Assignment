// Subclass of Account allowing overdraft withdrawals up to a limit and deducting a monthly maintenance fee.
public class CurrentAccount extends Account {
    private double overdraftLimit;
    private double maintenanceFee;

    public CurrentAccount(String accountNumber, double balance, double overdraftLimit, double maintenanceFee) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
        this.maintenanceFee = maintenanceFee;
    }

    public CurrentAccount(String accountNumber, double balance, double overdraftLimit) {
        this(accountNumber, balance, overdraftLimit, 15.0);
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public double getMaintenanceFee() {
        return maintenanceFee;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.printf("[%s] Withdrawal rejected: Amount must be positive.%n", accountNumber);
            return;
        }

        if (balance - amount < -overdraftLimit) {
            System.out.printf("[%s] WITHDRAWAL REJECTED: Attempted to withdraw $%.2f, which exceeds overdraft limit of $%.2f (Current balance: $%.2f, Max allowed: $%.2f).%n",
                    accountNumber, amount, overdraftLimit, balance, balance + overdraftLimit);
        } else {
            balance -= amount;
            if (balance < 0) {
                System.out.printf("[%s] Withdrew $%.2f (OVERDRAFT ACTIVE). New balance: $%.2f (Remaining overdraft limit: $%.2f)%n",
                        accountNumber, amount, balance, overdraftLimit + balance);
            } else {
                System.out.printf("[%s] Withdrew $%.2f. New balance: $%.2f%n", accountNumber, amount, balance);
            }
        }
    }

    @Override
    public void endOfMonth() {
        balance -= maintenanceFee;
        System.out.printf("[%s] End of Month: Deducted maintenance fee of $%.2f. New balance: $%.2f%n",
                accountNumber, maintenanceFee, balance);
    }
}
