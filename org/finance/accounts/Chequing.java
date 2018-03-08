package org.finance.accounts;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * org.finance.accounts.Chequing Class
 * @author: Michael Butto
 * Contains name, account number, balance, start balance, service charge
 */
public class Chequing extends Account {
	private BigDecimal servCharge;
	ArrayList <BigDecimal> transactions = new ArrayList <BigDecimal> ();
	
	/**
	 * Empty constructor
	 * @author: Michael Butto
	 */
	public Chequing() {
		super();
		BigDecimal d = new BigDecimal(0.25);
		setServCharge(d);
		
	};
	
	
	/**
	 * Four argument constructor
	 * @author: Michael Butto
	 * @param name
	 * @param accNum
	 * @param sBal
	 * @param serv
	 */
	public Chequing(String name, String accNum, double sBal, BigDecimal serv) {
		super(name, accNum, sBal);
		setServCharge(serv);
		
	};
	
	/**
	 * get the service charge
	 * @author Michael Butto
	 * @return BigDecimal service charge
	 */
	public BigDecimal getServCharge() {
		return servCharge;
	};
	
	/**
	 * set the service charge
	 * @author Michael Butto
	 * @param servCharge
	 */
	public void setServCharge(BigDecimal servCharge) {
		this.servCharge = servCharge;
		
	};
	
	/**
	 * puts money into account balance
	 * @author: Michael Butto
	 * @param BigDecimal amount
	 * @return boolean success
	 */
	public boolean deposit(BigDecimal amount) {
		boolean success = false;
		
		BigDecimal n = getAccountBalance();
		
		n = n.add(amount);
		
		if(amount.compareTo(BigDecimal.ZERO) > 0) {
			if(n.compareTo(BigDecimal.ZERO) > 0){
				setAccountBalance(n);
				transactions.add(amount);
				
				success = true;
			
			}
		}
		
		return success;
	};
	
	/**
	 * takes money out of account
	 * @author: Michael Butto
	 * @param BigDecimal amount
	 * @return boolean success
	 */
	public boolean withdraw(BigDecimal amount) {
		boolean success = false;
		
		BigDecimal n = getAccountBalance();
		
		n = n.subtract(amount);
		
		if(amount.compareTo(BigDecimal.ZERO) > 0) {
			if(n.compareTo(BigDecimal.ZERO) > 0){
				setAccountBalance(n.subtract(getServCharge()));
				transactions.add(amount.negate());
				success = true;
			
			}
		}
		
		return success;
	};
	
	/**
	 * check if the accounts are equal
	 * @author Michael Butto
	 * @return boolean result 
	 */
	public boolean equals(Object a) {
		boolean result = false;
					
		//create instance of account
		if ( a instanceof Chequing ) {

			Chequing a2 = (Chequing) a;

			//check all account attributes
			if ((a2.getAccountBalance() == getAccountBalance()) &&
				(a2.getFullName().equals(getFullName())) &&
				(a2.getAccountNumber().equals(getAccountNumber())) &&
				(a2.getServCharge() == getServCharge()) &&
				(a2.getStartBal() == getStartBal())){
				
					result = true;
				}
			}

		return result;
	};
	
	/**
	 * toString method for output
	 * @author: Michael Butto/Lars Kloosterman
	 * @return String account information
	 */
	public String toString() {

        String s;
        BigDecimal total = new BigDecimal(0);

        NumberFormat curr = NumberFormat.getCurrencyInstance();

        total = total.add((new BigDecimal(transactions.size()).multiply(servCharge)));

        s =    super.toString() +
                "type: CHEQUING\n" +
                "service charge: " + curr.format(getServCharge()) + "\n" +
                "number of transactions: " + transactions.size() + "\n" +
                "total amount of service charge: " + curr.format(total) + "\n";

        return s;
    }
}
