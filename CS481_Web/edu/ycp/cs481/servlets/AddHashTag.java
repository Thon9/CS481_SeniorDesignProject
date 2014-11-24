package edu.ycp.cs481.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// import org.apache.catalina.connector.Request;




import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.controllers.AddHashtagController;

/**
 * Servlet implementation class 
 */
@WebServlet("/AddHashTag")
public class AddHashTag extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashTag newHashTag = new HashTag();
		//TODO: get the data of the hashTag

		//if valid hashtag
		if (newHashTag != null){
			AddHashtagController controller = new AddHashtagController();
			//add hashtag to database
			try {
				controller.addHashtag(newHashTag);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//if invalid hashtag e.g. empty hashTag
		else {
			request.setAttribute("result", "enter a proper hashTag");
			this.doGet(request, response);
		}
	}
}

