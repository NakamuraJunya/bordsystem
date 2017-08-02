package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.Branch;
import beans.Position;
import beans.User;
import service.BranchService;
import service.PositionService;
import service.UserService;

@WebServlet(urlPatterns = { "/settings" })
@MultipartConfig(maxFileSize = 100000)
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<User> User = new UserService().getUsers();

		request.setAttribute("users", User);

		List<Branch> Branch = new BranchService().getBranch();

		request.setAttribute("Branches", Branch);

		List<Position> Position = new PositionService().getPosition();

		request.setAttribute("Positions", Position);
		request.getRequestDispatcher("settings.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		if (isValid(request, messages) == true) {

			User user = new User();
			user.setName(request.getParameter("name"));
			user.setLogin_id(request.getParameter("login_id"));
			user.setPassword(request.getParameter("password"));
			user.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
			user.setPosition_id(Integer.parseInt(request.getParameter("position_id")));

			new UserService().register(user);

			response.sendRedirect("./");

		} else {
			User user = new User();
			user.setName(request.getParameter("name"));
			user.setLogin_id(request.getParameter("login_id"));
			user.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
			user.setPosition_id(Integer.parseInt(request.getParameter("position_id")));
			request.setAttribute("users", user);

			session.setAttribute("errorMessages", messages);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
			dispatcher.forward(request,response);
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		User editUser = (User) session.getAttribute("editUser");

		editUser.setName(request.getParameter("login_id"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setName(request.getParameter("name"));
		editUser.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
		editUser.setPosition_id(Integer.parseInt(request.getParameter("position_id")));
		return editUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String name = request.getParameter("name");
		String password = request.getParameter("password");

		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
