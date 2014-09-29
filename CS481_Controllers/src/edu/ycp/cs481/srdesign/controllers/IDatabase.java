package edu.ycp.cs481.srdesign.controllers;

// import model classes
import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.User;

public interface IDatabase {

	
		// Returns the user based on an ID
		public User getUser(int id);
		
		// Verifys the user based on input userName and password
		public void verifyAccount(String userName, String password);
}
