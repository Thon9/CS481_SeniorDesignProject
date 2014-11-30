

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
       
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("Creating get all photos controller");
    	GetAllPhotosController getCont = new GetAllPhotosController();
    	
    	String subject = request.getParameter("search_object");
    	
    	System.out.println(subject);
    	List<Photo> temp = getCont.getAllPhotos();
    	System.out.println(temp.size());
    	System.out.println("temp should contain all of the photos");
		List<String> paths = new ArrayList<String>();
		
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
			// retrive photo blobs
			GetPhotosByHashtagString getPhotos = new GetPhotosByHashtagString();
			try {
				temp = getPhotos.Photo(subject);
				//System.out.println(temp.size());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Convert temp Photos into IMAGES
			for(int i = 0; i < temp.size(); i++){
				// convert each photo into an actauly image
				BufferedImage buffer = null;
				InputStream is = temp.get(i).getFIS();
				buffer = ImageIO.read(is);
				/*
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				int nRead;
				byte[] data = new byte[16384];
				while ((nRead = is.read(data, 0, data.length)) != -1) {
				  buffer.write(data, 0, nRead);
				}

				buffer.flush();
				buffer.toByteArray();
				*/
			}
			
			
			
			
			//ArrayList<Photo> gallery  = getCont.getAllPhotos();
			//for (Photo photo : gallery){
				/*
				if (photo.getFile().getName().toString().contains(subject)){
					//remove the extension
					String path = photo.getFile().getName().toString();
					if (path.indexOf(".") > 0){
						path = path.substring(0, path.lastIndexOf("."));
					}
					paths.add("image/"+path);
					System.out.println("doGet found "+path);
				}
			*/
			//}
		}
		
		//request.setAttribute("photoList", images);
		request.setAttribute("photoList", paths);
		request.getRequestDispatcher("/gallery.jsp").forward(request, response); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}
	
}

