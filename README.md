# Practical Assignment: Object-Oriented Programming in Java

**Student Details:**
- **Name:** Anesu B Kahanda
- **Registration Number:** H250407A
- **Department:** Computer Science
- **Course:** Object-Oriented Programming in Java

---

## Overview
This practical assignment implements an object-oriented banking application demonstrating abstraction, inheritance, polymorphism, encapsulation, and dynamic method dispatch in Java.

A bank offers two distinct account types that share core state and behaviour (account numbering, deposits, balance tracking) while exhibiting different rules for withdrawals and end-of-month maintenance processing:
1. **Savings Account**: Enforces a minimum balance floor below which withdrawals are rejected, and earns a monthly interest rate applied to the balance.
2. **Current Account**: Supports an overdraft facility allowing the balance to go into debit up to a specified overdraft limit, and incurs a fixed monthly maintenance fee.

---

## Deliverables & Class Hierarchy

```
Assignment/
├── Account.java          # Abstract base class with shared state and abstract hooks
├── SavingsAccount.java   # Subclass enforcing minimum balance and applying interest
├── CurrentAccount.java   # Subclass supporting overdraft and deducting maintenance fees
├── BankDemo.java         # Driver program demonstrating polymorphic dispatch and edge cases
└── README.md             # Project documentation and execution trace
```

### 1. `Account.java` (Abstract Base Class)
- **Protected Fields:** `accountNumber` (`String`), `balance` (`double`).
- **Concrete Methods:**
  - `deposit(double amount)`: Validates that deposits are strictly positive before crediting balance.
  - `getBalance()`: Accessor for current balance.
  - `getAccountNumber()`: Accessor for account identification.
- **Abstract Hook Methods:**
  - `abstract void withdraw(double amount)`: Implemented differently by subclasses to enforce their respective balance constraints.
  - `abstract void endOfMonth()`: Implemented differently by subclasses to apply interest or fee adjustments.

### 2. `SavingsAccount.java`
- Extends `Account` and encapsulates a `minimumBalance` threshold and an `interestRate`.
- **`withdraw(double amount)`**: Verifies whether `balance - amount < minimumBalance`. If breached, the transaction is rejected with an informative console alert and balance remains unchanged.
- **`endOfMonth()`**: Calculates interest earned (`balance * interestRate`) and credits it to the account balance.

### 3. `CurrentAccount.java`
- Extends `Account` and encapsulates an `overdraftLimit` and a fixed `maintenanceFee`.
- **`withdraw(double amount)`**: Permits withdrawals exceeding current balance provided `balance - amount >= -overdraftLimit`. Rejects withdrawals exceeding the overdraft ceiling.
- **`endOfMonth()`**: Deducts the fixed monthly maintenance service charge from the account.

### 4. `BankDemo.java`
- Manages a heterogeneous `List<Account>` holding instances of both `SavingsAccount` and `CurrentAccount`.
- Demonstrates true dynamic method dispatch by invoking `withdraw(...)` and `endOfMonth()` **strictly through the base `Account` reference without casting**, allowing the JVM runtime to dispatch to the correct overridden subclass logic.
- Verifies all edge cases:
  - Valid savings withdrawal.
  - Rejected savings withdrawal violating minimum balance floor.
  - Overdraft withdrawal within allowed overdraft limit.
  - Rejected current account withdrawal violating maximum overdraft limit.
  - Month-end processing for both savings interest and current account fees.

---

## How to Compile and Run

From the repository root directory:

```bash
# 1. Compile all source files
javac *.java

# 2. Run the driver demonstration
java BankDemo
```

---

## Expected Output

```
=================================================================
   Object-Oriented Programming — Practical Assignment: Bank System
=================================================================

--- 1. INITIAL ACCOUNT BALANCES ---
Account: SA-1001 | Type: SavingsAccount | Balance: $500.00
Account: SA-1002 | Type: SavingsAccount | Balance: $250.00
Account: CA-2001 | Type: CurrentAccount | Balance: $300.00
Account: CA-2002 | Type: CurrentAccount | Balance: $150.00

--- 2. DEMONSTRATING DEPOSITS ---
[SA-1001] Successfully deposited $100.00. New balance: $600.00
[SA-1002] Successfully deposited $100.00. New balance: $350.00
[CA-2001] Successfully deposited $100.00. New balance: $400.00
[CA-2002] Successfully deposited $100.00. New balance: $250.00

--- 3. POLYMORPHIC WITHDRAWALS & EDGE CASES ---
[Test 1: Valid Savings Withdrawal]
[SA-1001] Withdrew $200.00. New balance: $400.00

[Test 2: Savings Withdrawal Rejection — Below Minimum Balance]
[SA-1002] WITHDRAWAL REJECTED: Attempted to withdraw $320.00, but balance would drop below minimum balance of $50.00 (Current balance: $350.00).

[Test 3: Current Account Overdraft Within Allowed Limit]
[CA-2001] Withdrew $500.00 (OVERDRAFT ACTIVE). New balance: $-100.00 (Remaining overdraft limit: $100.00)

[Test 4: Current Account Overdraft Limit Exceeded]
[CA-2002] WITHDRAWAL REJECTED: Attempted to withdraw $900.00, which exceeds overdraft limit of $500.00 (Current balance: $250.00, Max allowed: $750.00).

--- 4. POLYMORPHIC END-OF-MONTH PROCESSING ---
Processing month-end adjustments (polymorphically via Account references):
[SA-1001] End of Month: Applied interest of $16.00 (Rate: 4.0%). New balance: $416.00
[SA-1002] End of Month: Applied interest of $10.50 (Rate: 3.0%). New balance: $360.50
[CA-2001] End of Month: Deducted maintenance fee of $12.00. New balance: $-112.00
[CA-2002] End of Month: Deducted maintenance fee of $15.00. New balance: $235.00

--- 5. FINAL ACCOUNT BALANCES ---
Account: SA-1001 | Type: SavingsAccount | Final Balance: $416.00
Account: SA-1002 | Type: SavingsAccount | Final Balance: $360.50
Account: CA-2001 | Type: CurrentAccount | Final Balance: $-112.00
Account: CA-2002 | Type: CurrentAccount | Final Balance: $235.00

=================================================================
   Assignment Verification Completed Successfully
=================================================================
```
