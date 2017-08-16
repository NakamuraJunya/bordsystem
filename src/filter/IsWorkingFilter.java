//package filter;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//
//import beans.User;
//import service.UserService;
//
//@WebFilter(urlPatterns = {"/*"})
//public class IsWorkingFilter implements Filter {
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//
//		List<User> User = new UserService().getUsers();
//		System.out.println(User);
////
////			if (User.getIs_working() !=1) {
////				((HttpServletResponse) response).sendRedirect("login");
////				System.out.println("aaa");
////				return;
////			}
//		chain.doFilter(request, response);
//	}
//
//	@Override
//	public void init(FilterConfig config) {
//
//	}
//
//	@Override
//	public void destroy() {
//	}
//
//}
