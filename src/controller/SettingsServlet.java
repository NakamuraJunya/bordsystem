package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.Branch;
import beans.Position;
import beans.User;
import exception.NoRowsUpdatedRuntimeException;
import service.BranchService;
import service.PositionService;
import service.UserService;

@WebServlet(urlPatterns = { "/settings" })
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserService userService = new UserService();
		User editUser = userService.getUser(Integer.parseInt(request.getParameter("id")));

		session.setAttribute("editUser", editUser);
		session.setAttribute("id", editUser);

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

		User editUser = getEditUser(request);

		if (isValid(request, messages) == true) {

			try {
				new UserService().update(editUser);
			} catch (NoRowsUpdatedRuntimeException e) {
				session.removeAttribute("editUser");
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("settings");
			}

			session.setAttribute("editUser", editUser);
			session.removeAttribute("editUser");

			response.sendRedirect("usermanagement");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("settings");
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {

		UserService userService = new UserService();
		User editUser = userService.getUser(Integer.parseInt(request.getParameter("id")));

		editUser.setName(request.getParameter("name"));
		editUser.setLoginId(request.getParameter("loginId"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setBranchId(Integer.parseInt(request.getParameter("selectBranch")));
		editUser.setPositionId(Integer.parseInt(request.getParameter("selectPosition")));
		return editUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String name = request.getParameter("name");
		String login_id = request.getParameter("loginId");
		String password = request.getParameter("password");
		String branch_id = request.getParameter("selectBranch");
		String position_id = request.getParameter("selectPosition");

		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		}
		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if (StringUtils.isEmpty(branch_id) == true) {
			messages.add("支店名を選択してください");
		}
		if (StringUtils.isEmpty(position_id) == true) {
			messages.add("部署・役職を選択してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}

