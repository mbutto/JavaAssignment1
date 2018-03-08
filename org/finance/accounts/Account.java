package org.finance.accounts;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * org.finance.accounts.Account Class
 * Contains name, account number, balance, start balance
 * @author: Michael Butto
 */

public class Account {
	private String name;
	private String accNum;
	private BigDecimal bal;
	private BigDecimal startBal;
	
	/**
	 * Empty constructor
	 * @author Michael Butto
	 */
	public Account() {
		this(null, null, 0);
		
	};
	
	/**
	 * Three argument constructor
	 * @author Michael Butto
	 * @param n
	 * @param a
	 * @param b
	 */
	public Account(String n, String a, double b) {
		if(n == null) {
			name = "";
			
		}else {
			name = n;
			
		}
		
		if(a == null) {
			accNum = "";
			
		}else {
			accNum = a;
			
		}
		
		if(b < 0) {
			bal = new BigDecimal(0);
			startBal = new BigDecimal(0);
			
		}else {
			bal = new BigDecimal(b);
			startBal = new BigDecimal(b);
			
		}
	};
	
	/**
     * @see java.lang.Object#toString()
     * @author Lars Kloosterman/Michael Butto
     */
    public String toString() {

        String s;

        NumberFormat curr = NumberFormat.getCurrencyInstance();

        s = 	"number: " + getAccountNumber() +
                ", name: " + getFullName() +
                "\nstarting balance: " + curr.format(getStartBal()) + ", current balance: " +
                curr.format(getAccountBalance()) + "\n";

        return s;

    }
	 
	/**
	 * Set the name
	 * @author Michael Butto
	 * @param n
	 */
	public void setFullName(String n) {
		this.name = n;
		 
	};
	 
	/**
	 * get the name
	 * @author: Michael Butto
	 * @return String name
	 */

	public String getFullName() {return name;};
	 
	/**
	 * set the account number
	 * @author Michael Butto
	 * @param n
	 */
	public void setAccountNumber(String n) {
		this.accNum = n;
		 
	};
	 
	/**
	 * get the account number
	 * @author Michael Butto
	 * @return String account number
	 */
	public String getAccountNumber() {return accNum;};
	 
	/**
	 * get the account balance
	 * @author Michael Butto
	 * @param n
	 */
	public void setAccountBalance(BigDecimal n) {
		this.bal = n;
		 
	};
	 
	/** get the account balance
	 * @author Michael Butto
	 * @return BigDecimal balance
	 */
	public BigDecimal getAccountBalance() {return bal;};
	 
	/** 
	 * check if the accounts are equal
	 * @author Michael Butto
	 * @return boolean result 
	 */

	public boolean equals(Object a) {

		boolean result = false;
			
		//create instance of account
		if ( a instanceof Account ) {

			Account a2 = (Account) a;

			//check all account attributes
			if ( (a2.bal == bal) &&
				 (a2.name.equals(name)) &&
				 (a2.accNum.equals(accNum))){

				result = true;
				
			}
		}

		return result;
	}
	
	/**
	 * get starting balance
	 * @author Michael Butto
	 * @return BigDecimal starting balance
	 */

	public BigDecimal getStartBal() {
		return startBal;
		
	}
	
	/**
	 * set staring balance
	 * @author: Michael Butto
	 * @param startBal
	 */
	public void setStartBal(BigDecimal startBal) {
		this.startBal = startBal;
		
	};
	
	/**
	 * puts money into account balance
	 * @author: Michael Butto
	 * @param amount
	 * @return boolean success
	 */
	public boolean deposit(BigDecimal amount) {
		boolean success = false;
		
		BigDecimal n = bal;
		
		n = n.add(amount);
		
		if(amount.compareTo(BigDecimal.ZERO) > 0) {
			if(n.compareTo(BigDecimal.ZERO) > 0){
				bal.add(amount);
				success = true;
			
			}
		}
		
		return success;
	};
	
	/**
	 * takes money out of account
	 * @author: Michael Butto
	 * @param amount
	 * @return boolean success
	 */
	public boolean withdraw(BigDecimal amount) {
		boolean success = false;
		
		BigDecimal n = bal;
		
		n = n.subtract(amount);
		
		if(amount.compareTo(BigDecimal.ZERO) > 0) {
			if(n.compareTo(BigDecimal.ZERO) > 0){
				bal.subtract(amount);
				success = true;
			
			}
		}
		
		return success;
	};
	 
}
