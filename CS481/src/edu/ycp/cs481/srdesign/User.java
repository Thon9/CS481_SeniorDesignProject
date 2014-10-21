package edu.ycp.cs481.srdesign;

import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable {

		private static final long serialVersionUID = 1L;
		private String userName;
		private String password;
		private String Email;
		private String firstName;
		private String lastName;
		private int userID;
		
		public User() {
		   	 
			 
		}
		
		public void setuserID(int id){
			this.userID = id;
		}
		
		public int getuserID(){
			return userID;
		}
		
		
		// Set/Get User Name
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUserName() {
			return userName;
		}
					
		// Set/Get User password
		public void setPassword(String password){
			this.password = password;
		}
		public String getPassword(){
			return password;
		}
		
		
		// Set/Get User email
		public void setUserEmail(String email){
			this.Email = email;
		}
		public String getUserEmail(){
			return Email;
		}
		
		
		// Set/Get first name of user
		public void setFirstName(String firstName){
			this.firstName = firstName;
		}
		public String getFirstName(){
			return firstName;
		}
		
		
		// Set/Get last name of user
		public void setLastName(String lastName){
			this.lastName = lastName;
		}
		public String getLastName(){
			return lastName;
		}
		
		
}
