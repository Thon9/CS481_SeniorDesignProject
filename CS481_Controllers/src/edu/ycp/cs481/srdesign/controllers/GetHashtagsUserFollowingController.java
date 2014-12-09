package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.ycp.cs481.srdesign.HashTag;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class GetHashtagsUserFollowingController {
	public ArrayList<HashTag> GetPhotosFollowingHashtagID(final int uID) throws SQLException{
		return DatabaseProvider.getInstance().getUserFollowingHashtags(uID);
	}
}
