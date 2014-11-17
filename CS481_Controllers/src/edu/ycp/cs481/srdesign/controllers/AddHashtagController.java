package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;

import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class AddHashtagController {
	public void addHashtag(HashTag hashtag) throws SQLException {
		IDatabase db = DatabaseProvider.getInstance();
		try {
			db.addHashtag(hashtag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
