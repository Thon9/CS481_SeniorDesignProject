package edu.ycp.cs481.srdesign.controllers;

import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class LoginController {
	public User login(String username, String password) {
		User user = new User();
		
		//System.out.println(DatabaseProvider.getInstance().login(username, password));
		user = DatabaseProvider.getInstance().login(username, password);
		return user;
	}
}
