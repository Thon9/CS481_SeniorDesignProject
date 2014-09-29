package edu.ycp.cs481.srdesign.controllers;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;


public class CheckExistUserController {
	public boolean checkExistence(String username) {
		IDatabase db = DatabaseProvider.getInstance();
		
		return db.checkExistence(username);
		}
}

