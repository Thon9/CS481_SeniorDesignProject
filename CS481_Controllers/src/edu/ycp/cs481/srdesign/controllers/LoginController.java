package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;

import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class LoginController {
	public User login(String username, String password) throws SQLException {
		User user = new User();
		
		//System.out.println(DatabaseProvider.getInstance().login(username, password));
		user = DatabaseProvider.getInstance().login(username, password);
		return user;
	}
}
