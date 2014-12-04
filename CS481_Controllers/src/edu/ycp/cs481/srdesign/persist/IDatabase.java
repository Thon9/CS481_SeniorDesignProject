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
		
		// Creating account with a user
		public boolean createAccountUser(User user) throws SQLException;
		
		//Removes user from database
		public boolean deleteUser(int userID) throws SQLException;
		
		// Add new photo to the SQLDatabase - mediumblob
		//public boolean addPhoto(String fileName, FileInputStream fis, long filelength) throws SQLException;

		// Login handling for the User
		public User login(String username, String password) throws SQLException;
		
		// Simply checks to see if UserName already exists
		public boolean checkExistence(String username) throws SQLException;

		// execute transaction
		Boolean execute(Connection conn) throws SQLException;
		
		// 	Add new photo
		public int addPhoto(Photo newPhoto) throws SQLException;
		
		// Get Photos Uploaded by User
		public ArrayList<Photo> getUserUploadedPhotos(int uID) throws SQLException;
		
		// Get Photos User is Following
		public ArrayList<Photo> getUserFollowingPhotos(int uID, int hashtagID) throws SQLException;
		
		public ArrayList<Photo> getUserSearchPhotos(String hashtagstring) throws SQLException;
		
		// Get Hashtags Based On PhotoID
		public ArrayList<HashTag> getHashtagsFromPhoto(int photoID) throws SQLException;
		
		public ArrayList<String> autoCompleteSearch (String entered) throws SQLException;
		/* TESTING PURPOSES ONLY
		public ArrayList<Photo> getPhotos();
		*/
		
		// FAKE DATABASE
		public Photo getPhotoByID(int pID);
		
		// Add Hashtag to HashTagTable
		public int addHashtag(HashTag hashtag) throws SQLException;
		
		// Return name of hashtag based on it
		public String getHashtagByID(int id) throws SQLException;
		
		// Return the id of hashtag based on string
		public int getHashtagByName(String hashtagName) throws SQLException;
		
		//Adds relation of hashtag to photo in database
		public boolean addRelaHTP(int hashtagID, int photoID);

		public ArrayList<Photo> getPhotos();

		//public ArrayList<Photo> getUserPhotos(int uID);

		Photo getPhotoByID(int pID, boolean x) throws SQLException;



		
}

