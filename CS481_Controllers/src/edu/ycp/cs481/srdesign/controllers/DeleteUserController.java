package edu.ycp.cs481.srdesign.controllers;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class DeleteUserController {
	public void deleteUser(int userID) {
		IDatabase db = DatabaseProvider.getInstance();
		db.deleteUser(userID);
	}
}
