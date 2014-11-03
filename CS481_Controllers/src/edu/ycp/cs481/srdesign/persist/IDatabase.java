package edu.ycp.cs481.srdesign.persist;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.Photo;

public interface IDatabase {

	
		// Returns the user based on an ID
		public User getUserID(int id) throws SQLException;
		
		// Returns the user based on the userName
		public User getUserString(String username) throws SQLException;
		
		// Creating account with user's credentials
		public boolean createAccount(String username, String password, int userID ,
				String firstname, String lastname, String email) throws SQLException;

		//Removes user from database
		public void deleteUser(int userID);
		
		// Add new photo to the fakeDatabase
		public void addPhoto(String fileName, InputStream content);

		// Login handling for the User
		public User login(String username, String password);

		// Verifys the user based on input userName and password
		public boolean verifyAccount(String userName, String password) throws SQLException;
		
		// Simply checks to see if UserName already exists
		public boolean checkExistence(String username);

		public void createAccountUser(User user);
		
		// Add new photo to the fakeDatabase
		public void addHashtag(HashTag hashtag);

		Boolean execute(Connection conn) throws SQLException;

		
}

