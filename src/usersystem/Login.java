package usersystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}




	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubtry
		try {
			// 本番環境では変更の可能性あり
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/user", "root", "jikken2016");
			String name = request.getParameter("name");
			String input_password = request.getParameter("password");
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM user where name = " + "'"+name +"'";
			ResultSet rs = stmt.executeQuery(sql);
			response.setContentType("text/html");
			response.setCharacterEncoding("Shift-JIS");
			if (rs.next()) {
				String password = rs.getString("name");
				if(!password.equals(input_password)){
					response.getWriter().println("パスワードが違う");
				}
			}else{
				response.getWriter().println("おまえは誰だ");
			}
			HttpSession session = request.getSession(true);
			session.setAttribute("name", name);
			stmt.close();
			con.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/appointment.jsp");
			dispatcher.forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
