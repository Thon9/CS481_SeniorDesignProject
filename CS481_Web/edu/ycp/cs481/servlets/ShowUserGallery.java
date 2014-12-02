

package edu.ycp.cs481.servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
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
import edu.ycp.cs481.srdesign.controllers.GetPhotosByUserID;

/**
 * Servlet implementation class
*/
@WebServlet("/ShowUserGallery")
@MultipartConfig
public class ShowUserGallery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    	ArrayList<Photo>temp = new ArrayList<Photo>();
    	ArrayList<String> paths = new ArrayList<String>();
		
    	//get user id
    	HttpSession session = request.getSession();
    	int userID = (int) session.getAttribute("userID");
		//get the user uploaded photos
    	GetPhotosByUserID getPhotos = new GetPhotosByUserID();
		 temp = getPhotos.getUserPhotos(userID);
		if (temp == null){
			System.out.println("SUG: user has no uploaded photos");
		}
		else {
			System.out.println(temp.size());
			for(int i = 0; i < temp.size(); i++){
				System.out.println("SUG: Counter is at " + i);
				System.out.println("SUG: PhotoID is " + temp.get(i).getphotoID());
				System.out.println("SUG: User ID is " + temp.get(i).getuserID());
			}
			//System.out.println("The temp size is " + temp.size());
		
			
			for(int i = 0; i < temp.size(); i++){
				System.out.println("SUG: The PHOTO ID of temp photo " + i + " is " + temp.get(i).getphotoID());
				// NEEDS TO BE FIXED, HARDCORDED
				paths.add("image/"+(temp.get(i).getphotoID()-temp.size()+i+1));			
			}
		}
		request.setAttribute("photoList", paths);
		request.getRequestDispatcher("/userGallery.jsp").forward(request, response); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}
	
}
