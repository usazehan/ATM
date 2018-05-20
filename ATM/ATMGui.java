package ATM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ATMGui extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel loginPanel;
	private JPanel accountNumPanel;
	private JPanel passwordPanel;
	private JLabel accountNumLabel;
	private JLabel passwordLabel;
	private JTextField accountNumText;
	private JTextField passwordText;
	private JButton loginButton;
	private JButton createAccountButton;
	private JPanel createAccountPanel;
	private JPanel mainPanel;

	public ATMGui(){

		//creating frame
		this.frame = new JFrame("ATM");
		this.frame.setLayout(new FlowLayout());
		loginPanel = new JPanel();
		accountNumPanel = new JPanel();
		passwordPanel = new JPanel();
		createAccountPanel = new JPanel();
		mainPanel = new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel,BoxLayout.X_AXIS));
		createAccountPanel.setLayout(new BoxLayout(createAccountPanel, BoxLayout.X_AXIS));
		accountNumPanel.setLayout( new BoxLayout(accountNumPanel, BoxLayout.X_AXIS));
		passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBounds(5, 5, frame.getWidth()-5, frame.getHeight()-5);
		setBackground(Color.DARK_GRAY);
		//setting labels
		accountNumLabel = new JLabel("Account Number: \t");
		accountNumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordLabel = new JLabel("Password: \t");
		passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton = new JButton("Login");
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		createAccountButton = new JButton("Create New Account");
		createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountNumText = new JTextField(20);
		accountNumText.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordText = new JTextField(20);
		passwordText.setAlignmentX(Component.CENTER_ALIGNMENT);

		//assigning buttons and labels to panels
		loginPanel.add(loginButton);		
		createAccountPanel.add(createAccountButton);
		accountNumPanel.add(accountNumLabel);
		accountNumPanel.add(accountNumText);		
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordText);

		//adding to mainPanel
		mainPanel.add(accountNumPanel);
		mainPanel.add(passwordPanel);
		mainPanel.add(loginPanel);
		mainPanel.add(createAccountPanel);

		//adding panels to frame
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 350);
		frame.setResizable(true);
		frame.setVisible(true);

		//adding action listener for login
		loginButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//TODO Login
				//System.out.println("Login Pressed");
				//checking if accountNum and password match text file
				Main main = new Main();
				//System.out.println(accountNumText.getText());
				try {
					//if(main.getAccountInfo().compareAccountNum(Integer.parseInt(accountNumText.getText())) &&
					//main.getAccountInfo().comparePassword(passwordText.getText())){
					if(main.checkInfo(Integer.parseInt(accountNumText.getText()), passwordText.getText())){	
						System.out.println("Log in successful");
						main.getUserMenu();

					}else if(!(main.checkInfo(Integer.parseInt(accountNumText.getText()), passwordText.getText()))){
						System.out.println("Login Failed");
					}
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		createAccountButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Main main = new Main();
				if(accountNumText.getText() != null){
					main.getAccountInfo().setAccountNumber(Integer.parseInt(accountNumText.getText()));

				}
				if(passwordText.getText() != null){
					main.getAccountInfo().setPassword(passwordText.getText());
				}
			}

		});

	}
	public String getAccountNumText(){
		return accountNumText.getText();
	}
	public String getPasswordText(){
		return passwordText.getText();
	}

}
