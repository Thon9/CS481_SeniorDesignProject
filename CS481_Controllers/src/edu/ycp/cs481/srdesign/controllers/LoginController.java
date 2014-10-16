package edu.ycp.cs481.srdesign.controllers;

import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class LoginController {
	public User login(String username, String password) {
		return DatabaseProvider.getInstance().login(username, password);
		
	}
}
