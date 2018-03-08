package org.finance.accounts;

/**
 * org.finance.accounts.Savings Class
 * //Contains name, account number, balance, start balance, annual interest
 * @author: Michael Butto
 */
public class Savings extends Account {
	private double anInt;
	
	/**
	 * Four argument constructor
	 * @author: Michael Butto
	 * @param name
	 * @param accNum
	 * @param sBal
	 * @param interest
	 */
	public Savings(String name, String accNum, double sBal, double interest) {
		super(name, accNum, sBal);
		setAnInt(interest);
		
	};
	
	/**
	 * Empty constructor
	 * @author: Michael Butto
	 */
	public Savings() {
		super();
		setAnInt(0.3);
		
	};
	
	/**
	 * toString method for output
	 * @author: Michael Butto
	 * @return String account information
	 */
	public String toString() {
	       String s;

	       s =	super.toString() + 
 			   	"type: SAVINGS\n" + 
 			   	"annual interest rate: " + getAnInt() + "% \n";

	       return s;
	 };
	
	/**
	 * check if the accounts are equal
	 * @author Michael Butto
	 * @return boolean result 
	 */
	public boolean equals(Object a) {

		boolean result = false;
				
		//create instance of account
		if ( a instanceof Savings ) {

			Savings a2 = (Savings) a;

			//check all account attributes
			if ((a2.getAccountBalance() == getAccountBalance()) &&
				(a2.getFullName().equals(getFullName())) &&
				(a2.getAccountNumber().equals(getAccountNumber())) &&
				(a2.anInt == anInt) &&
				(a2.getStartBal() == getStartBal())){

				result = true;
			}
		}

		return result;
	};
	
	/**
	 * get the annual interest
	 * @author Michael Butto
	 * @return double annual interest
	 */
	public double getAnInt() {
		return anInt;
	};
	
	/** 
	 * set the annual interest
	 * @author Michael Butto
	 * @param double annual interest
	 */
	public void setAnInt(double anInt) {
		this.anInt = anInt;
	};
}
