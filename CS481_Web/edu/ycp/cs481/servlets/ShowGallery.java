package edu.ycp.cs481.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.PhotoUI;
import edu.ycp.cs481.srdesign.controllers.AddPhotoController;
import edu.ycp.cs481.srdesign.controllers.AutoCompleteHashTag;
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
    	ArrayList<String> hashTags = new ArrayList<String>();
    	
    	//get list of string to autocomplete the textbox
    	HttpSession session = request.getSession();
    	AutoCompleteHashTag autoCompleteController = new AutoCompleteHashTag();
    	try {
    		hashTags = autoCompleteController.getAutoCompleteSearch();
    		//add the quotation for each tags
    		for (int i=0;i<hashTags.size();i++){
    			hashTags.set(i, "\""+hashTags.get(i)+"\"");
    		}
    		session.setAttribute("autocomplete", hashTags);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	//save the hashtag to the session for following
    	session.setAttribute("hashTag", subject);
		
    	System.out.println(subject);
    	ArrayList<Photo>temp = new ArrayList<Photo>();
		ArrayList<PhotoUI> paths = new ArrayList<PhotoUI>();
		
		
	
		if (subject == null || subject.isEmpty()){
			System.out.println("no search subject detect showing gallery normally...");
			// Print message 
		}
		
		//searching gallery 
		else {
			System.out.println("searching for "+subject+"...");
			GetPhotosByHashtagString getPhotos = new GetPhotosByHashtagString();
			try {
				temp = getPhotos.getPhotos(subject);
				System.out.println("The size of the temp array is " + temp.size());
			
			} catch (SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
		Iterator<Photo> iter = temp.iterator();
		while (iter.hasNext()){
			newPhoto = iter.next();
			System.out.println("The photo ID is " + newPhoto.getphotoID());
			paths.add(new PhotoUI("image/"+newPhoto.getphotoID(), "editPhoto/"+newPhoto.getphotoID()));
		}
		
		request.setAttribute("search_object", hashTags);
		request.setAttribute("photoList", paths);
		request.getRequestDispatcher("/gallery.jsp").forward(request, response); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}
	
}
