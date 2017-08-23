package filter;

import java.io.IOException;

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


@WebFilter(urlPatterns = {"/*"})
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException{

		HttpSession session = ((HttpServletRequest)request).getSession();
		System.out.println(((HttpServletRequest) request).getServletPath());

		if(!((HttpServletRequest) request).getServletPath().equals("/login") && !((HttpServletRequest) request).getServletPath().equals("/css/login.css")){

			if (session.getAttribute("loginUser") == null){
				((HttpServletResponse) response).sendRedirect("login");
				return;
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