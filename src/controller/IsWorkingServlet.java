package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

	@WebServlet(urlPatterns = { "/isworking" })
	public class IsWorkingServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		int is_working =Integer.parseInt(request.getParameter("is_working"));
		new UserService().is_woking(id,is_working);

		response.sendRedirect("usermanagement");

	}
}