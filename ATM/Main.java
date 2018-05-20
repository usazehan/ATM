package ATM;

public class Main extends AccountInformation{
	
	private static ATMGui loginMenu;
	private static AccountInformation accountInfo;
	private static UserMenu userMenu;
	
	public static void main(String[] args){
		loginMenu = new ATMGui();
		accountInfo = new AccountInformation();
		
	}
	public ATMGui getLoginMenu(){
		//loginMenu = new ATMGui();
		return loginMenu;
	}
	
	public AccountInformation getAccountInfo(){
		return accountInfo;
		
	}
	
	public UserMenu getUserMenu(){
		userMenu = new UserMenu();
		return userMenu;
	}

}
