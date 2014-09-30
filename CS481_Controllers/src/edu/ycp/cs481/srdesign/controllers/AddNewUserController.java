package edu.ycp.cs481.srdesign.controllers;

import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class AddNewUserController {
		public void addNewUser(User user) {
			IDatabase db = DatabaseProvider.getInstance();
			
			// TODO: use db to add the account
			db.createAccountUser(user);
		}
}
