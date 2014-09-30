package edu.ycp.cs481.srdesign;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	User userTest, userTest2;
	
	@Before
	public void setUp() throws Exception {
		userTest = new User();
		userTest2 = new User();
		
		userTest.setuserID(0);
		userTest.setFirstName("John");
		userTest.setLastName("Smith");
		userTest.setPassword("wordOfThePass");
		userTest.setUserEmail("JSmith1337@ycp.edu");
		
		userTest2.setuserID(1);
		userTest2.setFirstName("Jane");
		userTest2.setLastName("Doe");
		userTest2.setPassword("passoFtHewOrD");
		userTest2.setUserEmail("JDoe1337@ycp.edu");
		
	}
	
	@Test
	public void testGetUserID(){
		assertEquals(0, userTest.getuserID());
		
		assertNotEquals(userTest2.getuserID(), userTest.getuserID());
		
		assertEquals(1, userTest2.getuserID());
	}

	@Test
	public void testGetName() {
		assertEquals("John", userTest.getFirstName());
		assertEquals("Jane", userTest2.getFirstName());
		
		assertEquals("Doe", userTest2.getLastName());
		assertEquals("Smith", userTest.getLastName());
		
		assertNotEquals("John", userTest2.getFirstName());
		
		assertNotEquals("Doe", userTest.getLastName());
	}

	@Test
	public void testGetPassword(){
		assertEquals("wordOfThePass", userTest.getPassword());
		
		assertNotEquals(userTest2.getPassword(), userTest.getPassword());
		
		assertEquals("passoFtHewOrD", userTest2.getPassword());
	}
	
	@Test
	public void testGetUserEmail(){
		assertEquals("JDoe1337@ycp.edu", userTest2.getUserEmail());
		
		assertNotEquals(userTest.getUserEmail(), userTest2.getUserEmail());
		
		assertEquals("JSmith1337@ycp.edu", userTest.getUserEmail());
	}
}
