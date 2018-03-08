package org.finance.accounts;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * org.finance.accounts.GIC Class
 * @author: Michael Butto
 * Contains name, account number, balance, start balance, investment period, annual interst
 */
public class GIC extends Account {
	private int pInvest;
	private double annualInt;
	
	/**Empty constructor
	*@author: Michael Butto
	*/
	public GIC() {
		super();
		setpInvest(1);
		setAnnualInt(0.0125);
		
	};
	
	/**Four argument constructor
	 * @author: Michael Butto
	 * @param String name, String account number, double starting balance, int investment period, double annual interest rate
	 */
	public GIC(String name, String accNum, double sBal, int period, double rate) {
		super(name, accNum, sBal);
		setpInvest(period);
		setAnnualInt(rate);
		
	};

	/**
	 * get the investment period
	 * @author Michael Butto
	 * @return the pInvest
	 */
	public int getpInvest() {
		return pInvest;
	};

	/**
	 * set the investment period
	 * @author Michael Butto
	 * @param pInvest the pInvest to set
	 */
	public void setpInvest(int pInvest) {
		this.pInvest = pInvest;
	};

	/**
	 * get the annual interest
	 * @author Michael Butto
	 * @return the annualInt
	 */
	public double getAnnualInt() {
		return annualInt;
	};

	/**
	 * set annual interest
	 * @author Michael Butto
	 * @param annualInt the annualInt to set
	 */
	public void setAnnualInt(double annualInt) {
		this.annualInt = annualInt;
	};
	
	/**
	 * @author Michael Butto
	 * @param BigDecimal amount
	 * @return false deposit not allowed on org.finance.accounts.GIC
	 */
	public boolean deposit(BigDecimal a) {
		return false;
		
	};
	
	/**
	 * @author Michael Butto
	 * @param BigDecimal amount
	 * @return false Withdraw not allowed on org.finance.accounts.GIC
	 */
	public boolean withdraw(BigDecimal a) {
		return false;
		
	};
	
	/**
	 * calculates balance at maturity
	 * @author Michael Butto
	 * @param BigDecimal amount
	 * @return the balance at maturity
	 */
	public BigDecimal getBalanceAtMaturity() {
		BigDecimal mature = new BigDecimal(0);
		
		mature = (getStartBal().multiply(new BigDecimal(1 + annualInt))).multiply(new BigDecimal(pInvest));
		
		return mature;
	};
	
	/**
	 * check if the accounts are equal
	 * @author Michael Butto
	 * @return boolean result 
	 */
	public boolean equals(Object a) {

		boolean result = false;
					
		//create instance of account
		if ( a instanceof GIC ) {

			GIC a2 = (GIC) a;

			//check all account attributes
			if ((a2.getAccountBalance() == getAccountBalance()) &&
				(a2.getFullName().equals(getFullName())) &&
				(a2.getAccountNumber().equals(getAccountNumber())) &&
				(a2.getpInvest() == getpInvest()) &&
				(a2.getAnnualInt() == getAnnualInt()) &&
				(a2.getStartBal() == getStartBal())){
				
				result = true;
			}
		}

		return result;
	};
	
	/**
	 * toString method for output
	 * @author: Michael Butto
	 * @return String account information
	 */
	public String toString() {

		String s;
		NumberFormat curr = NumberFormat.getCurrencyInstance();
		       
		s =	super.toString() + 
		   	"type: org.finance.accounts.GIC\n" +
	 		"annual interest rate: " + getAnnualInt() + "% \n" +
	 	   	"period of investment: " + getpInvest() + " years \n" +
		   	"new balance at maturity: " + curr.format(getBalanceAtMaturity()) + "\n";

		return s;
	}
	
}
