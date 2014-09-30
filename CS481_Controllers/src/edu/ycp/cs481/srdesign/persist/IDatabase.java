package edu.ycp.cs481.srdesign.persist;

import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.User;

public interface IDatabase {

	
		// Returns the user based on an ID
		public User getUser(int id);
		
		// Verifys the user based on input userName and password
		public void verifyAccount(String userName, String password);
		
		// Adds the new user to the fakeDatabase
		public void addNewUser(User user);
		
		//Removes user from database
		public void deleteUser(int userID);
		
		// Add new photo to the fakeDatabase
		public void addPhoto(Photo photo);
		
		// Add new photo to the fakeDatabase
		public void addHashtag(HashTag hashtag);
						
		// Login handling for the User
		public User login(String username, String password);
		
		// Simply checks to see if UserName already exists
		public boolean checkExistence(String username);
}
