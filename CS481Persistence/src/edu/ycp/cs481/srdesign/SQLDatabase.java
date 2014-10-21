package edu.ycp.cs481.srdesign;

import java.sql.*;

public class SQLDatabase {
	
	
	public static void main(String []args){
	// Create connection and statement
	Connection conn = null;
	Statement stmt = null;
	try {
	      Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	      String url = "jdbc:mysql://localhost/mysql";
	      conn = DriverManager.getConnection(url, "username", "password");

	      stmt = conn.createStatement();
	      String userSQL = "CREATE DATABASE USERS";
	      String photoSQL = "CREATE DATABASE PHOTOS";
	      String hashtagSQL = "CREATE DATABASE HASHTAGS";
	      stmt.executeUpdate(photoSQL);
	      stmt.executeUpdate(userSQL);
	      stmt.executeUpdate(hashtagSQL);
	    } catch (Exception e) {
	      e.printStackTrace();
	} finally {
		try {
			if(stmt!=null){
				stmt.close();
			} 
		}
		catch(SQLException sql){
			// Do NOTHING
		}
		try {
			if(conn!=null){
				conn.close();
			}
		}
		catch(SQLException sqlc){
			sqlc.printStackTrace();
		}
			
	}
	
	
	
	
		// close database
		System.out.println("Database is closed");
	}

	private static void CreateTables() {
		// TODO CREATE TABLES FOR 3 DATABASES;
		
		
		
	} // ENDS MAIN
} // ENDS CLASS
