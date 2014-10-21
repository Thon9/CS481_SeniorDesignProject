
package edu.ycp.cs481.srdesign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import edu.ycp.cs481.srdesign.persist.IDatabase;

public class SQLDatabase implements IDatabase {
	 private String DATABASE_PATH = null;
	 private int MAX_ATTEMPTS = 10;
		
		// Create connection and statement
		Connection conn = null;
		Statement stmt = null;
		try {
		      Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		      String url = "jdbc:mysql://localhost/mysql";
		      conn = DriverManager.getConnection(url, "root", "password");

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
		@Override
		public User getUserID(int id) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public User getUserString(String username) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean createAccount(String username, String password,
				int userID, String firstname, String lastname, String email) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void deleteUser(int userID) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void addPhoto(Photo photo) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public User login(String username, String password) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean verifyAccount(String userName, String password) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean checkExistence(String username) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void createAccountUser(User user) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void addHashtag(HashTag hashtag) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
	}
	
	interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	
	
	 
	 public <ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
			try {
				return doExecuteTransaction(txn);
			} catch (SQLException e) {
				throw new SQLException("Transaction failed", e);
			}
		}

		public <ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
			Connection conn = connect();
			
			try {
				int numAttempts = 0;
				boolean success = false;
				ResultType result = null;
				
				while (!success && numAttempts < MAX_ATTEMPTS) {
					try {
						result = txn.execute(conn);
						conn.commit();
						success = true;
					} catch (SQLException e) {
						if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
							numAttempts++;
						} else {
							// Some other kind of SQLException
							throw e;
						}
					}
				}
				
				if (!success) {
					throw new SQLException("Transaction failed (too many retries)");
				}
				
				// Success!
				return result;
			} finally {
				DBUtil.closeQuietly(conn);
			}
		}	
	 Connection connect() throws SQLException {
		 	System.out.println("Connecting to database " + url );
			Connection conn = DriverManager.getConnection("jdbc:mysql" + url + ";create=true"); 
			
			conn.setAutoCommit(false);
			
			return conn;
		}

	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"create table users (" +
							"  id integer " +
							"  userName varchar(20) " +
							"  password varchar(20)" +
							"  firstName varchar(20) " +
							"  lastName varchar(20) " +
							"  emailAddress varchar(40)" +
							")"
					);
					stmt.executeUpdate();
					
					stmt = conn.prepareStatement(
							"create table hashtags (" +
							"  hashtagName varchar(30)" +
							"  id integer " +
							"  description varchar(100)" +
							")"	
					);
					stmt.executeUpdate();
					
					stmt = conn.prepareStatement(
							"create table photos(" +
							"  photoID integer " +
							"  userID integer ," +
							"  likeCount integer " +
							" imagePath varchar(60)" +
							")"
					);
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
				
			}
		});
	}
	}
		
	 


