package ATM;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountInformation {

	private String password;
	private int accountNumber;
	private String name;
	private double accountBalance;
	private String accessStatus;
	private boolean accountInfoMatches = false;
	private ArrayList<String> userNums = new ArrayList<String>();
	private ArrayList<String> userPasswords = new ArrayList<String>();
	private ArrayList<String> accountNums =  new ArrayList<String>();
	private ArrayList<String> lastNames =  new ArrayList<String>();
	private ArrayList<String> firstNames =  new ArrayList<String>();
	private ArrayList<String> userBalance =  new ArrayList<String>();
	private ArrayList<String> userStatus =  new ArrayList<String>(); 

	private File accountInfoFile = new File("AccountInformation.txt");
	private static File passwordFile = new File("Password.txt");
	private AccountInformation(String password, int accountNum, String name, double accountbalance, String accessstatus){

		setAccountNumber(accountNum);
		setPassword(password);
		setAccountBalance(accountbalance);
		setAccessStatus(accessstatus);
	}

	private void setName(String name){

		this.name = name;
	}
	private void setAccountBalance(double accountbalance){
		this.accountBalance = accountbalance;
	}
	private void setAccessStatus(String accessstatus){

		this.accessStatus = accessstatus;
	}
	public int getAccountNumber(){
		return accountNumber;
	}
	public boolean getAccessStatus(){
		if(accessStatus.equals("Active")){
			return true;}
		else{
			return false;}
	}
	public String getPassword(){
		return password;
	}
	public double getAccountBalance(){

		return accountBalance;
	}
	private String getName(){
		return name;
	}
	public void setAccountNumber(int accountnumber){

		this.accountNumber = accountnumber;
	}

	public void setPassword(String password){

		this.password = password;
	}
	public void setAccountInfoMatches(){
		accountInfoMatches = true;
	}
	public boolean checkInfo(int testAccountNum, String testPassword) throws IOException, 
	FileNotFoundException{
		Scanner scanInfo = new Scanner(passwordFile);
		while(scanInfo.hasNext()){
			if (scanInfo.nextInt() == testAccountNum && 
					scanInfo.next().equals(testPassword)){
				setAccountInfoMatches();
				setAccountNumber(testAccountNum);
				setPassword(testPassword);
				System.out.println("true");
				break;
			}
			break;
		}
		scanInfo.close();
		return accountInfoMatches;
	}

	public AccountInformation getUserInfo() throws FileNotFoundException{

		Scanner inputAccountInfo = new Scanner(accountInfoFile);

		String firstName = null;
		String lastName = null;
		double balance = 0.00;
		String accessstatus = null;

		while(inputAccountInfo.hasNext()){
			inputAccountInfo.nextInt();
			firstName = inputAccountInfo.nextLine();
			lastName = inputAccountInfo.nextLine();
			balance = inputAccountInfo.nextDouble();
			accessstatus = inputAccountInfo.nextLine();
		}
		String name = firstName + " " + lastName;
		inputAccountInfo.close();

		AccountInformation patron = new AccountInformation(password, accountNumber, name, 
				balance, accessstatus);
		return patron;
	}
	public void readAccount(String fileName) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(fileName));
		int count =0;
		while(scan.hasNext()){
			if(scan.hasNext())
				accountNums.add(scan.nextLine());

			if(scan.hasNext())
				lastNames.add(scan.nextLine());

			if(scan.hasNext())
				firstNames.add(scan.nextLine());

			if(scan.hasNext())
				userBalance.add(scan.nextLine());

			if(scan.hasNext())
				userStatus.add(scan.nextLine());
			if(scan.hasNext())
				scan.nextLine();

		}
		scan.close();
	}
	public void readPassword(File fileName) throws FileNotFoundException{
		Scanner scan = new Scanner(fileName);
		while(scan.hasNext()){
			if(scan.hasNext())
				userNums.add(scan.next());
			if(scan.hasNext())
				userPasswords.add(scan.next());
		}
		scan.close();
	}

	public void changePassword(String currentPassword, String newPassword) throws IOException{
		readPassword(passwordFile);
		FileWriter fileWriter = new FileWriter(passwordFile);
		BufferedWriter buffWriter = new BufferedWriter(fileWriter);
		int count = 0;
		for(String tempPassword: userPasswords){
			if(tempPassword.equals(currentPassword)){
				userPasswords.set(count, newPassword);
			}
			buffWriter.write(userNums.get(count) + " " + userPasswords.get(count));
			buffWriter.newLine();
			count++;
		}
		buffWriter.close();
		fileWriter.close();
		//tempPasswordFile.renameTo(passwordFile);
	}

	public double deposit(String accountNum, double depositSum) throws IOException{
		readAccount("AccountInformation.txt");
		FileWriter accountWriter = new FileWriter(accountInfoFile);
		BufferedWriter buff = new BufferedWriter(accountWriter);
		double accountBalance = 0.0;
		int counter = 0;
		for(String userID: accountNums){
			buff.write(userID); System.out.println("userID: " + userID);
			buff.newLine();
			buff.write(lastNames.get(counter)); 
			buff.newLine();
			buff.write(firstNames.get(counter));
			buff.newLine();

			if(userID.equals(accountNum)){
				accountBalance = Double.parseDouble(userBalance.get(counter)) + depositSum;
				buff.write(Double.toString(accountBalance));
				buff.newLine();
			} else{
				buff.write(userBalance.get(counter));
				buff.newLine();
			}
			buff.write(userStatus.get(counter));
			buff.newLine();
			buff.newLine();
			counter++;
		}
		buff.close();
		accountWriter.close();
		return accountBalance;
	}

	public double withdraw(String accountNum, double withdrawAmount) throws IOException{
		readAccount("AccountInformation.txt");
		FileWriter accountWriter = new FileWriter(accountInfoFile);
		BufferedWriter buff = new BufferedWriter(accountWriter);
		double accountBalance = 0.0;
		int counter = 0;
		for(String userID: accountNums){
			buff.write(userID); 
			buff.newLine();
			buff.write(lastNames.get(counter));
			buff.newLine();
			buff.write(firstNames.get(counter));
			buff.newLine();

			if(userID.equals(accountNum)){
				accountBalance = Double.parseDouble(userBalance.get(counter)) - withdrawAmount;
				buff.write(Double.toString(accountBalance));
				buff.newLine();
			} else{
				buff.write(userBalance.get(counter));
				buff.newLine();
			}
			buff.write(userStatus.get(counter));
			buff.newLine();
			buff.newLine();
			counter++;
		}
		buff.close();
		accountWriter.close();
		return accountBalance;
	}
	public String makeTransfer(String accountNumFrom, String accountNumTo, double transferAmount) throws IOException{
		readAccount("AccountInformation.txt");
		FileWriter accountWriter = new FileWriter(accountInfoFile);
		BufferedWriter buff = new BufferedWriter(accountWriter);
		double accountBalance = 0.0;
		int counter = 0;
		for(String userID: accountNums){
			buff.write(userID); 
			buff.newLine();
			buff.write(lastNames.get(counter));
			buff.newLine();
			buff.write(firstNames.get(counter));
			buff.newLine();

			if(userID.equals(accountNumTo)){
				accountBalance = Double.parseDouble(userBalance.get(counter)) + transferAmount;
				buff.write(Double.toString(accountBalance));
				buff.newLine();
			} else{
				buff.write(userBalance.get(counter));
				buff.newLine();
			}
			buff.write(userStatus.get(counter));
			buff.newLine();
			buff.newLine();
			counter++;
		}
		buff.close();
		accountWriter.close();
		String statement = "$" +transferAmount + " transferred from " + accountNumFrom + " to "
							+ accountNumTo;
		return statement;
	}

	public double checkBalance(String accountNum) throws FileNotFoundException{
		readAccount("AccountInformation.txt");
		double accountBalance = 0.0;
		int index = 0;
		for(String userID: accountNums){
			if(userID.equals(accountNum)){
				accountBalance = Double.parseDouble(userBalance.get(index));
			}
			index++;
		}
		return accountBalance;
	}

	public AccountInformation() {

		Scanner inputpassword = null;
		try {
			inputpassword = new Scanner(passwordFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String accountnum = null;
		String password = null;
		while(inputpassword.hasNext()){
			accountnum = inputpassword.next();
			password = inputpassword.next();
			System.out.println(accountnum + " " + password);
		}  
		inputpassword.close();

		Scanner inputaccountinfo = null;
		try {
			inputaccountinfo = new Scanner(accountInfoFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String accountn = null;
		String firstname = null;
		String lastname = null;
		double balance = 0.00;
		String status = null;

		accountn = inputaccountinfo.next();
		lastname = inputaccountinfo.next();
		inputaccountinfo.nextLine();
		firstname = inputaccountinfo.nextLine();
		balance = inputaccountinfo.nextDouble();
		status = inputaccountinfo.next();

		inputaccountinfo.close();

		String name = firstname + " " + lastname;

		if(accountnum == accountn){
			new AccountInformation(password, Integer.parseInt(accountnum), name, balance, status);
		}

	}

}
