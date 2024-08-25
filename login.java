

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static Connection connect;

	public static PreparedStatement ps;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		
		
		
		try {
			connect = jdbc.getDBConnection();
			
			ps=connect.prepareStatement("select name from registration where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				response.sendRedirect("success.jsp");
				
				String name=rs.getString(1);
				HttpSession session = request.getSession();
				
				session.setAttribute("name", name);
			}
			else
			{
				response.sendRedirect("login.html");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			 try {
				jdbc.closeResource(connect, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
