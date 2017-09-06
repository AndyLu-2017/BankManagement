import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * TempConvert
 * This program demonstrates the use of a GridBaglayout manager
 * to create a simple GUI interface involving labels, textfields, 
 * and buttons.
 *
 * @author Andy
 */
@SuppressWarnings("serial")
public class Bank extends JFrame {
	/*
	 *Define the GUI components as class variables
	 *which are accessible from anywhere.
	 *
	 */
	
	
	JLabel balance = new JLabel();
	JLabel month = new JLabel();
	
	JButton buttonA = new JButton();
	JButton button1 = new JButton();
	JButton button2 = new JButton();
	JButton button3 = new JButton();
	JButton button4 = new JButton();
	
	int accountNum = 1;
	Account[] account = {
			new SavingAccount(50), 
			new SavingAccount(250), 
			new CheckingAccount(100)
	};
	String[] accountChoice = { "01", "02", "03"};
	@SuppressWarnings("rawtypes")
	JComboBox accountSelect = new JComboBox<Object>(accountChoice);
	ArrayList<Account> accountList = new ArrayList<Account>();
	/*
	 *Initialize the frame and display it.
	 */
	public Bank() {
		setTitle("Bank Accounts Management Menu");
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
				jBActionPerformed(e);
				updateLabel();
			}
		});
		addItem(accountSelect, 1, 0, 2, 1, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL);
		
		//(3, 0)
		buttonA.setText("+");
		//specify the action listener for the button
		buttonA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bAActionPerformed(e);
				updateLabel();
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
		updateLabel();
		addItem(balance, 1, 1, 2, 1, GridBagConstraints.NORTH, 
				GridBagConstraints.HORIZONTAL);
		
		//(3, 1)
		grid.gridx = 3;
		grid.gridy = 1;
		getContentPane().add(month, grid);
		
		//(0, 2)
		button1.setText("Deposit");
		//specify the action listener for the button
		button1.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				b1ActionPerformed(e);
				updateLabel();
			}
		});
		grid.gridx = 0;
		grid.gridy = 2;
		getContentPane().add(button1, grid);
		
		//(1, 2)
		button2.setText("Withdrawl");
		//specify the action listener for the button
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b2ActionPerformed(e);
				updateLabel();
			}
		});
		grid.gridx = 1;
		grid.gridy = 2;
		getContentPane().add(button2, grid);
		
		//(2, 2)
		button3.setText("MonthEnd");
		//specify the action listener for the button
		button3.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				b3ActionPerformed(e);
				updateLabel();
			}
		});
		grid.gridx = 2;
		grid.gridy = 2;
		getContentPane().add(button3, grid);
		
		//(3, 2)
		button4.setText("Quit");
		//specify the action listener for the button
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b4ActionPerformed(e);
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
	
	//defines the action listener for the accountSelect
	private void jBActionPerformed(ActionEvent e) {
		accountNum = accountSelect.getSelectedIndex() + 1;
	}
	
	//defines the action listener for the buttonA
	private void bAActionPerformed(ActionEvent e) {
		
	}
	
	//defines the action listener for the button1
	private void b1ActionPerformed(ActionEvent e) {
		deposit(account, accountNum, Double.parseDouble(
				JOptionPane.showInputDialog(null, "Deposit Amount:")));
	}
	
	//defines the action listener for the button2
	private void b2ActionPerformed(ActionEvent e) {
		withdrawl(account, accountNum, Double.parseDouble(
				JOptionPane.showInputDialog(null, "Withdrawl Amount:")));
	}
	//defines the action listener for the button3
	private void b3ActionPerformed(ActionEvent e) {
		monthEnd(account);
	}
	
	//defines the action listener for the button4
	private void b4ActionPerformed(ActionEvent e) {
		//System.out.println("Exited from Bank Accounts Management Menu.");
		System.exit(0);
	}
	
	private void updateLabel() {
		balance.setText("$" + account[accountNum-1].getBalance());
		month.setText("Month - " + Account.getMonthNum());
	}
	
	private static void deposit
	(Account[] account, int accountNum, double amount) {
		account[accountNum-1].Deposit(amount);
		String string = "Account" + accountNum + " +$" + amount + 
				"\nNew Balance: $" + account[accountNum-1].getBalance();
		JOptionPane.showMessageDialog(null, string);
	}
	
	private static void withdrawl
	(Account[] account, int accountNum, double amount) {
		account[accountNum-1].Withdrawl(amount);
		String string = "Account" + accountNum + " -$" + amount + 
				"\nNew Balance: $" + account[accountNum-1].getBalance();
		JOptionPane.showMessageDialog(null, string);
	}
	
	private static void monthEnd(Account[] account) {
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
	
	//the main program simply initializes and displays the frame
	public static void main(String[]args) {
		new Bank();
	}
}