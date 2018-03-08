package com.little.bank;

import org.finance.accounts.Account;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Bank {

	private ArrayList<Account> accounts;	// Bank accounts
	private String name;			        // Name of the bank
	
	/**
	 * Zero-argument constructor.
	 * @author Lars Kloosterman
	 */
	public Bank() {
		this.name = "Seneca@York";
		this.accounts = new ArrayList<>();
	}
	
	/**
	 * One-argument constructor.
	 * @param name The name of the bank.
	 * @author Lars Kloosterman
	 */
	public Bank(String name) {
		this.name = name;
		this.accounts = new ArrayList<>();
	}
	
	/**
	 * Adds the specified Account object to the ArrayList and returns success status.
	 * @param account The account to add to the ArrayList of managed accounts.
	 * @author Lars Kloosterman
	 */
	public boolean addAccount(Account account) {
		boolean success = false;
		
		if (account != null) {
			boolean found = false;
			
			for (Account acct : accounts) {
				if (acct.getAccountNumber().equals(account.getAccountNumber())) {
					found = true;
				}
			}
			
			if (!found) {
				accounts.add(account);
				
				success = true;	
			}
		}
			
		return success;
	}
	
	/**
	 * Searches through the accounts within the Bank object and returns an array
	 * of accounts with the specified balance.
	 * @param balance The balance to search through the accounts for.
	 * @author Lars Kloosterman
	 */
	public Account[] searchByBalance(BigDecimal balance) {
		ArrayList<Account> result = new ArrayList<>();
		
		for (Account account : this.accounts) {
			if (account.getAccountBalance().compareTo(balance) == 0) {
				result.add(account);
			}
		}
		
		return result.toArray(new Account[result.size()]);
	}
	
	/**
	 * @see java.lang.Object#toString()
	 * @author Lars Kloosterman
	 */
	@Override
	public String toString() {
		String result;
		StringBuilder builder = new StringBuilder();

		int counter = 0;
		for (Account account : this.accounts) {
			counter++;
			builder.append(String.format("\n%d. number: %s, name: %s, balance: $%s", counter,
					account.getAccountNumber(), account.getFullName(), account.getAccountBalance().toPlainString()));
		}

		result = builder.toString();

		return result;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @author Lars Kloosterman
	 */
	@Override
	public boolean equals(Object alt) {
		boolean equal = false;
		
		if (alt instanceof Bank) {
			Bank ret = (Bank)alt;
			
			if ((ret.getName().equals(this.getName())) && (this.getAccounts().equals(ret.getAccounts()))) {
				equal = true;
			}
		}
		
		return equal;
	}
	
	/**
	 * Returns the name within a Bank object.
	 * @author Lars Kloosterman
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name within a Bank object to the String specified.
	 * @param name The value to set the Bank's name to.
	 * @author Lars Kloosterman
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the accounts within a Bank object as an array.
	 * @author Lars Kloosterman
	 */
	public Account[] getAccounts() {
		return this.accounts.toArray(new Account[this.accounts.size()]);
	}

	/**
	 * Returns the accounts with the specified name.
	 * @param name The name to search through the accounts for.
     * @author Lars Kloosterman
	 */ 
	public Account[] searchByAccountName(String name) {
		ArrayList<Account> results = new ArrayList<>();

		if (name != null) {
			for (Account account : accounts) {
				if (account.getFullName().equals(name)) {
					results.add(account);
				}
			}
		}

		return results.toArray(new Account[results.size()]);
	}

	/**
	 * Returns the account with the specified number.
	 * @param number The number to search through the accounts for.
	 * @author Lars Kloosterman
	 */
	public Account searchByAccountNumber(String number) {
		Account result = null;

		if (number != null) {
			for (Account account : accounts) {
				if (account.getAccountNumber().equals(number)) {
					result = account;
				}
			}
		}

		return result;
	}

	/**
	 * Removes the account with the specified number and returns it.
	 * @param number The number for the account to remove.
	 * @author Lars Kloosterman
	 */
	public Account removeAccount(String number) {
		Account removalAccount = null;

		if (number != null) {
			boolean removed = false;
			int counter = 0;

			do {
				Account account = accounts.get(counter);

				if (account.getAccountNumber().equals(number)) {
					removalAccount = account;
					accounts.remove(account);
					removed = true;
				}

				counter++;
			} while (!removed);
		}

		return removalAccount;
	}
	
}
