

package edu.ycp.cs481.servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.controllers.AddPhotoController;
import edu.ycp.cs481.srdesign.controllers.GetAllPhotosController;
import edu.ycp.cs481.srdesign.controllers.GetPhotosByHashtagString;

/**
 * Servlet implementation class
*/
@WebServlet("/ShowGallery")
@MultipartConfig
public class ShowGallery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Photo newPhoto = new Photo();
	
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    	String subject = request.getParameter("search_object");

    	//save the hashtag to the session
    	HttpSession session = request.getSession();
		session.setAttribute("hashTag", subject);
		
    	System.out.println(subject);
    	ArrayList<Photo>temp = new ArrayList<Photo>();
		ArrayList<String> paths = new ArrayList<String>();
		
		
	
		if (subject == null || subject.isEmpty()){
			System.out.println("no search subject detect showing gallery normally...");
			// Print message 
		}
		
		//searching gallery 
		else {
			System.out.println("searching for "+subject+"...");
			GetPhotosByHashtagString getPhotos = new GetPhotosByHashtagString();
			try {
				//temp=null;
				// Creating arraylist of photos based on search subject
				//System.out.println("Should be adding photos containing subect " + subject);
				//System.out.println(temp);
				temp = getPhotos.getPhotos(subject);
				
				System.out.println("The size of the temp array is " + temp.size());
				/*
				for(int i = 0; i < temp.size(); i++){
					System.out.println("Counter is at " + i);
					System.out.println("PhotoID is " + temp.get(i).getphotoID());
					System.out.println("User ID is " + temp.get(i).getuserID());
				}
				*/
				//System.out.println("The temp size is " + temp.size());
			} catch (SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		// if temp.size==0, NO PHOTOS
		// PRINT OUT MESSAGE ON WEBSITE
		
		Iterator<Photo> iter = temp.iterator();
		while (iter.hasNext()){
			newPhoto = iter.next();
			//System.out.println(newPhoto.getuserID());
			
			// NEEDS TO BE FIXED, HARDCORDED
			System.out.println("The photo ID is " + newPhoto.getphotoID());
			paths.add("image/" + newPhoto.getphotoID());
			//paths.add("image/"+(temp.get(i).getphotoID()-temp.size()+i+1));			
		}
		
		// Store photos as photolist

		//request.setAttribute("photoList", images);

		request.setAttribute("photoList", paths);
		request.getRequestDispatcher("/gallery.jsp").forward(request, response); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}
	
}

/*
/InputStream is = temp.get(i).getFIS();
System.out.println(temp.get(i).getphotoID());
System.out.println(temp.get(i).getuserID());
System.out.println(temp.get(i).getFIS());

//
//File newImage = new File("C:\\imagesFolder\\"+subject+"\\"+temp.get(i).getphotoID());
//String formatName="image/jpeg";
// Write image to filePath
//BufferedImage bufferedImage= ImageIO.read(temp.get(i).getFIS());
//ImageIO.write(bufferedImage,formatName,newImage);

if (newImage.toString().contains(subject)){
	//remove the extension
	String path = newImage.getName().toString();
	if (path.indexOf(".") > 0){
		path = path.substring(0, path.lastIndexOf("."));
	}
	paths.add("image/"+path);
	System.out.println("doGet found "+path);
}	
*/
