package edu.ycp.cs481.srdesign;

import static org.junit.Assert.*;

import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;

public class PhotoTest {

	Photo pic_1, image_B;
	
	@Before
	public void setUp(){
		pic_1 = new Photo();
		image_B = new Photo(1, 0, null, 13);
		
		pic_1.setuserID(1);
		pic_1.setphotoID(0);
		pic_1.setFIS(null);
		pic_1.setFileLength(234);
		
	}
	
	@Test
	public void testGetIDs() {
		assertEquals(0, pic_1.getphotoID());
		
		assertNotEquals(image_B.getphotoID(), pic_1.getphotoID());
		
		assertEquals(1, image_B.getphotoID());
	}
	
	@Test
	public void testGetUserIDs() {
		assertEquals(1, pic_1.getuserID());
		
		assertNotEquals(image_B.getuserID(), pic_1.getuserID());
		
		assertEquals(0, image_B.getuserID());
	}

	@Test
	public void testFileInfo() {
		assertNull(pic_1.getFIS());
		assertEquals(13,image_B.getFileLength());
		
		assertEquals(image_B.getFIS(), pic_1.getFIS());
		assertNotEquals(image_B.getFileLength(), pic_1.getFileLength());
		
		assertNull(image_B.getFIS());
		assertEquals(234,pic_1.getFileLength());
	}
}
