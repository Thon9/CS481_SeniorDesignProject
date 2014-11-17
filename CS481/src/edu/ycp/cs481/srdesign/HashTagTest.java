package edu.ycp.cs481.srdesign;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HashTagTest {
	HashTag hashtag, hashtag_2;
	
	@Before
	public void setUp(){
		hashtag = new HashTag();
		hashtag_2 = new HashTag();
		
		hashtag.sethashtagID(0);
		hashtag.sethashtagName("test_1");
		
		hashtag_2.sethashtagID(1);
		hashtag_2.sethashtagName("test_2");
	}
	
	@Test
	public void testID() {
		assertEquals(0, hashtag.gethashtagID());
		
		assertNotEquals(hashtag_2.gethashtagID(), hashtag.gethashtagID());
		
		assertEquals(1, hashtag_2.gethashtagID());
	}

	@Test
	public void testStringValue() {
		assertEquals("test_1", hashtag.gethashtagName());
		
		assertNotEquals(hashtag_2.gethashtagName(), hashtag.gethashtagName());
		
		assertEquals("test_2", hashtag_2.gethashtagName());
		
		assertNotEquals("Something that it shouldn't be <--", hashtag.gethashtagName());
		assertNotEquals("It could be this.. but no really i shouldn't be but ok >.<", hashtag.gethashtagName());
	}
}
