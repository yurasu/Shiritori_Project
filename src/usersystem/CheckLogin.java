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

@WebServlet("/Check_Login")
public class CheckLogin extends HttpServlet{
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost"); // ここは個人のサーバ環境によって異なる．
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String userName = mapper.readValue(request.getParameter("username"),
				String.class);
		String password = mapper.readValue(request.getParameter("password"),
				String.class);
		try {
			String sendMsg = "No";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/user", "root", "jikken2016");
			String sql = "select * from user where name =" + "'" + userName
					+ "';";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if(rs.getString("password").equals(password)){
					sendMsg = "OK";
				}
			}
			stmt.close();
			con.close();
			out.println(mapper.writeValueAsString(sendMsg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
