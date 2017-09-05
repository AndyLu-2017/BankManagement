
public abstract class Account {
	
	private double balance;
	
	public Account(double balance) {
		this.setBalance(balance);
	}
	
	public double getBalance() {
		return Double.parseDouble(String.format("%.2f", balance));
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	

	public abstract void Deposit(double amount);
	
	public abstract void Withdrawl(double amount);
	
	public abstract void MonthEnd();
}
