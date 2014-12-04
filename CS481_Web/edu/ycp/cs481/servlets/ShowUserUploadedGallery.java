package edu.ycp.cs481.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.controllers.GetPhotosByHashtagString;
import edu.ycp.cs481.srdesign.controllers.GetPhotosByUserID;

/**
 * Servlet implementation class
*/
@WebServlet("/ShowUserUploadedGallery")
@MultipartConfig
public class ShowUserUploadedGallery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ArrayList for photos user uploaded
	public ArrayList<Photo> temp = new ArrayList<Photo>();
	public Photo newPhoto = new Photo();
	    @Override
		protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

	    	
	    	
	    	// ArrayList for photopaths to display photos
			ArrayList<String> paths = new ArrayList<String>();
			// Get User Uploaded Photos controller
			GetPhotosByUserID getUserUploads = new GetPhotosByUserID();	
			// Session variables
			HttpSession session = request.getSession();
			int uID = (int) session.getAttribute("userID");
			try {
				temp = getUserUploads.getUserPhotos(uID);
				// Print to console, how many images the user has uploaded
				System.out.println("The user has uploaded " + temp.size() + " images");
				for(int i = 0; i < temp.size(); i++){
					newPhoto = temp.get(i);
					paths.add("image/"+newPhoto.getphotoID());
				
				}
			} catch (SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
				

			// if temp.size==0, NO PHOTOS
			// PRINT OUT MESSAGE ON WEBSITE
			
			
			for(int i = 0; i < temp.size(); i++){
				//temp.get(i).getphotoID()	
				//System.out.println(temp.get(i))
				//paths.add("image/"+i);
				System.out.println("The PHOTO ID of temp photo " + i + " is " + temp.get(i).getphotoID());
				// NEEDS TO BE FIXED, HARDCORDED
				paths.add("image/"+(temp.get(i).getphotoID()-temp.size()+i+1));			
			}
			
			// Store photos as photolist

			//request.setAttribute("photoList", images);

			request.setAttribute("photoList", paths);
			request.getRequestDispatcher("/userGallery.jsp").forward(request, response); 
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
}
