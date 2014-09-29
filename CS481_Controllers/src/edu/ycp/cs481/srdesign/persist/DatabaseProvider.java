package edu.ycp.cs481.srdesign.persist;


public class DatabaseProvider {
	private static IDatabase theInstance;
	
	public static void setInstance(IDatabase db) {
		theInstance = db;
	}
	
	public static IDatabase getInstance() {
		return theInstance;
	}
}
