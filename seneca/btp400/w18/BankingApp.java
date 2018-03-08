package seneca.btp400.w18;

import com.little.bank.Bank;
import org.finance.accounts.Account;
import org.finance.accounts.Chequing;
import org.finance.accounts.GIC;
import org.finance.accounts.Savings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BankingApp {

    private static Bank bank;

    /**
     * Loads the bank object with test accounts.
     * @param bank The bank object to load the test accounts into.
     * @author Lars Kloosterman
     */
    private static void loadBank(Bank bank) {
        // John Doe accounts
        bank.addAccount(new GIC("John Doe", "D1234", 6000.00, 2, 1.5));
        bank.addAccount(new Chequing("John Doe", "E5678", 15000.00, new BigDecimal(0.75)));
        bank.addAccount(new Savings("John Doe", "F9801", 8000.00, 0.15));

        // Mary Ryan accounts
        bank.addAccount(new GIC("Mary Ryan", "A1234", 15000.00, 4, 2.5));
        bank.addAccount(new Chequing("Mary Ryan", "B5678", 15000.00, new BigDecimal(0.75)));
        bank.addAccount(new Savings("Mary Ryan", "C9012", 8000.00, 0.15));
    }

    /**
     * Displays the main application menu to the console.
     * @param bankName The name of the bank to display on the header.
     * @author Lars Kloosterman
     */
    private static void displayMenu(String bankName) {
        LocalDateTime dateTime = LocalDateTime.now();

        String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("h:mm a MMMM d, y"));

        System.out.format("### Welcome to the Bank of %s ###\n"
                + dateTimeString + "\n"
                + "\n1. Open an account.\n"
                + "2. Close an account.\n"
                + "3. Update an account.\n"
                + "4. Search.\n"
                + "5. List all accounts.\n"
                + "6. Exit.\n"
                + "\nPlease make a selection: ", bankName);
    }

    /**
     * Removes the first element from the given ArrayList and returns the removed element.
     * @param list The ArrayList object to run the operation on.
     * @return The removed element
     * @author Lars Kloosterman
     */
    private static String push(ArrayList<String> list) {
        String value = list.get(0);

        list.remove(0);

        return value;
    }

    /**
     * Runs the operations needed to open a new account at the bank.
     * @author Lars Kloosterman
     */
    private static void openAccount() {
        String input;

        System.out.print("\na. Savings Account.\n"
                + "b. Chequing Account.\n"
                + "c. GIC Account.\n"
                + "Please make a selection: ");

        Scanner scan = new Scanner(System.in);
        input = scan.next();

        if (input.equals("a")) {
            System.out.print("\nSavings Account Creation:\n"
                    + "Please enter the account information in one line\n"
                    + "(<name>, <account number>, <balance>, <interest rate>): ");

            scan.skip("\\n");
            input = scan.nextLine();
            ArrayList<String> splitInput = new ArrayList<>(Arrays.asList(input.split(", *")));

            String name, number;
            double balance, interest;

            boolean success = false;

            if (splitInput.size() == 4)
                success = true;

            if (success) {
                name = push(splitInput);
                number = push(splitInput);
                balance = Double.parseDouble(push(splitInput));
                interest = Double.parseDouble(push(splitInput));

                success = bank.addAccount(new Savings(name, number, balance, interest));

                if (success) {
                    System.out.println("\n+ Account Opened:\n"
                            + bank.searchByAccountNumber(number).toString());
                }
            }

            if (!success) {
                System.out.println("\n*** FAILED: ACCOUNT CANNOT BE OPENED ***\n");
            }
        } else if (input.equals("b")) {
            System.out.print("\nChequing Account Creation:\n"
                    + "Please enter the account information in one line\n"
                    + "(<name>, <account number>, <balance>, <service charge>): ");

            scan.skip("\\n");
            input = scan.nextLine();
            ArrayList<String> splitInput = new ArrayList<>(Arrays.asList(input.split(", *")));

            String name, number;
            double balance;
            BigDecimal charge;

            boolean success = false;

            if (splitInput.size() == 4)
                success = true;

            if (success) {
                name = push(splitInput);
                number = push(splitInput);
                balance = Double.parseDouble(push(splitInput));
                charge = new BigDecimal(push(splitInput));

                success = bank.addAccount(new Chequing(name, number, balance, charge));

                if (success) {
                    System.out.println("\n+ Account Opened:\n"
                            + bank.searchByAccountNumber(number).toString());
                }
            }

            if (!success) {
                System.out.println("\n*** FAILED: ACCOUNT CANNOT BE OPENED ***\n");
            }
        } else if (input.equals("c")) {
            System.out.print("\nGIC Account Creation:\n"
                    + "Please enter the account information in one line\n"
                    + "(<name>, <account number>, <balance>, <interest period>, <interest rate>): ");

            scan.skip("\\n");
            input = scan.nextLine();
            ArrayList<String> splitInput = new ArrayList<>(Arrays.asList(input.split(", *")));

            String name, number;
            double balance, rate;
            int period;

            boolean success = false;

            if (splitInput.size() == 5)
                success = true;

            if (success) {
                name = push(splitInput);
                number = push(splitInput);
                balance = Double.parseDouble(push(splitInput));
                period = Integer.parseInt(push(splitInput));
                rate = Double.parseDouble(push(splitInput));

                success = bank.addAccount(new GIC(name, number, balance, period, rate));

                if (success) {
                    System.out.println("\n+ Account Opened:\n"
                            + bank.searchByAccountNumber(number).toString());
                }
            }

            if (!success) {
                System.out.println("\n*** FAILED: ACCOUNT CANNOT BE OPENED ***\n");
            }

            scan.close();
        }
    }

    /**
     * Runs the operations needed to close an account at the bank.
     * @author Lars Kloosterman
     */
    private static void closeAccount() {
        System.out.print("\nPlease enter the account number for removal: ");

        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        String removeNumber = input;

        Account removalAccount = bank.searchByAccountNumber(removeNumber);

        if (removalAccount != null) {
            System.out.print("\n" + removalAccount.toString()
                    + "\nDo you wish to remove the above account?\n"
                    + "(y/n): ");
            scan.skip("\\n");
            input = scan.next();

            if (input.equals("y")) {
                removalAccount = bank.removeAccount(removeNumber);

                System.out.println("\n- Account Removed:\n"
                        + removalAccount.toString());
            } else {
                System.out.println("\n*** REMOVAL ABORTED ***\n");
            }
        } else {
            System.out.println("\n*** FAILED: ACCOUNT WAS NOT FOUND ***\n");
        }

        scan.close();
    }

    /**
     * Runs the operations needed to update an account managed by the bank.
     * @author Lars Kloosterman
     */
    private static void updateAccount() {
        System.out.println("Please enter an account number: ");

        Scanner scan = new Scanner(System.in);
        String input = scan.next();

        Account account = bank.searchByAccountNumber(input);

        if (account != null) {
            System.out.print("\nAccount:\n"
                    + account.toString()
                    + "\na. Deposit.\n"
                    + "b. Withdraw\n"
                    + "Please make a selection: ");

            scan.skip("\\n");
            input = scan.next();

            if (input.equals("a")) {
                System.out.print("\nDeposit:\n"
                        + "Please enter an amount to deposit: ");

                scan.skip("\\n");
                input = scan.next();

                boolean success = account.deposit(new BigDecimal(input));

                if (success) {
                    System.out.println("\n+ Deposit Successful:\n"
                            + account.toString() + "\n");
                } else {
                    System.out.println("\n*** FAILED: COULD NOT DEPOSIT FUNDS ***\n");
                }
            } else if (input.equals("b")) {
                System.out.print("\nWithdraw:\n"
                        + "Please enter an amount to withdraw: ");

                scan.skip("\\n");
                input = scan.next();

                boolean success = account.withdraw(new BigDecimal(input));

                if (success) {
                    System.out.println("\n- Withdrawal Successful:\n"
                            + account.toString() + "\n");
                } else {
                    System.out.println("\n*** FAILED: COULD NOT WITHDRAW FUNDS ***");
                }
            }
        } else {
            System.out.println("\n*** FAILED: ACCOUNT WAS NOT FOUND ***\n");
        }

        scan.close();
    }

    /**
     * Provides search functionality to the banking application.
     * @author Lars Kloosterman
     */
    private static void searchAccount() {
        System.out.print("\na. Search by account balance.\n"
                + "b. Search by account name.\n"
                + "c. Search by account number.\n"
                + "Please make a selection: ");

        Scanner scan = new Scanner(System.in);
        String input = scan.next();

        if (input.equals("a")) {
            System.out.print("\nPlease enter the balance to search for: ");

            scan.skip("\\n");
            input = scan.next();

            if (input.matches("[^a-zA-Z]*")) {
                Account[] matches = bank.searchByBalance(new BigDecimal(input));

                if (matches.length > 0) {
                    listAccounts(matches);
                } else {
                    System.out.println("\n*** NO ACCOUNTS FOUND ***\n");
                }
            } else {
                System.out.println("\n*** FAILED: COULD NOT PARSE SEARCH PARAMETER ***");
            }
        } else if (input.equals("b")) {
            System.out.print("\nPlease enter the name to search for: ");

            scan.skip("\\n");
            input = scan.nextLine();

            if (input.matches("[^0-9]*")) {
                Account[] matches = bank.searchByAccountName(input);

                if (matches.length != 0) {
                    listAccounts(matches);
                } else {
                    System.out.println("\n*** NO ACCOUNTS FOUND ***\n");
                }
            } else {
                System.out.println("\n*** FAILED: COULD NOT PARSE SEARCH PARAMETER ***");
            }
        } else if (input.equals("c")) {
            System.out.print("\nPlease enter the account number to search for: ");

            scan.skip("\\n");
            input = scan.next();

            System.out.println(input);

            Account match = bank.searchByAccountNumber(input);

            if (match != null) {
                displayAccount(match);
            } else {
                System.out.println("\n*** NO ACCOUNTS FOUND ***\n");
            }
        }

        scan.close();
    }

    // displayAccount displays information of one account
    // @author Michael Butto
    // @param account
    private static void displayAccount(Account account) {
        System.out.println(account.toString());
    }

    // listAccounts method lists all accounts information
    // @author Michael Butto
    // @param accountList
    private static void listAccounts(Account[] accountList) {
        for(Account a : accountList) {
            System.out.println("\n" + a.toString());
        }
    }

    /**
     * The main method for the banking application.
     * @param args Command line arguments for the program
     * @author Lars Kloosterman
     */
    public static void main(String[] args) {
        bank = new Bank("Lars and Michael");

        loadBank(bank);

        boolean loopMenu = true;

        Scanner scan = new Scanner(System.in);

        do {
            displayMenu(bank.getName());

            String input;
            scan.reset();
            input = scan.nextLine();

            if (input.equals("1")) {
                openAccount();
            } else if (input.equals("2")) {
                closeAccount();
            } else if (input.equals("3")) {
                updateAccount();
            } else if (input.equals("4")) {
                searchAccount();
            } else if (input.equals("5")) {
                listAccounts(bank.getAccounts());
            } else if (input.equals("6")) {
                System.out.println("Thank you!");
                loopMenu = false;
            }
        } while (loopMenu);

        scan.close();
    }

}
