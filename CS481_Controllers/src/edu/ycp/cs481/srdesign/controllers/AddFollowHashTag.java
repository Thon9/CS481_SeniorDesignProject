package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;

import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class AddFollowHashTag {
	public boolean addFollowingHashTag (int hashID,int uID) throws SQLException {
		IDatabase db = DatabaseProvider.getInstance();
		return db.addFollowHashtagToUser(hashID, uID);
	}
}
