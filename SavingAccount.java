
public class SavingAccount extends Account {
	
	private double interestRate = 0.025;
	
	public SavingAccount(double balance) {
		super(balance);
	}
	public SavingAccount(double balance, double interestRate) {
		super(balance);
		this.interestRate = interestRate;
	}
	
	public void Deposit(double amount) {
		setBalance(getBalance() + amount);
	}

	public void Withdrawl(double amount) {
		setBalance(getBalance() - amount);
	}

	public void MonthEnd() {
		if(getBalance() > 0)
			setBalance(getBalance() + (getBalance()*interestRate));
	}
}
