package edu.ycp.cs481.srdesign;

import java.io.FileOutputStream;
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
	ArrayList<User>users;
	ArrayList<Photo> photos;
	ArrayList<HashTag>hashtags;
	private int userID = 1;
	// Store an image in here
	private Blob image;
	
	public FakeDatabase(){
		
	users = new ArrayList<User>();
	photos = new ArrayList<Photo>();
	hashtags = new ArrayList<HashTag>();
	
		
		User test = new User();
			test.setFirstName("Test");
			test.setLastName("Testy");
			test.setPassword("password");
			test.setUserName("TestUser");
			test.setUserEmail("Test@Test.com");
			test.setuserID(userID);
			users.add(test);
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

	@Override
	public boolean addPhoto(String fileName, InputStream content) {
		//photos.add(photo);
		OutputStream OStream = null;
		try{
			OStream = new FileOutputStream("/CS481Persistence/edu/ycp/cs481/srdesign/Images/"+fileName);
			
			int read=0;
			byte[] data = new byte[1024];
			
			while((read = content.read(data)) !=-1){
				OStream.write(data, 0, read);
			}
			/**************************************/
			//add things for adding new id fields
			//Photo p = new Photo();
			/**************************************/
			
			System.out.println("Data transfer complete!!");
		}catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			if (content != null) {
				try {
					content.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (OStream != null) {
				try {
					// outputStream.flush();
					OStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	 
			}
		}
		return true;
		
	}

	@Override
	public Boolean execute(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addHashtag(String hashtagname, int userID, String username)
			throws SQLException {
		return false;
	}

	@Override
	public boolean createAccountUser(User user) {
		users.add(user);
		return true;
	}

	@Override
	public void addHashtag(HashTag hashtag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean createAccount(String username, String password,
			String firstname, String lastname, String email)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
