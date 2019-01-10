/**
 * Transactions.java
 */

/**
 * @author {Mugdha Phadke}
 *
 */
public class Transactions {
	final private int accountNo;
	final private String transactionType;
	final private double amount;
	static private int noCheckingAccountTransaction =0;
	Transactions(int account, String type, double amount){
		this.accountNo = account;
		this.transactionType = type;
		this.amount = amount;
		noCheckingAccountTransaction++;
	}
	
	boolean isCheckingAccountTransaction()
	{
		if (noCheckingAccountTransaction >0)
			return true;
		else
			return false;
	}
	
	@Override
	 
    public String toString()
    {
		 return  "AccountNo: " + this.accountNo + "\t"+
				 "Transaction Type: " + this.transactionType + "\t"+
				 "amount " + this.amount + "\t\n";
				 
	}
	void printTransaction() {
		System.out.println(this.toString());
	}
	
	
	
	

}
