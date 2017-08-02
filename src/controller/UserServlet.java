package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Branch;
import beans.Position;
import beans.User;
import service.BranchService;
import service.PositionService;
import service.UserService;

@WebServlet(urlPatterns = { "/usermanagement" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Branch> Branch = new BranchService().getBranch();

		request.setAttribute("Branches", Branch);

		List<Position> Position = new PositionService().getPosition();

		request.setAttribute("Positions", Position);

		List<User> User = new UserService().getUsers();

		request.setAttribute("users", User);

		request.getRequestDispatcher("/usermanagement.jsp").forward(request, response);

	}
}