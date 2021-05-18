package j.code;

import java.math.BigDecimal;

public class BankAccount {
    private BigDecimal balance; //var for account balance
    private String owner; //var for owner name

    public BankAccount(String owner) { //constructor for balance = 0 and owner name
        this(BigDecimal.ZERO, owner);
    }

    public BankAccount(BigDecimal balance, String owner) { //constructor
        this.balance = balance;
        this.owner = owner;
    }

    public synchronized void deposit(BigDecimal money) { // multithreading safe
        BigDecimal newBalance = balance.add(money); //put money on balance
        System.out.println("Add: " + money + ", current balance is: " + newBalance);
        balance = newBalance; // a new value for balance
    }

    public synchronized void withdraw(BigDecimal money) throws NotEnoughMoneyException { // money withdraw, exception if you have not enough money to withdraw
        BigDecimal newBalance = balance.subtract(money); // newBalance = balance - money
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) throw new NotEnoughMoneyException(); // if newBalance < 0 >> exception, newBalance is not changing
        balance = newBalance; // new value for the balance
        System.out.println("Spending " + money + ", " + balance + " left on the account");
    }

    public void deposit(String money) {
        deposit(new BigDecimal(money));
    }

    public void withdraw(String money) throws NotEnoughMoneyException {
        withdraw(new BigDecimal(money));
    }

}
