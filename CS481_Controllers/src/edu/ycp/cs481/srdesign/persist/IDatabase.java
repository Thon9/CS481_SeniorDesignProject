package edu.ycp.cs481.srdesign.persist;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.Photo;

public interface IDatabase {

	
		// Returns the user based on an ID
		public User getUserID(int id) throws SQLException;
		
		// Returns the user based on the userName
		public User getUserString(String username) throws SQLException;
		
		// Creating account with user's credentials
		public boolean createAccount(String username, String password, 
				String firstname, String lastname, String email) throws SQLException;
		
		// Creating account with a user
		public boolean createAccountUser(User user) throws SQLException;
		
		//Removes user from database
		public boolean deleteUser(int userID) throws SQLException;
		
		// Add new photo to the SQLDatabase - mediumblob
		public boolean addPhoto(String fileName, FileInputStream fis, long filelength) throws SQLException;

		// Login handling for the User
		public User login(String username, String password) throws SQLException;
		
		// Simply checks to see if UserName already exists
		public boolean checkExistence(String username) throws SQLException;

		// execute transaction
		Boolean execute(Connection conn) throws SQLException;
		
		// 	Add new photo
		public void addPhoto(Photo newPhoto);
		
		public ArrayList<Photo> getPhotos();

		public ArrayList<Photo> getUserUploadedPhotos(int uID);
		
		public ArrayList<Photo> getUserFollowingPhotos(int uID, int hashtagID);
		
		public Photo getPhotoByID(int pID);

		boolean addHashtag(String hashtagname, int userID, String username) throws SQLException;

		public void addHashtag(HashTag hashtag);

		boolean addHashtagtoDatabase(String hashtagname) throws SQLException;
		
		//Adds relation of hashtag to photo in database
		public void addRelaHTP(int hashtagID, int photoID);

		
}

