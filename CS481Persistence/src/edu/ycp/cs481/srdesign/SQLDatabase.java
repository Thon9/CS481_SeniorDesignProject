package edu.ycp.cs481.srdesign;

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


public SQLDatabase(){
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
				preparedStatement = conn.prepareStatement("SELECT * FROM USERS where USERNAME=? AND PASSWORD=?");
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

// WORKING
@Override
public boolean createAccountUser(final User user) throws SQLException {
	return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			try{
				preparedStatement = conn.prepareStatement(
						"INSERT INTO USERS (USERNAME, PASSWORD, "
						+ "FIRSTNAME, LASTNAME, EMAIL) VALUES (?, ?, ?, ?, ?)");
					// Set Values to be inserted into DB
					
					preparedStatement.setString(1, user.getUserName());
					preparedStatement.setString(2, user.getPassword());
					preparedStatement.setString(3, user.getFirstName());
					preparedStatement.setString(4, user.getLastName());
					preparedStatement.setString(5, user.getUserEmail());
					// INSERT INTO DATABASE
					preparedStatement.executeUpdate();
					// Console print statement
					System.out.println("Account for " + user.getUserName() + " created!");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			finally
			{
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
		return true;
		}
	});
}

// Adds hashtag to database if does not exist!
// Implemented - Need Controller to TEST
@Override
public int addHashtag(final HashTag hashtag) throws SQLException {
	return executeTransaction(new Transaction<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			int newHashID;
			try{
				preparedStatement = conn.prepareStatement(
						"INSERT INTO HASHTAGS (HASHTAGNAME) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
						preparedStatement.setString(1, hashtag.gethashtagName());
						preparedStatement.executeUpdate();
						
						try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				            if (generatedKeys.next()) { 
				            	newHashID = generatedKeys.getInt(1);
				            }
				            else {
				                throw new SQLException("Creating user failed, no ID obtained.");
				            }
				        }
						
						System.out.println(newHashID);
			}
			finally
			{
				DBUtil.closeQuietly(preparedStatement);
				DBUtil.closeQuietly(conn);
			}
		return newHashID;
		}
	});
	
}


@Override
public ArrayList<Photo> getUserSearchPhotos(final String hashtagstring) throws SQLException {
	return executeTransaction(new Transaction<ArrayList<Photo>>() {
		@Override 
		public ArrayList<Photo> execute(Connection conn) throws SQLException {
			ArrayList<Photo> searchPhotos = new ArrayList<Photo>();
			PreparedStatement preparedStatement = null;
			try{
				// Return a resultset That contains the photos from the hashtags the user is following.	
				// CORRECT PREPARESTATEMENT
				preparedStatement = conn.prepareStatement("SELECT p.id,P.USERID,P.PHOTO FROM HASHTAGS H join PHOTOHASHTAG ph on h.HASHTAGNAME=?"
				+ "AND h.id=ph.HASHTAGID JOIN PHOTOS p ON ph.PHOTOID=p.id");
				//String prepareString = new String("'" + hashtagstring + "'");
				preparedStatement.setString(1, hashtagstring);
 				resultSet = preparedStatement.executeQuery();
 				while(resultSet.next()){
 					Photo newPhoto = new Photo();
					getPhoto(newPhoto, resultSet);
					searchPhotos.add(newPhoto);
					System.out.println("Adding new photo to arrayList that user searched for");
				}
			}
			finally
			{
				DBUtil.closeQuietly(preparedStatement);
				DBUtil.closeQuietly(conn);
			}
			return searchPhotos;
		
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
				preparedStatement = conn.prepareStatement("DELETE FROM USERS WHERE id = ?");
					preparedStatement.setInt(1, userID);
				// Execute Query
				preparedStatement.executeUpdate();
				System.out.println("User with ID of " + userID + " deleted");
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
		return true;
		}

	});	
	
}

@Override
public boolean userUnfollowHashtag(final int hashtagID,final int uID) throws SQLException {
	return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			try{
				// Prepare statement
				preparedStatement = conn.prepareStatement("DELETE FROM USERHASHTAG WHERE USERID=? AND HASHTAGID=?");
					preparedStatement.setInt(1, uID);
					preparedStatement.setInt(2, hashtagID);
				// Execute Query
				preparedStatement.executeUpdate();
				System.out.println("User is now unfollowing hashtag with ID of " + hashtagID);
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
		return true;
		}
	});	
	
}


@Override
public boolean checkUserFollowingHashtag(final int hashtagID, final int uID) throws SQLException {
	return executeTransaction(new Transaction<Boolean>() {
		HashTag hashtag = new HashTag();
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			boolean flag = false;
			try{	
				// Prepare statement
				preparedStatement = conn.prepareStatement("SELECT H.ID, H.HASHTAGNAME FROM HASHTAGS H JOIN USERHASHTAG U WHERE U.USERID=? AND U.HASHTAGID=?");
				preparedStatement.setInt(1, uID);
				preparedStatement.setInt(2, hashtagID);
				// Execute Query
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()){
					getHashtags(hashtag, resultSet);			
					System.out.println("user is following hashtag");
					flag = true;
				}
			} catch (SQLException e){
				e.printStackTrace();
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			System.out.println(flag);
			return flag;
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
				
				if(resultSet.next()){
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
public int addPhoto(final Photo newPhoto) throws SQLException {
	return executeTransaction(new Transaction<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException {
			
			PreparedStatement preparedStatement = null;
			int newPhotoID;
			try{
				// CREATE FILE INPUT STREAM FROM FILE
				preparedStatement = conn.prepareStatement(
						"INSERT INTO PHOTOS (USERID, PHOTO) VALUES (?, ?)",Statement.RETURN_GENERATED_KEYS);
					// Set Values to be inserted into DB
					System.out.println("Setting "+newPhoto.getuserID()+" FOR USERID");
					preparedStatement.setInt(1, newPhoto.getuserID());
					System.out.println("Setting values FOR PHOTO");
					preparedStatement.setBinaryStream(2, newPhoto.getFIS(), newPhoto.getFileLength());
					
					
					
					// Update DATABASE
					preparedStatement.executeUpdate();
					
					try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
			            if (generatedKeys.next()) {
			            	newPhotoID = generatedKeys.getInt(1);
			            }
			            else {
			                throw new SQLException("Creating user failed, no ID obtained.");
			            }
			        }
					System.out.println("Check database to see if updated:	"+newPhotoID);
			}
			finally
			{
				DBUtil.closeQuietly(preparedStatement);
				DBUtil.closeQuietly(conn);
			}
		return newPhotoID;
		}
	});
}

// Implemented - NEED to TEST
@Override
public ArrayList<Photo> getUserUploadedPhotos(final int uID) throws SQLException {
	return executeTransaction(new Transaction<ArrayList<Photo>>() {
		@Override
		public ArrayList<Photo> execute(Connection conn) throws SQLException {	
			ArrayList<Photo> photos = new ArrayList<Photo>();
			PreparedStatement preparedStatement = null;
			try{
				preparedStatement = conn.prepareStatement("SELECT id,USERID, PHOTO FROM PHOTOS WHERE USERID =?");
					preparedStatement.setInt(1, uID);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()){
					Photo newPhoto = new Photo();
					getPhoto(newPhoto,resultSet);
					System.out.println("adding a photo to the arrayList");
					photos.add(newPhoto);
				} 			
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			return photos;
		}

	});
}

// should be good to go!
@Override
public ArrayList<Photo> getUserFollowingPhotos(final int uID) throws SQLException {
	return executeTransaction(new Transaction<ArrayList<Photo>>() {
		
		@Override
		public ArrayList<Photo> execute(Connection conn) throws SQLException {	
			PreparedStatement preparedStatement = null;
			ArrayList<Photo> photos = new ArrayList<Photo>();
			try{
				// Return a resultset That contains the photos from the hashtags the user is following.	

				preparedStatement = conn.prepareStatement("SELECT P.ID, P.USERID, P.PHOTO FROM PHOTOS P JOIN USERHASHTAG U ON U.USERID=? JOIN PHOTOHASHTAG PH ON PH.HASHTAGID=U.HASHTAGID "
						+ "AND PH.PHOTOID=P.ID");
				preparedStatement.setInt(1, uID);
				// Execute Search
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()){
					Photo photo = new Photo();
					System.out.println("Retrieving a photo");
					getPhoto(photo, resultSet);
					photos.add(photo);
					System.out.println("Adding a photo to the ArrayList");
				}
			}
			finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			// Prints out number of photos
			System.out.println("The number of photos is " + photos.size());
			return photos;
		}

	});
}

// Related a photo to a Hashtag
@Override
public boolean addRelaHTP(final int hashtagID, final int photoID) {
	try {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement preparedStatement = null;
				try{
					// Prepare statement
					preparedStatement = conn.prepareStatement("INSERT INTO PHOTOHASHTAG (PHOTOID, HASHTAGID) VALUES (?, ?)");
					
					// Set values to be inserted
					preparedStatement.setInt(1, photoID);
					preparedStatement.setInt(2, hashtagID);
					
					// Execute Update
					preparedStatement.executeUpdate();
					
				} finally {
					DBUtil.closeQuietly(preparedStatement);
					DBUtil.closeQuietly(conn);
				}
				return true;
			}

		});
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	
	
	
}

// IMPLEMENTED, NEEDS TO BE TESTED
@Override
public int checkHashtagExistance(final String hashtagName) throws SQLException {
	return executeTransaction(new Transaction<Integer>() {
		HashTag hashtag = new HashTag();
		@Override
		public Integer execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			
			
			try{
				
				// Prepare statement
				preparedStatement = conn.prepareStatement("SELECT ID, HASHTAGNAME FROM HASHTAGS WHERE HASHTAGNAME=?");
				preparedStatement.setString(1, hashtagName);
				// Execute Query
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()){
					getHashtags(hashtag, resultSet);			
					System.out.println("Hashtag found with name of " + hashtagName);
				}
				
			} catch (SQLException e){
				e.printStackTrace();
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			if(hashtag == null){
				return 0;
			} else {
				System.out.println("The hashtag with a name of " + hashtagName + " has an id of " + hashtag.gethashtagID());
				return hashtag.gethashtagID();
			}
		}

	});

}

// Implemented- NEEDS TO BE TESTED
@Override
public boolean deletePhoto(final int PhotoID) throws SQLException {
	return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			PreparedStatement preparedStatement2 = null;
			try{
				// Prepare statement
				preparedStatement = conn.prepareStatement("DELETE FROM USERS WHERE id = ?");
					preparedStatement.setInt(1, PhotoID);
				// Execute Query
				preparedStatement.executeUpdate();
				
				// PreparedStatement for PhotoHashtag
				preparedStatement2 = conn.prepareStatement("DELETE FROM PHOTOHASHTAG WHERE PHOTOID = ?");
				preparedStatement2.setInt(1, PhotoID);
				preparedStatement2.executeUpdate();
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
				DBUtil.closeQuietly(preparedStatement2);
			}
		return true;
		}

	});	
	
}


// Implemented - NEED TO TEST
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

//USED FOR AUTOCOMPLETE FEATURE IN SEARCH
@Override
public ArrayList<String> returnAllHashtags() throws SQLException {
	return executeTransaction(new Transaction<ArrayList<String>>() {
		@Override
		public ArrayList<String> execute(Connection conn) throws SQLException {
			ArrayList<String> hashtags = new ArrayList<String>();
			PreparedStatement preparedStatement = null;
			try{
				// Prepare statement
				preparedStatement = conn.prepareStatement("SELECT * FROM HASHTAGS");
				// Execute Query
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()){
					HashTag hashtag = new HashTag();
					getHashtags(hashtag, resultSet);
					hashtags.add(hashtag.gethashtagName());	
				}
				
			} catch (SQLException e){
				e.printStackTrace();
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			return hashtags;
		}

	});

}

// USED FOR AUTOCOMPLETE FEATURE IN SEARCH
@Override
public ArrayList<String> autoCompleteSearch(final String entered) throws SQLException {
	return executeTransaction(new Transaction<ArrayList<String>>() {
		ArrayList<String> hashtags = new ArrayList<String>();
		
		@Override
		public ArrayList<String> execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			try{
				// Prepare statement
				preparedStatement = conn.prepareStatement("SELECT * FROM HASHTAGS WHERE HASHTAGENAME LIKE '?%');");
				preparedStatement.setString(1, entered);
				// Execute Query
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
					HashTag hashtag = new HashTag();
					getHashtags(hashtag, resultSet);
					hashtags.add(hashtag.gethashtagName());	
				}
				
			} catch (SQLException e){
				e.printStackTrace();
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			//hashtags.add(" ");
			return hashtags;
		}

	});

}

// Implemented - NEED TO TESET
@Override
public boolean deleteHashtagFromPhoto(final int photoID,final int hashtagID) throws SQLException {
	return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			try{
				// Prepare statement
				preparedStatement = conn.prepareStatement("DELETE FROM PHOTOHASHTAG WHERE PHOTOID=? AND HASHTAGID=?");
					preparedStatement.setInt(1, photoID);
					preparedStatement.setInt(2, hashtagID);
				// Execute Query
				preparedStatement.executeUpdate();

			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
		return true;
		}

	});	
	
}

@Override
public boolean addFollowHashtagToUser(final int hashtagID, final int uID) throws SQLException {
	return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			try{
		
				preparedStatement = conn.prepareStatement("INSERT INTO USERHASHTAG (USERID, HASHTAGID) VALUES (?, ?)");
				preparedStatement.setInt(1, uID);
				preparedStatement.setInt(2, hashtagID);				
				preparedStatement.executeUpdate();
			
				} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
		return true;
		}

	});	
	
}

@Override
public boolean checkPhotoHashtagIDRelation(final int photoID, final int hashtagID) throws SQLException {
	return executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
			boolean value = false;
			PreparedStatement preparedStatement = null;			
			ArrayList<HashTag> hashtags = new ArrayList<HashTag>();
				try{
					// Return a resultset That contains the hashtags from a single photo
					preparedStatement = conn.prepareStatement("SELECT H.ID, H.HASHTAGNAME FROM HASHTAGS H JOIN PHOTOHASHTAG P WHERE P.PHOTOID=? AND H.ID=P.HASHTAGID");
					preparedStatement.setInt(1, photoID);
					// Execute Search
					resultSet = preparedStatement.executeQuery();
					while(resultSet.next()){
						HashTag hashtag = new HashTag();
						getHashtags(hashtag, resultSet);
						hashtags.add(hashtag);
					}
				}
				finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(preparedStatement);
				}
				for(int i = 0; i <hashtags.size(); i++){
					if(hashtags.get(i).gethashtagID()==hashtagID){
						value = true;
					}
				}
				return value;
			}
		});
	}

// Implemented - NEED TO TEST
@Override
public ArrayList<HashTag> getHashtagsFromPhoto(final int photoID) throws SQLException {
	return executeTransaction(new Transaction<ArrayList<HashTag>>() {
		@Override
		public ArrayList<HashTag> execute(Connection conn) throws SQLException {	
			PreparedStatement preparedStatement = null;
			ArrayList<HashTag> hashtags = new ArrayList<HashTag>();
			try{
				// Return a resultset That contains the hashtags from a single photo
				preparedStatement = conn.prepareStatement("SELECT H.ID, H.HASHTAGNAME FROM HASHTAGS H JOIN PHOTOHASHTAG P WHERE P.PHOTOID=? AND H.ID=P.HASHTAGID");
				preparedStatement.setInt(1, photoID);
				// Execute Search
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()){
					HashTag hashtag = new HashTag();
					getHashtags(hashtag, resultSet);
					hashtags.add(hashtag);
				}
			}
			finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			// Prints out number of photos
			System.out.println("The number of hashtags on the photo with an id of " + photoID + " is " + hashtags.size());
			return hashtags;
		}
	});
}

// Implemented - NEED TO TEST
@Override
public int getHashtagByName(final String hashtagName) throws SQLException {
	return executeTransaction(new Transaction<Integer>() {
		int return_value = 0;
		HashTag hashtag = new HashTag();
		@Override
		public Integer execute(Connection conn) throws SQLException {
			PreparedStatement preparedStatement = null;
			try{
				// Prepare statement
				preparedStatement = conn.prepareStatement("SELECT * FROM HASHTAGS WHERE HASHTAGNAME=?");
				// Execute Query
				preparedStatement.setString(1, hashtagName);
				System.out.println("Searching for hashtag with name of "+ hashtagName);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
					getHashtags(hashtag, resultSet);
					System.out.println("Hashtag name is " + hashtag.gethashtagName() + " and hashtagID is " + hashtag.gethashtagID());
				}
				if (hashtag == null){
					System.out.println("Could not find hashtag");
				}
				else {
					return_value = hashtag.gethashtagID();
				}
			} catch (SQLException e){
				e.printStackTrace();
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			return return_value;
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

@Override
public Photo getPhotoByID(final int pID, boolean x) throws SQLException{
	return executeTransaction(new Transaction<Photo>() {
		//ArrayList<Photo> photos = new ArrayList<Photo>();
		Photo ReqPhoto = new Photo();
		@Override
		public Photo execute(Connection conn) throws SQLException {	
			PreparedStatement preparedStatement = null;
			try{
				// Return a resultSet That contains the photos from the hashtags the user is following.	
				preparedStatement = conn.prepareStatement("SELECT * FROM PHOTOS WHERE ID = ?");
				// Execute Search
				preparedStatement.setInt(1, pID);
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.first()){
					getPhoto(ReqPhoto, resultSet);	
				}
			}
			finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(preparedStatement);
			}
			// Prints out number of photos
			return ReqPhoto;
		}

	});
}


////////////  UTILITY METHODS  ///////////////
private void getPhoto(Photo photo, ResultSet resultSet) throws SQLException {	
	photo.setphotoID(resultSet.getInt("id"));
	photo.setuserID(resultSet.getInt("USERID"));
	photo.setFIS(resultSet.getBinaryStream("PHOTO"));
}

private void getUser(User user, ResultSet resultSet) throws SQLException {
	user.setuserID(resultSet.getInt("id"));	
	user.setUserName(resultSet.getString("USERNAME"));
	user.setPassword(resultSet.getString("PASSWORD"));
	user.setFirstName(resultSet.getString("FIRSTNAME"));
	user.setLastName(resultSet.getString("LASTNAME"));
	user.setUserEmail(resultSet.getString("EMAIL"));
}

private void getHashtags(HashTag hashtag, ResultSet resultSet) throws SQLException {
	hashtag.sethashtagName(resultSet.getString("HASHTAGNAME"));
	hashtag.sethashtagID(resultSet.getInt("id"));
}







}



    
    

		




