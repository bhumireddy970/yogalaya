import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class model {
	
	private String name;
	private String email;
	private String password;
	private String role;
	private Connection connect=null;
	private PreparedStatement ps=null;
	private int row;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public int register()
	{
		try {
			connect=jdbc.getDBConnection();
			
			String sql="INSERT INTO registration(name,email,password,role) VALUES(?,?,?,?)";
			ps=connect.prepareStatement(sql);
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, role);
			
			row=ps.executeUpdate();
			
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
		
		
		return row;
	}
	
}
