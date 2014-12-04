package edu.ycp.cs481.srdesign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.persist.IDatabase;


public class FakeDatabase implements IDatabase {
	private ArrayList<User>users;
	private ArrayList<Photo> photos;
	private ArrayList<HashTag>hashtags;
	private int userID = 1;

	public FakeDatabase(){
		
		users = new ArrayList<User>();
		photos = new ArrayList<Photo>();
		hashtags = new ArrayList<HashTag>();
		
		//numPhotos = 0;
		//numUsers = 0;
		 
		User test = new User();
		test.setFirstName("Test");
		test.setLastName("Testy");
		test.setPassword("password");
		test.setUserName("TestUser");
		test.setUserEmail("Test@Test.com");
		test.setuserID(userID);
		users.add(test);

		initPhotos();
	}
	
	private void initPhotos(){
		if(!(new File("C:\\imagesFolder\\")).isDirectory()){
			(new File("C:\\imagesFolder\\")).mkdirs();
		}else{
			for(int i=0;i<users.size(); i++){
				File directory = new File("C:\\imagesFolder\\");
				for (File file : directory.listFiles()) {
				    if (file.isFile()) {
				       //FileInputStream fis = directory
				    	if (file.getName().endsWith(".jpg")||file.getName().endsWith(".png")) {
				           photos.add(new Photo());
				       }
				    } 
				}
			}
		}
	}
	

	@Override
	public User getUserID(int id) {
		for (User user : users){
			if(user.getuserID() == id){
				return user;
			}
		}
		return null;
	}
	
	@Override
	public User getUserString(String username) {
		for(User user : users){
			if(user.getUserName().equals(username)){
				return user;
			}
		}
		return null;
	}
	
	public boolean createAccount(String username, String password, int userID ,String firstname, String lastname, String email){
		User existing = getUserString(username);
		
		if(existing != null){
			System.out.println("Account already exists");
			return false;
		} else {
			System.out.println("Creating account for user: " + username + ", pass: " + password );
			User user = new User();
			user.setUserName(username);
			user.setPassword(password);
			user.setuserID(userID);
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setUserEmail(email);
			users.add(user);
			userID++;
			return true;
		}
	}

	@Override
	public User login(String username, String password) {
		User loggedIn = null;
		for (User user : users) {
			if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
				loggedIn = user;
			}
		}
		return loggedIn;
	}

	@Override
	public boolean checkExistence(String username) {
		for (User user : users) {
			if (user.getUserName().equals(username)) {
				return true;
			}
		}
		return false;
	}


	@Override
	public boolean deleteUser(int userID) {
		for (User user : users){
			if(user.getuserID() == userID){
				users.remove(userID);
			}
		}
		return true;
	}
/*	
	@Override
	public ArrayList<Photo> getPhotos(){
		ArrayList<Photo> PICS = new ArrayList<Photo>();
		
		for(int i=0;i<users.size(); i++){
			File directory = new File("C:\\imagesFolder\\");
		
			for (File file : directory.listFiles()) {
			    if (file.isFile()) {
			       if (file.getName().endsWith(".jpg")||file.getName().endsWith(".png")) {
			           //System.out.println(file.getAbsolutePath());
			           PICS.add(new Photo());
			       }
			    } 
			}
		}
		return PICS;
	}
*/
	@Override	
	public int addPhoto(Photo newPhoto) {

		OutputStream OStream = null;
		try{
			File newImage = new File("C:\\imagesFolder\\"+photos.size()+".jpg");
			
			if(!newImage.exists()) {
				newImage.createNewFile();
			} 
			OStream = new FileOutputStream(newImage, false); 
			
			
			int read=0;
			byte[] data = new byte[1024];
			
			while((read = newPhoto.getFIS().read(data)) !=-1){
				OStream.write(data, 0, read);
			}
			this.photos.add(newPhoto);
			
			System.out.println("Data transfer complete!!");
		}catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			if (newPhoto.getFIS() != null) {
				try {
					newPhoto.getFIS().close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (OStream != null) {
				try {
					OStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	 
			}
		}

		return 0;
		

		
	}

	@Override
	public Boolean execute(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createAccountUser(User user) {
		users.add(user);
		return true;
	}

	public void addHashtag1(HashTag hashtag) {
		// TODO Auto-generated method stub
		
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
/*	
	@Override
	public Photo getPhotoByID(int pID) {
		return photos.get(pID);
	}
	public int getNumPhoto() {
		return numPhotos;
	}

	public void setNumPhoto(int numPhoto) {
		this.numPhoto = numPhoto;
	}
*/

	@Override
	public ArrayList<Photo> getUserUploadedPhotos(int uID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Photo> getUserFollowingPhotos(int uID, int hashtagID)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHashtagByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHashtagByName(String hashtagName) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean addRelaHTP(int hashtagID, int photoID) {
		// TODO Auto-generated method stub
		return false;
	}
/*
	@Override
	public ArrayList<Integer> getUserSearchPhotos(String hashtagstring)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Override
	public Photo getPhotoByID(int pID, boolean x) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addHashtag(HashTag hashtag) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkHashtagExistance(String hashtagName) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deletePhoto(int PhotoID) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<HashTag> getHashtagsFromPhoto(int photoID)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> autoCompleteSearch(String entered)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteHashtagFromPhoto(int photoID, int hashtagID)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Photo> getUserSearchPhotos(String hashtagstring)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean addFollowHashtagToUser(int hashtagID, int uID)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}



	
}



