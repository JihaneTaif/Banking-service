package account;



import transaction.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {

    private int balance = 0;
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void deposit(int amount) {
        validatePositiveAmount(amount);

        balance += amount;
        transactions.add(
                new Transaction(LocalDate.now(), amount, balance)
        );
    }

    @Override
    public void withdraw(int amount) {
        validatePositiveAmount(amount);

        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }

        balance -= amount;
        transactions.add(
                new Transaction(LocalDate.now(), -amount, balance)
        );
    }

    @Override
    public void printStatement() {
        System.out.println("Date || Amount || Balance");

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            System.out.println(
                    transaction.getDate() + " || " +
                            transaction.getAmount() + " || " +
                            transaction.getBalance()
            );
        }
    }

    private void validatePositiveAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}
