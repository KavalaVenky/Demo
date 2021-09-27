
package com.TaskProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Update() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		String name =request.getParameter("name");
		ServletContext sc = getServletContext();
		String email = (String)sc.getAttribute("email");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","vENKY@123");
			
			PreparedStatement psref=con.prepareStatement("update practicehtml set name=? where email=?");
			psref.setString(1,name);
			psref.setString(2,email);
			psref.executeUpdate();
			
			out.print("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>Updated  Successful</h1></center></body></html>");
			}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
