package usersystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class CheckUser
 */
@WebServlet("/Check_User")
public class CheckUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost"); // ここは個人のサーバ環境によって異なる．
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String userName = mapper.readValue(request.getParameter("requestJs"),String.class);
		try {
			String sendMsg = "OK";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/user", "root", "jikken2016");
			String sql = "select * from user where name =" + "'" + userName
					+ "';";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				sendMsg = "No";
			}
			stmt.close();
			con.close();
			out.println(mapper.writeValueAsString(sendMsg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
