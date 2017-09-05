
public class CheckingAccount extends Account {
	private int freeWithdrawlAvailable = 3;
	
	public CheckingAccount(double balance) {
		super(balance);
	}
	
	public void Deposit(double amount) {
		setBalance(getBalance() + amount);
	}

	public void Withdrawl(double amount) {
		if(freeWithdrawlAvailable > 0) {
			setBalance(getBalance() - amount);
			freeWithdrawlAvailable--;
		} else setBalance(getBalance() - amount - 1);
	}

	public void MonthEnd() {
		freeWithdrawlAvailable = 3;
	}
}
