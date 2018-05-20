package ATM;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class UserMenu extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton checkBalanceButton;
	private JButton makeTransferButton;
	private JButton changePasswordButton;
	private JButton logoutButton;
	private JButton depositButton;
	private JButton withdrawButton;
	private JTextField infoField;

	//makeTransferPanel
	private JPanel transferPanel;
	private JTextField accountNumTo;
	private JTextField transferAmount;

	public UserMenu() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.DARK_GRAY);
		frame.setVisible(true);

		checkBalanceButton = new JButton("Check Balance");
		checkBalanceButton.setBounds(69, 114, 138, 23);

		makeTransferButton = new JButton("Make a Transfer");
		makeTransferButton.setBounds(213, 114, 140, 23);

		changePasswordButton = new JButton("Change Password");
		changePasswordButton.setBounds(69, 168, 138, 23);

		logoutButton = new JButton("Logout");
		logoutButton.setBounds(213, 168, 140, 23);

		depositButton = new JButton("Deposit");
		depositButton.setBounds(69, 64, 138, 23);

		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(213, 64, 140, 23);

		infoField = new JTextField();
		infoField.setBounds(69, 200, 280, 50);

		frame.getContentPane().setLayout(null);
		//adding buttons
		frame.getContentPane().add(depositButton);
		frame.getContentPane().add(checkBalanceButton);
		frame.getContentPane().add(changePasswordButton);
		frame.getContentPane().add(withdrawButton);
		frame.getContentPane().add(makeTransferButton);
		frame.getContentPane().add(logoutButton);
		frame.getContentPane().add(infoField);

		//creating makeTransfer Panel
		accountNumTo = new JTextField(15);
		transferAmount = new JTextField(15);
		transferPanel = new JPanel();
		transferPanel.add(new JLabel("Transer To Account Number:"));
		transferPanel.add(accountNumTo);
		transferPanel.add(Box.createHorizontalStrut(15)); // a spacer
		transferPanel.add(new JLabel("Amount To Transfer:"));
		transferPanel.add(transferAmount);


		//adding action listeners for buttons
		depositButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Main main = new Main();

				try {
					String userInput;
					userInput = JOptionPane.showInputDialog("Please Enter Amount To Deposit: \n");
					double depositBalance;
					depositBalance = Double.parseDouble(userInput);
					depositBalance = main.deposit(main.getLoginMenu().getAccountNumText(), depositBalance);
					infoField.setText("Current Balance: " + depositBalance);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		withdrawButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Main main = new Main();

				try {
					String userInput;
					userInput = JOptionPane.showInputDialog("Please Enter Amount To Withdraw: \n");
					double withdrawBalance;
					withdrawBalance = Double.parseDouble(userInput);
					withdrawBalance = main.withdraw(main.getLoginMenu().getAccountNumText(), withdrawBalance);
					infoField.setText("Current Balance: " + withdrawBalance);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		checkBalanceButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Main main = new Main();
				try {
					double userBalance;
					userBalance = main.checkBalance(main.getLoginMenu().getAccountNumText());
					infoField.setText("Current Balance: " + userBalance);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



			}

		});

		changePasswordButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Main main = new Main();
				try {
					String newPassword;
					newPassword = JOptionPane.showInputDialog("Please Enter New Password: \n");
					String currentPassword = main.getLoginMenu().getPasswordText();
					main.changePassword(currentPassword, newPassword);
					infoField.setText("Password Changed From: " + currentPassword + " to " + newPassword);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		makeTransferButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Main main = new Main();

				try {
					int userInput;
					userInput = JOptionPane.showConfirmDialog(null, transferPanel,
							"Please Enter Account Number and Transfer Amount", JOptionPane.OK_CANCEL_OPTION);
					if(userInput == JOptionPane.OK_OPTION){
					String transferStatement;
					String userNumTo = accountNumTo.getText();
					Double amount = Double.parseDouble(transferAmount.getText());
					transferStatement = main.makeTransfer(main.getLoginMenu().getAccountNumText(), userNumTo, amount);
					infoField.setText(transferStatement);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		
		logoutButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
	}
}
