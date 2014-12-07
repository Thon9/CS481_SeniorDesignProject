package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class CheckHashtagExistController {
		public int checkHashtagExistence(String hashtagName) throws SQLException {
			return DatabaseProvider.getInstance().checkHashtagExistance(hashtagName);
		}
}

