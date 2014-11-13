
package edu.ycp.cs481.srdesign;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.ycp.cs481.srdesign.persist.IDatabase;

public class SQLDatabase implements IDatabase {
User user = new User();
Photo photo = new Photo();
HashTag hashtag = new HashTag();

private ArrayList<User>users;
private ArrayList<Photo> photos;
private ArrayList<HashTag>hashtags;
private int userID = 1;


public SQLDatabase(){
	System.out.println("SQL CONSTRUCTOR");
	users = new ArrayList<User>();
	photos = new ArrayList<Photo>();
	hashtags = new ArrayList<HashTag>();
	initPhotos();
	
}
private String DATABASE_PATH = "jdbc:mysql://localhost/cs481";

	// Create connection and statement
	Connection conn;
	Statement statement;
	
	ResultSet resultSet;


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

private Connection connect() throws SQLException{
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	// System.out.println("Connecting to database " + DATABASE_PATH);
	Connection conn = DriverManager.getConnection(DATABASE_PATH, "root", "password"); 
	
	return conn;
}

@Override
public Boolean execute(Connection conn) throws SQLException {
	conn.commit();
	return true;
}
// WORKING
@Override
public User login(final String username, final String password) throws SQLException {
	return executeTransaction(new Transaction<User>() {
		@Override
		public User execute(Connection conn) throws SQLException {	
			PreparedStatement preparedStatement = null;
			try{
				System.out.println("Creating PreparedStatement");
				preparedStatement = connect().prepareStatement("SELECT * FROM USERS where USERNAME=? AND PASSWORD=?");
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, password);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
					System.out.println("Username is " +  resultSet.getString("USERNAME"));
					getUser(user, resultSet);
				} else {
					System.out.println("NO USERS AVAIBLE");
				}
								
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			return user;
		}

	});
}

// WORKING - NEED TO GET RID OF EVENTUALLY, SHOULD ONLY ADD USER BY USER, NOT ACCOUNT DETAILS
@Override
public boolean createAccount(final String username,final String password,final String 
		firstname,final  String lastname,final String email) throws SQLException {
	return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			
			PreparedStatement preparedStatement = null;
			try{
				preparedStatement = conn.prepareStatement(
						"INSERT INTO USERS (USERNAME, PASSWORD, "
						+ "FIRSTNAME, LASTNAME, EMAIL) VALUES (?, ?, ?, ?, ?)");
					// Set Values to be inserted into DB
					System.out.println("Setting values to be inserted");
						preparedStatement.setString(1, username);
						preparedStatement.setString(2, password);
						preparedStatement.setString(3, firstname);
						preparedStatement.setString(4, lastname);
						preparedStatement.setString(5, email);
					// Update DATABASE
					preparedStatement.executeUpdate();
					System.out.println("Check database to see if updated");
			}
			finally
			{
				DBUtil.closeQuietly(preparedStatement);
				DBUtil.closeQuietly(conn);
			}
		return true;
		}
	});
}

// Adds hashtag to database if does not exist!
// Implemented - Need Controller to TEST
@Override
public boolean addHashtag(final HashTag hashtag) throws SQLException {
	return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			try{
				preparedStatement = conn.prepareStatement(
						"INSERT INTO HASHTAGS (HASHTAGNAME) VALUES (?)");
						preparedStatement.setString(1, hashtag.gethashtagName());
				preparedStatement.executeUpdate();
			}
			finally
			{
				DBUtil.closeQuietly(preparedStatement);
				DBUtil.closeQuietly(conn);
			}
		return true;
		}
	});
	
}

// Implemented - Need Controller to TEST
@Override
public boolean deleteUser(final int userID) throws SQLException {
	return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			try{
				// Prepare statement
				preparedStatement = conn.prepareStatement("DELETE FROM USERS WHERE id=?");
					preparedStatement.setInt(1, userID);
				// Execute Query
				resultSet = preparedStatement.executeQuery();
				System.out.println("User with ID of " + userID + " deleted");
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
		return true;
		}

	});	
	
}

//Implemented - Need to be TESTED
@Override
public boolean checkExistence(final String username) throws SQLException {
	
	return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			boolean flag = false;
			try{
				// Prepare statement
				preparedStatement = conn.prepareStatement("SELECT * FROM USERS WHERE USERNAME =?");
					preparedStatement.setString(1, username);
				// Execute Query
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet != null){
					flag = true;
				}
				
			} catch (SQLException e){
				e.printStackTrace();
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			return flag;
		}

	});	
}

// Implemented - NEEDS TO BE TESTED
@Override
public boolean addPhoto(final Photo newPhoto) throws SQLException {
	return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			
			PreparedStatement preparedStatement = null;
			try{
				// CREATE FILE INPUT STREAM FROM FILE
				preparedStatement = conn.prepareStatement(
						"INSERT INTO PHOTOS (USERID, PHOTO) VALUES (?, ?)");
					// Set Values to be inserted into DB
					System.out.println("Setting values FOR USERID");
					preparedStatement.setInt(1, newPhoto.getuserID());
					System.out.println("Setting values FOR PHOTO");
					preparedStatement.setBinaryStream(2, newPhoto.getFIS(), newPhoto.getFileLength());
					// Update DATABASE
					preparedStatement.executeUpdate();
					int userID = 1;
					System.out.println("Check database to see if updated");
			}
			finally
			{
				DBUtil.closeQuietly(preparedStatement);
				DBUtil.closeQuietly(conn);
			}
		return true;
		}
	});
}

	
// Implemented - NEED TO FINISH UTILITY METHOD
@Override
public ArrayList<Photo> getUserUploadedPhotos(final int uID) throws SQLException {
	return executeTransaction(new Transaction<ArrayList<Photo>>() {
		ArrayList<Photo> photos = new ArrayList<Photo>();
		@Override
		public ArrayList<Photo> execute(Connection conn) throws SQLException {	
			PreparedStatement preparedStatement = null;
			try{
				preparedStatement = connect().prepareStatement("SELECT * FROM PHOTOS where id=?");
					preparedStatement.setInt(1, uID);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
					getPhotos(photo, resultSet);
					photos.add(photo);
				} else {
					System.out.println("NO PHOTOS FROM USER");
				}
								
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			return photos;
		}

	});
}

@Override
public ArrayList<Photo> getUserFollowingPhotos(int uID, int hashtagID) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void addRelaHTP(int hashtagID, int photoID) {
	// TODO Auto-generated method stub
	
}

@Override
public String getHashtagByID(int id) throws SQLException {
	return executeTransaction(new Transaction<String>() {
		@Override
		public String execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			try{
				// Prepare statement
				preparedStatement = conn.prepareStatement("SELECT * FROM HASHTAGS WHERE id = id");
				// Execute Query
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet != null){
					getHashtags(hashtag, resultSet);
				}
				
			} catch (SQLException e){
				e.printStackTrace();
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			return hashtag.gethashtagName();
		}

	});

}

@Override
public int getHashtagByName(String hashtagName) throws SQLException {
	return executeTransaction(new Transaction<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			try{
				// Prepare statement
				preparedStatement = conn.prepareStatement("SELECT * FROM HASHTAGS WHERE HASHTAGNAME = hashtagName");
				// Execute Query
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet != null){
					getHashtags(hashtag, resultSet);
				}
				
			} catch (SQLException e){
				e.printStackTrace();
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			return hashtag.gethashtagID();
		}

	});

}

// Implemented, NEED TO TEST
@Override
public User getUserID(int userid) throws SQLException  {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement preparedStatement = null;
				try{
					// Prepare statement
					preparedStatement = conn.prepareStatement("SELECT * FROM USERS WHERE id = userid");
					// Execute Query
					resultSet = preparedStatement.executeQuery();
					
					if(resultSet != null){
						getUser(user, resultSet);
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

//NOT CURRENTLY USING
@Override
public User getUserString(String username) throws SQLException {
	
	return executeTransaction(new Transaction<User>() {
		@Override
		public User execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatementps = null;
			try{
				// Prepare statement
				preparedStatementps = conn.prepareStatement("SELECT * FROM USERS WHERE USERNAME = username");
				// Execute Query
				resultSet = preparedStatementps.executeQuery();
				
				while(resultSet.next()){
					
					getUser(user, resultSet);
	
				}
				
			} catch (SQLException e){
				e.printStackTrace();
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatementps);
			}
			return user;
		}

	});	
}

// Implemented, Need to Test
@Override
public boolean createAccountUser(final User user) throws SQLException {
	return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			try{
				preparedStatement = conn.prepareStatement("INSERT INTO USERS (USERNAME, PASSWORD, "
					+ "FIRSTNAME, LASTNAME, EMAIL) VALUES (?, ?, ?, ?, ?)");
					// Set Values to be inserted into DB
					System.out.println("Setting values to be inserted");
					preparedStatement.setString(1, user.getUserName());
					preparedStatement.setString(2, user.getPassword());
					preparedStatement.setString(3, user.getFirstName());
					preparedStatement.setString(4, user.getLastName());
					preparedStatement.setString(5, user.getUserEmail());
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

// Implemented and Working
@Override
public boolean addPhoto(final String fileName, final FileInputStream fis, final long filelength) throws SQLException {
	return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			
			PreparedStatement preparedStatement = null;
			try{
				// CREATE FILE INPUT STREAM FROM FILE
				preparedStatement = conn.prepareStatement(
						"INSERT INTO PHOTOS (USERID, PHOTO) VALUES (?, ?)");
					// Set Values to be inserted into DB
					System.out.println("Setting values FOR USERID");
					preparedStatement.setInt(1, 1);
					System.out.println("Setting values FOR PHOTO");
					preparedStatement.setBinaryStream(2, fis, filelength);
					
					// Update DATABASE
					preparedStatement.executeUpdate();
					System.out.println("Check database to see if updated");
			}
			finally
			{
				DBUtil.closeQuietly(preparedStatement);
				DBUtil.closeQuietly(conn);
			}
		return true;
		}
	});
}
/**
 * 
 * METHODS FROM FAKEDATABASE TO HAVE A WORKING SYSTEM
 * 
 * 
 * 
 */



private void initPhotos(){
	if(!(new File("C:\\imagesFolder\\")).isDirectory()){
		(new File("C:\\imagesFolder\\")).mkdirs();
	}else{
		
			File directory = new File("C:\\imagesFolder\\");
			for (File file : directory.listFiles()) {
			    if (file.isFile()) {
			       if (file.getName().endsWith(".jpg")||file.getName().endsWith(".png")) {
			    	   System.out.println("Adding a photo");
			    	   photos.add(new Photo());
			       }
			    } 
			}
		}

}
@Override
public ArrayList<Photo> getPhotos(){
	ArrayList<Photo> PICS = new ArrayList<Photo>();
	
	//for(int i=0;i<users.size(); i++){
		File directory = new File("C:\\imagesFolder\\");

		for (File file : directory.listFiles()) {
		    if (file.isFile()) {
		       if (file.getName().endsWith(".jpg")||file.getName().endsWith(".png")) {
		           System.out.println(file.getAbsolutePath());
		           PICS.add(new Photo());
		       }
		    } 
		}
	//}
	return PICS;
}
public ArrayList<Photo> getUserPhotos(int uID) {
	ArrayList<Photo> userPhotos = new ArrayList<Photo>();
	for(int i=0; i<photos.size();i++){
		if(photos.get(i).getuserID()==uID){
			userPhotos.add(photos.get(i));
		}
	}
	return userPhotos;
}
@Override
public Photo getPhotoByID(int pID) {
	return photos.get(pID);
}


////////////  UTILITY METHODS  ///////////////
private void getUser(User user, ResultSet resultSet) throws SQLException {
	user.setuserID(resultSet.getInt("id"));	
	user.setUserName(resultSet.getString("USERNAME"));
	user.setPassword(resultSet.getString("PASSWORD"));
	user.setFirstName(resultSet.getString("FIRSTNAME"));
	user.setLastName(resultSet.getString("LASTNAME"));
	user.setUserEmail(resultSet.getString("EMAIL"));
}

private void getPhotos(Photo photo, ResultSet resultSet) throws SQLException {
	photo.setFileLength(resultSet.getLong(""));
	photo.setFIS((FileInputStream) resultSet.getBinaryStream(""));
	photo.setphotoID(resultSet.getInt("id"));
	photo.setuserID(resultSet.getInt("USERID"));
}

private void getHashtags(HashTag hashtag, ResultSet resultSet) throws SQLException {
	hashtag.sethashtagName(resultSet.getString("HASHTAGNAME"));
	hashtag.sethashtagID(resultSet.getInt("id"));
}



}



    
    

		




