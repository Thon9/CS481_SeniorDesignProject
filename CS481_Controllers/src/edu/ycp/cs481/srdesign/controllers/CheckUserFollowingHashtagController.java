package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class CheckUserFollowingHashtagController {
	public boolean checkUserFollowingHashtag (int hashtagID, int uID) throws SQLException {
		return DatabaseProvider.getInstance().checkUserFollowingHashtag(hashtagID, uID);
	}
}
