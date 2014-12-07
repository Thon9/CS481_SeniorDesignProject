package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class GetHashtagIDFromString {
	public int getHashtagID (String hashtagname) throws SQLException{
		return DatabaseProvider.getInstance().getHashtagByName(hashtagname);
	}
}
