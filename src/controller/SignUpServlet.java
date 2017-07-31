package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.User;
import service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {

			User user = new User();
			user.setName(request.getParameter("name"));
			user.setLogin_id(request.getParameter("login_id"));
			user.setPassword(request.getParameter("password"));
			user.setBranch_id(request.getParameter("branch_id"));
			user.setPosition_id(request.getParameter("position_id"));

			System.out.println(user.getLogin_id());

			new UserService().register(user);

			response.sendRedirect("./");

		} else {
			User user = new User();
			user.setName(request.getParameter("name"));
			user.setLogin_id(request.getParameter("login_id"));
			user.setBranch_id(request.getParameter("branch_id"));
			user.setPosition_id(request.getParameter("position_id"));
			request.setAttribute("users", user);

			session.setAttribute("errorMessages", messages);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
			dispatcher.forward(request,response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String name = request.getParameter("name");
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");

		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		}
		if (10< name.length()) {
				messages.add("名前は10文字以内で入力してください");
		}
		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (!login_id.matches("\\w{6,20}")) {
				messages.add("ログインIDは半角英数字6文字以上20文字以内で入力してください");
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
