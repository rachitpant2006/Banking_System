package bms;

import java.util.InputMismatchException;
import java.util.Scanner;

// User-defined exception for invalid deposit or withdrawal amounts
class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

// User-defined exception for insufficient balance
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class Banking {
    int account_num, balance;

    void deposition(int val) throws InvalidAmountException {
        if (val <= 0) {
            throw new InvalidAmountException("Deposited amount isn't positive!");
        }
        balance = balance + val;
        System.out.println("Deposited Successfully!New Balance: " + balance);
    }

    void withdrawing(int val) throws InvalidAmountException, InsufficientFundsException {
        if (val <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive!");
        }
        if (val > balance) {
            throw new InsufficientFundsException("Insufficient balance to withdraw!");
        }
        balance = balance - val;
        System.out.println("Withdrawn Successfully! Remaining Balance: " + balance);
    }

    void balance_check() {
        System.out.println("The Account number " + account_num + " has a balance of " + balance + " Rupees");
    }
}

public class BankManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Banking b = new Banking();

        try {
            System.out.println("Enter the account_num: ");
            b.account_num = sc.nextInt();
            System.out.println("Enter current Balance: ");
            b.balance = sc.nextInt();
            System.out.println("Account Created Successfully!");

            System.out.println("Enter the operation you want to perform:");
            System.out.println("1. Depositing Money\n2. Withdrawing Money\n3. Viewing Bank Balance\n4. Exit");
            while (true) {
                int op;
                try {
                    op = sc.nextInt();
                } catch (InputMismatchException ime) {
                    System.out.println("Invalid! Please enter a number.");
                    sc.next();
                    continue;
                }

                int val;
                switch (op) {
                    case 1:
                        System.out.println("Enter the amount to be deposited: ");
                        try {
                            val = sc.nextInt();
                            b.deposition(val);
                        } catch (InputMismatchException ime) {
                            System.out.println("Invalid! Amount must be numeric.");
                            sc.next();
                        } catch (InvalidAmountException iae) {
                            System.out.println(iae.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Enter the amount to be withdrawn: ");
                        try {
                            val = sc.nextInt();
                            b.withdrawing(val);
                        } catch (InputMismatchException ime) {
                            System.out.println("Invalid! Amount must be numeric.");
                            sc.next();
                        } catch (InvalidAmountException | InsufficientFundsException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 3:
                        b.balance_check();
                        break;

                    case 4:
                        System.out.println("Exiting....");
                        return;

                    default:
                        System.out.println("Invalid! Please select 1, 2, 3, or 4.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }
}
