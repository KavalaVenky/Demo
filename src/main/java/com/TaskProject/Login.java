package com.TaskProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		ServletContext sc = getServletContext();
		sc.setAttribute("email",email);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","vENKY@123");
			
			PreparedStatement psref=con.prepareStatement("select email,password from practicehtml where email=? and password=?");
			psref.setString(1, email);
			psref.setString(2, password);
			ResultSet rs = psref.executeQuery(); 
			
			if(rs.next()) {
				out.println("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>LogIn Successful</h1></center></body></html>");

				RequestDispatcher rd = request.getRequestDispatcher("Operations.html");
				rd.include(request, response);
				
			}
			else {
				out.println("<html><body><body style =\"background-color:AliceBlue;\"/><center><br><br><br><br><h1>LogIn Failed</h1></center></body></html>");
			   
				RequestDispatcher rd1 = request.getRequestDispatcher("Login.html");
				rd1.include(request, response);
			}
			}
			
			catch(Exception e)
			{
				System.out.println(e);
			}
	}

}
