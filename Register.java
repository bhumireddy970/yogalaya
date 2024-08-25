

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String role=request.getParameter("role");
		
		model md=new model();
		
		md.setEmail(email);
		md.setName(name);
		md.setPassword(password);
		md.setRole(role);
		
		int row=md.register();
		
		HttpSession session = request.getSession();
		
		session.setAttribute("name", name);
		
		if(row==0)
		{
			response.sendRedirect("/RegisterMVC/register.html");
		}
		else
		{
			response.sendRedirect("/RegisterMVC/login.html");
		}
	}

}
