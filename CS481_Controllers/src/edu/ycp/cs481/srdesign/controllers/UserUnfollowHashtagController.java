package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class UserUnfollowHashtagController {
	public boolean userUnfollowHashtag (int hashtagID,int uID) throws SQLException {
		return DatabaseProvider.getInstance().userUnfollowHashtag(hashtagID, uID);
	}
}
