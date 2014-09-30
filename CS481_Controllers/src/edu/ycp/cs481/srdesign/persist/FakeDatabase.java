package edu.ycp.cs481.srdesign.persist;

import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.User;

public class FakeDatabase implements IDatabase {

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verifyAccount(String userName, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewUser(User user) {
		// TODO Auto-generated method stub
		
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
	public void addHashtag(HashTag hashtag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkExistence(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
