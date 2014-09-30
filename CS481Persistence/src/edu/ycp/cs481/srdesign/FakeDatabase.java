package edu.ycp.cs481.srdesign;

import java.util.ArrayList;

import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.persist.IDatabase;


public class FakeDatabase implements IDatabase {
	ArrayList<User>users = new ArrayList<User>();
	ArrayList<Photo> photos = new ArrayList<Photo>();
	ArrayList<HashTag>hashtags = new ArrayList<HashTag>();
	
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
			createAccountUser(user);
			userID++;
			return true;
		}
	}

	@Override
	public boolean verifyAccount(String username, String password) {
		User user = null;
		user = getUserString(username);
		if (user == null) {
			return false;
		} else if (!user.getPassword().equals(password)) {
			System.out.println("Incorrect password for " + username);
			return false;
		}
		return true;
	}

	@Override
	public User login(String username, String password) {
		for (User user : users) {
			if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		// No such username/password
		return null;
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
	public void createAccountUser(User user) {
		users.add(user);
	}

	@Override
	public void deleteUser(int userID) {
		for (User user : users){
			if(user.getuserID() == userID){
				users.remove(userID);
			}
		}
		
	}

	@Override
	public void addPhoto(Photo photo) {
		photos.add(photo);
		
	}

	@Override
	public void addHashtag(HashTag hashtag) {
		hashtags.add(hashtag);
		
	}

}
