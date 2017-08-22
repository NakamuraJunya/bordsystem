package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import service.UserService;

@WebFilter(urlPatterns = {"/*"})
public class IsWorkingFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String path = ((HttpServletRequest)request).getServletPath();

		HttpSession session = ((HttpServletRequest)request).getSession();
		User user = (User) session.getAttribute("loginUser");

		if(!path.equals("/login")) {
			if (user != null){
				User newLoginUser = new UserService().getUser(user.getId());

				if (newLoginUser.getIs_working() !=1){
					List<String> errows = new ArrayList<String>();
					errows.add("ログインに失敗しました");
					session.setAttribute("errorMessages", errows);

					((HttpServletResponse) response).sendRedirect("login");
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) {

	}

	@Override
	public void destroy() {
	}

}
