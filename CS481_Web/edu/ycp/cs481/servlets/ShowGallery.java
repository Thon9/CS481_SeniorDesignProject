

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
	static ArrayList<Photo> temp;
	
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	
    	//System.out.println("Creating get all photos controller");
    	//GetAllPhotosController getCont = new GetAllPhotosController();
    	
    	String subject = request.getParameter("search_object");
    	
    	System.out.println(subject);
    	temp = null;
		ArrayList<String> paths = new ArrayList<String>();
		//show gallery normally
		
	
		if (subject == null || subject.isEmpty()){
			// PRINT OUT NOTHING TO SEARCH FOR ON PAGE
			System.out.println("no search subject detect showing gallery normally...");
			for(int i=0; i<temp.size(); i++){
				//System.out.println(temp.get(i).getFile().toPath());
				paths.add("image/"+i);
			}
		}
		
		//searching gallery 
		else {
			System.out.println("searching for "+subject+"...");
			GetPhotosByHashtagString getPhotos = new GetPhotosByHashtagString();
			try {
				// Creating arraylist of photos based on search subject
				System.out.println("Should be adding photos containing subect " + subject);
				temp = getPhotos.GetPhotosByHashtagString(subject);	
				
			} catch (SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(temp.size());
			/*
			for(int i = 0; i < temp.size(); i++){
				//InputStream is = temp.get(i).getFIS();
				System.out.println(temp.get(i).getphotoID());
				System.out.println(temp.get(i).getuserID());
				System.out.println(temp.get(i).getFIS());
				
				// File path
				File newImage = new File("C:\\imagesFolder\\"+subject+"\\"+temp.get(i).getphotoID());
				String formatName="image/jpeg";
				// Write image to filePath
				BufferedImage bufferedImage= ImageIO.read(temp.get(i).getFIS());
				ImageIO.write(bufferedImage,formatName,newImage);
				
				if (newImage.toString().contains(subject)){
					//remove the extension
					String path = newImage.getName().toString();
					if (path.indexOf(".") > 0){
						path = path.substring(0, path.lastIndexOf("."));
					}
					paths.add("image/"+path);
					System.out.println("doGet found "+path);
				}	
			}
			*/
		}

	
		// Store photos as photolist

		//request.setAttribute("photoList", images);

		request.setAttribute("photoList", paths);
		request.getRequestDispatcher("/gallery.jsp").forward(request, response); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}
	
}

