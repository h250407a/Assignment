// Driver program demonstrating polymorphism with Account references, edge cases, and month-end processing.
import java.util.ArrayList;
import java.util.List;

public class BankDemo {
    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("   Object-Oriented Programming — Practical Assignment: Bank System");
        System.out.println("=================================================================\n");

        // Create a polymorphic collection of Account references
        List<Account> accounts = new ArrayList<>();

        // 1. Savings Accounts (initial balance, minimum balance, interest rate)
        SavingsAccount sa1 = new SavingsAccount("SA-1001", 500.0, 100.0, 0.04);
        SavingsAccount sa2 = new SavingsAccount("SA-1002", 250.0, 50.0, 0.03);

        // 2. Current Accounts (initial balance, overdraft limit, maintenance fee)
        CurrentAccount ca1 = new CurrentAccount("CA-2001", 300.0, 200.0, 12.0);
        CurrentAccount ca2 = new CurrentAccount("CA-2002", 150.0, 500.0, 15.0);

        accounts.add(sa1);
        accounts.add(sa2);
        accounts.add(ca1);
        accounts.add(ca2);

        System.out.println("--- 1. INITIAL ACCOUNT BALANCES ---");
        for (Account acc : accounts) {
            System.out.printf("Account: %s | Type: %-14s | Balance: $%.2f%n",
                    acc.getAccountNumber(), acc.getClass().getSimpleName(), acc.getBalance());
        }

        System.out.println("\n--- 2. DEMONSTRATING DEPOSITS ---");
        for (Account acc : accounts) {
            acc.deposit(100.0);
        }

        System.out.println("\n--- 3. POLYMORPHIC WITHDRAWALS & EDGE CASES ---");
        // Test 1: SavingsAccount normal withdrawal (successful)
        System.out.println("[Test 1: Valid Savings Withdrawal]");
        accounts.get(0).withdraw(200.0);

        // Test 2: SavingsAccount withdrawal exceeding minimum balance (CORRECTLY REJECTED)
        System.out.println("\n[Test 2: Savings Withdrawal Rejection — Below Minimum Balance]");
        accounts.get(1).withdraw(320.0);

        // Test 3: CurrentAccount entering overdraft within allowed limit (SUCCESSFUL OVERDRAFT)
        System.out.println("\n[Test 3: Current Account Overdraft Within Allowed Limit]");
        accounts.get(2).withdraw(500.0);

        // Test 4: CurrentAccount exceeding overdraft limit (REJECTED)
        System.out.println("\n[Test 4: Current Account Overdraft Limit Exceeded]");
        accounts.get(3).withdraw(900.0);

        System.out.println("\n--- 4. POLYMORPHIC END-OF-MONTH PROCESSING ---");
        System.out.println("Processing month-end adjustments (polymorphically via Account references):");
        for (Account acc : accounts) {
            acc.endOfMonth();
        }

        System.out.println("\n--- 5. FINAL ACCOUNT BALANCES ---");
        for (Account acc : accounts) {
            System.out.printf("Account: %s | Type: %-14s | Final Balance: $%.2f%n",
                    acc.getAccountNumber(), acc.getClass().getSimpleName(), acc.getBalance());
        }

        System.out.println("\n=================================================================");
        System.out.println("   Assignment Verification Completed Successfully");
        System.out.println("=================================================================");
    }
}
