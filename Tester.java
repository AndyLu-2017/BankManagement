import java.util.Scanner;

public class Tester {
	
	private static Scanner scnr = new Scanner(System.in);
	private static int monthNum = 1;
	
	public static void main(String[] args) {
		Account[] account = {
				new SavingAccount(50), 
				new SavingAccount(50, 0.02), 
				new CheckingAccount(100)
		};
		
		boolean quit = false;
		while(!quit) {
			int accountNum;
			String input;
			double amount;
			
			printAccounts(account);
			System.out.println("What action you would like to take?");
			input = scnr.nextLine();
			if(input.charAt(0) == 'd' || input.charAt(0) == 'D') {
				System.out.println("Which account you would like to access?");
				accountNum = scnr.nextInt();scnr.nextLine();
				System.out.print("Deposit Amount: ");
				amount = scnr.nextDouble();
				deposit(account, accountNum, amount);
			}
			if(input.charAt(0) == 'w' || input.charAt(0) == 'W') {
				System.out.println("Which account you would like to access?");
				accountNum = scnr.nextInt();scnr.nextLine();
				System.out.print("Withdrawl Amount: ");
				amount = scnr.nextDouble();
				withdrawl(account, accountNum, amount);
			}
			if(input.charAt(0) == 'm' || input.charAt(0) == 'M') monthEnd(account);
			if(input.charAt(0) == 'q' || input.charAt(0) == 'Q') {
				System.out.println("Exited from Bank Accounts Management Menu.");
				System.exit(0);
			}
		}
		
	}
	
	private static void deposit
	(Account[] account, int accountNum, double amount) {
		account[accountNum-1].Deposit(amount);
		System.out.println("	 Account" + accountNum + " +" + 
				amount + " Balance: $" + account[accountNum-1].getBalance());
	}
	
	private static void withdrawl
	(Account[] account, int accountNum, double amount) {
		account[accountNum-1].Withdrawl(amount);
		System.out.println("	 Account" + accountNum + " -" + 
				amount + " Balance: $" + account[accountNum-1].getBalance());
	}
	
	private static void printAccounts(Account[] account) {
		System.out.println();
		for(int i = 0; i < account.length; i++) 
			System.out.println("Month" + monthNum + " - Account" + 
					(i+1) + " Balance: $" + account[i].getBalance());
		System.out.println();
	}
	
	private static void monthEnd(Account[] account) {
		System.out.println();
		for(int i = 0; i < account.length; i++) 
			System.out.println("Month" + monthNum + " - Account" + 
					(i+1) + " Balance: $" + account[i].getBalance());
		for(int i = 0; i < account.length; i++) 
			account[i].MonthEnd();
		System.out.println("Month" + monthNum +" ended\n");
		monthNum++;
	}
}
