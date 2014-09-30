package edu.ycp.cs481.srdesign.persist;

import edu.ycp.cs481.srdesign.User;

public interface IDatabase {

	
		// Returns the user based on an ID
		public User getUserID(int id);
		
		// Returns the user based on the userName
		public User getUserString(String username);
		
		// Creating account with user's credentials
		public boolean createAccount(String username, String password, int userID ,
				String firstname, String lastname, String email);
		
		// Verifys the user based on input userName and password
		public boolean verifyAccount(String userName, String password);
		
		// Login handling for the User
		public User login(String username, String password);
		
		// Simply checks to see if UserName already exists
		public boolean checkExistence(String username);

		public void createAccountUser(User user);

		

		
}