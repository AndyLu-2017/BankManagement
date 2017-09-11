import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Bank
 * This program demonstrates the use of a GridBaglayout manager
 * to create a simple GUI interface involving labels, textfields, 
 * and buttons.
 *
 * @author Andy
 */
@SuppressWarnings("serial")
public class Bank extends JFrame {
	
	static int accountNum = 1;
	static Account[] account = {
			new SavingAccount(101, 50), 
			new SavingAccount(102, 250), 
			new CheckingAccount(103, 100)
	};
	static String[] accountChoice = { 
			Integer.toString(account[0].getAccountNum()), 
			Integer.toString(account[1].getAccountNum()), 
			Integer.toString(account[2].getAccountNum())
	};
	static String[] accountType = {"Saving Account", "Checking Account"};
	
	static JComboBox<String> accountSelect = new JComboBox<String>(accountChoice);
	//ArrayList<Account> accountList = new ArrayList<Account>();
	
	static JLabel balance = new JLabel();
	static JLabel month = new JLabel();
	
	/*
	 *Initialize the frame and display it.
	 */
	public Bank() {
		setTitle("Virtual ATM");
		setLocationRelativeTo(null);
		//define the layout manager
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		grid.ipadx = 4;
		grid.ipady = 4;
		grid.insets = new Insets(4, 4, 4, 4);
		grid.fill = GridBagConstraints.BOTH;
		
		//(0, 0)
		JLabel label1 = new JLabel("Account:");
		grid.gridx = 0;
		grid.gridy = 0;
		getContentPane().add(label1, grid);
		
		//(1, 0)
		accountSelect.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				accountNum = accountSelect.getSelectedIndex() + 1;
				update();
			}
		});
		addItem(accountSelect, 1, 0, 2, 1, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL);
		
		//(3, 0)
		JButton buttonA = new JButton("+");
		//specify the action listener for the button
		buttonA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAccountMenu();
				update();
			}
		});
		grid.gridx = 3;
		grid.gridy = 0;
		getContentPane().add(buttonA, grid);
		
		//(0, 1)
		JLabel label2 = new JLabel("Balance:");
		grid.gridx = 0;
		grid.gridy = 1;
		getContentPane().add(label2, grid);
		
		//(1, 1)
		update();
		addItem(balance, 1, 1, 2, 1, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL);
		
		//(3, 1)
		grid.gridx = 3;
		grid.gridy = 1;
		getContentPane().add(month, grid);
		
		//(0, 2)
		JButton button1 = new JButton("Deposit");
		//specify the action listener for the button
		button1.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				deposit(accountNum, Double.parseDouble(
						JOptionPane.showInputDialog(null, "Deposit Amount:")));
				update();
			}
		});
		grid.gridx = 0;
		grid.gridy = 2;
		getContentPane().add(button1, grid);
		
		//(1, 2)
		JButton button2 = new JButton("Withdrawl");
		//specify the action listener for the button
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdrawl(accountNum, Double.parseDouble(
						JOptionPane.showInputDialog(null, "Withdrawl Amount:")));
				update();
			}
		});
		grid.gridx = 1;
		grid.gridy = 2;
		getContentPane().add(button2, grid);
		
		//(2, 2)
		JButton button3 = new JButton("MonthEnd");
		//specify the action listener for the button
		button3.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				monthEnd();
				update();
			}
		});
		grid.gridx = 2;
		grid.gridy = 2;
		getContentPane().add(button3, grid);
		
		//(3, 2)
		JButton button4 = new JButton("Quit");
		//specify the action listener for the button
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		grid.gridx = 3;
		grid.gridy = 2;
		getContentPane().add(button4, grid);
		
		//prepare the frame for display and displaying it
		pack();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	// WIP, incomplete
	private static void createAccountMenu() {
		JFrame CAMenu = new JFrame();
		CAMenu.setTitle("Create New Account");
		CAMenu.setLocationRelativeTo(null);
		//define the layout manager
		CAMenu.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		grid.ipadx = 4;
		grid.ipady = 4;
		grid.insets = new Insets(4, 4, 4, 4);
		grid.fill = GridBagConstraints.BOTH;
		
		//(0, 0)
		JLabel label1 = new JLabel("Account Type:");
		grid.gridx = 0;
		grid.gridy = 0;
		CAMenu.getContentPane().add(label1, grid);
		
		//(1, 0)
		JComboBox<String> jBox1 = new JComboBox<String>(accountType);
		grid.gridx = 1;
		grid.gridy = 0;
		CAMenu.getContentPane().add(jBox1, grid);
				
		//(0, 1)
		JLabel label2 = new JLabel("Account Number:");
		grid.gridx = 0;
		grid.gridy = 1;
		CAMenu.getContentPane().add(label2, grid);
		
		//(1, 1)
		TextField textf2 = new TextField();
		textf2.setColumns(30);
		grid.gridx = 1;
		grid.gridy = 1;
		CAMenu.getContentPane().add(textf2, grid);
		
		//(0, 2)
		JLabel label3 = new JLabel("Initial Deposit:");
		grid.gridx = 0;
		grid.gridy = 2;
		CAMenu.getContentPane().add(label3, grid);
		
		//(1, 2)
		TextField textf3 = new TextField();
		grid.gridx = 1;
		grid.gridy = 2;
		CAMenu.getContentPane().add(textf3, grid);
		
		//(0, 3)
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CAMenu.dispose();
			}
		});
		grid.gridx = 0;
		grid.gridy = 3;
		CAMenu.getContentPane().add(buttonCancel, grid);
		
		//(1, 3)
		JButton buttonConfirm = new JButton("Confirm");
		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accType = accountType[jBox1.getSelectedIndex()];
				int accountNUm = Integer.parseInt(textf2.getText());
				double initAmount = Double.parseDouble(textf3.getText());
				addAccount(accType, accountNUm, initAmount);
				CAMenu.dispose();
			}
		});
		grid.gridx = 1;
		grid.gridy = 3;
		grid.anchor = GridBagConstraints.EAST;
		grid.fill = GridBagConstraints.VERTICAL;
		CAMenu.getContentPane().add(buttonConfirm, grid);
		
		CAMenu.pack();
		CAMenu.setVisible(true);
	}
	
	private static void update() {
		balance.setText("$" + account[accountNum-1].getBalance());
		month.setText("Month - " + Account.getMonthNum());
	}
	
	private static void deposit
	(int accountNum, double amount) {
		account[accountNum-1].Deposit(amount);
		String string = "Account" + accountNum + " +$" + amount + 
				"\nNew Balance: $" + account[accountNum-1].getBalance();
		JOptionPane.showMessageDialog(null, string);
	}
	
	private static void withdrawl
	(int accountNum, double amount) {
		account[accountNum-1].Withdrawl(amount);
		String string = "Account" + accountNum + " -$" + amount + 
				"\nNew Balance: $" + account[accountNum-1].getBalance();
		JOptionPane.showMessageDialog(null, string);
	}
	
	private static void monthEnd() {
		String string = "End of Month" + Account.getMonthNum() + " Account Summary:";
		for(int i = 0; i < account.length; i++) {
			string += "\n   - Account" + (i+1) + 
					" Balance: $" + account[i].getBalance();
			account[i].MonthEnd();
		}
		string += "\n*End of month interest not included.";
		JOptionPane.showMessageDialog(null, string);
		Account.setMonthNum(Account.getMonthNum() + 1);
	}
	
	private void addItem(JComponent c, int x, int y, 
			int width, int height, int align, int fill) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.gridheight = height;
		gc.insets = new Insets(4, 4, 4, 4);
		gc.anchor = align;
		gc.fill = fill;
		getContentPane().add(c, gc);
	}
	
	private static void addAccount(String accountType, 
			int accountNum, double initialDeposit) {
		account = Arrays.copyOf(account, account.length + 1);
		if(accountType.contains("saving")) 
			account[account.length - 1] = new SavingAccount(accountNum, initialDeposit);
		else if(accountType.contains("checking")) 
			account[account.length - 1] = new CheckingAccount(accountNum, initialDeposit);
		else account[account.length - 1] = new SavingAccount(accountNum, initialDeposit);
		
		accountChoice = Arrays.copyOf(accountChoice, accountChoice.length + 1);
		accountChoice[accountChoice.length - 1] = Integer.toString(accountNum);
		accountSelect.addItem( Integer.toString(accountNum));
	}
	
	//the main program simply initializes and displays the frame
	public static void main(String[]args) {
		new Bank();
	}
}