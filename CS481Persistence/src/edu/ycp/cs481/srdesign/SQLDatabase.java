
package edu.ycp.cs481.srdesign;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.ycp.cs481.srdesign.persist.IDatabase;

public class SQLDatabase implements IDatabase {
	User user = new User();
	
	// Database path
	private String DATABASE_PATH = "C:/Users/ChrisDavisSrDesign/git/CS481";
	
	// Create connection and statement
	Connection conn = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;


	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) throws SQLException {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw e;
		}
	}

	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
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
						// Deadlock: retry (unless max retry count has been reached)
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

	private Connection connect() throws SQLException {
		System.out.println("Connecting to database " + DATABASE_PATH);
		Connection conn = DriverManager.getConnection("jdbc:mysql:" + DATABASE_PATH + ";create=true"); 
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	@Override
	public Boolean execute(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getUserID(int userid) throws SQLException  {
			return executeTransaction(new Transaction<User>() {
				@Override
				public User execute(Connection conn) throws SQLException {
					try{
						// Prepare statement
						preparedStatement = conn.prepareStatement("SELECT * FROM USERS WHERE id = userid");
						// Execute Query
						resultSet = preparedStatement.executeQuery();
						
						while(resultSet.next()){
							
							getUser(user, resultSet, 1);
			
						}
						
					} catch (SQLException e){
						e.printStackTrace();
					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(preparedStatement);
					}
					return user;
				}

			});
		
	}

	@Override
	public User getUserString(String username) throws SQLException {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				try{
					// Prepare statement
					preparedStatement = conn.prepareStatement("SELECT * FROM USERS WHERE USERNAME = username");
					// Execute Query
					resultSet = preparedStatement.executeQuery();
					
					while(resultSet.next()){
						
						getUser(user, resultSet, 1);
		
					}
					
				} catch (SQLException e){
					e.printStackTrace();
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(preparedStatement);
				}
				return user;
			}

		});	
}

	@Override
	public boolean createAccount(String username, String password, int userID,
			String firstname, String lastname, String email) throws SQLException {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				try{
					preparedStatement = conn.prepareStatement(
							"INSERT INTO USERS values (username,password, firstname, lastname, email");
					preparedStatement.executeUpdate();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
				finally
				{
					DBUtil.closeQuietly(preparedStatement);
				}
			return true;
			}
		});
	}


	@Override
	public void deleteUser(int userID) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addPhoto(String fileName, InputStream content) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean verifyAccount(String userName, String password) throws SQLException {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {	
				try{
					preparedStatement = conn.prepareStatement("select * from USERS where USERNAME=userName AND PASSWORD=password");
				
					resultSet = preparedStatement.executeQuery();
					
					if(!resultSet.next()){
						// invalid User parameters, User could not be found
						return null;
					}
					
				
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(preparedStatement);
				}
				return true;
			}

		});
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



	// DATABASE UTILITY METHODS
	private void getUser(User user, ResultSet resultSet, int index) throws SQLException {
		user.setuserID(resultSet.getInt(index));	
		user.setUserName(resultSet.getString(index));
		user.setPassword(resultSet.getString(index));
		user.setFirstName(resultSet.getString(index));
		user.setLastName(resultSet.getString(index));
		user.setUserEmail(resultSet.getString(index));
	}


	
}

    
    

		




