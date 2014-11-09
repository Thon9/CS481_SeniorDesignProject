package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;

import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class AddNewUserController {
		public void addNewUser(String username, String password, String firstname, String lastname, String email) throws SQLException {
			IDatabase db = DatabaseProvider.getInstance();
			/*
			String username = user.getUserName();
			String password = user.getPassword();
			String firstname = user.getFirstName();
			String lastname = user.getLastName();
			String email = user.getUserEmail();
			*/
			
			// TODO: use db to add the account
			db.createAccount(username, password, firstname, lastname, email);
		}
}
