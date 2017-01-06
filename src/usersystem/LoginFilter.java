package usersystem;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	HttpSession session;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession(true);
		System.out.println("filte");

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		System.out.println("filte");
		try {
			if (session != null) {
				System.out.println("aaaaa");
				chain.doFilter(request, response);
			} else {
				System.out.println("セッションがありません");
				((HttpServletResponse) response).sendRedirect("/Shiritori_Project/Login.jsp");

			}
		} catch (ServletException se) {

		} catch (IOException e) {
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

}