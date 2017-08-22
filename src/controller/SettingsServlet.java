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

		if((StringUtils.isNotBlank(request.getParameter("id")) == false) ||
			(!request.getParameter("id").matches("^[0-9]+$") ||
					userService.getUser(Integer.parseInt(request.getParameter("id"))) == null)){

			List<String> errors = new ArrayList<String>();
			errors.add("不正なURLが入力されました");
			session.setAttribute("errorMessages", errors);
			response.sendRedirect("usermanagement");

			return;
		}

		User editUser = userService.getUser(Integer.parseInt(request.getParameter("id")));
		User users = (User) session.getAttribute("loginUser");

		session.setAttribute("editUser", editUser);
		session.setAttribute("id", editUser);
		session.setAttribute("users", users);

		List<Branch> branchList = new BranchService().getBranch();

		request.setAttribute("branchList", branchList);

		List<Position> positionList = new PositionService().getPosition();

		request.setAttribute("positionList", positionList);

		request.getRequestDispatcher("settings.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> errors = new ArrayList<String>();

		HttpSession session = request.getSession();

		User editUser = getEditUser(request);

		if (isValid(request, errors) == true) {

			try {

				new UserService().update(editUser);
				response.sendRedirect("usermanagement");

			} catch (NoRowsUpdatedRuntimeException e) {
				session.removeAttribute("editUser");
				errors.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", errors);
				response.sendRedirect("settings");
			}

			session.setAttribute("editUser", editUser);
			session.removeAttribute("editUser");

		} else {

			session.setAttribute("errorMessages", errors);
			session.setAttribute("editUser", editUser);

			List<Branch> branchList = new BranchService().getBranch();

			request.setAttribute("branchList", branchList);

			List<Position> positionList = new PositionService().getPosition();

			request.setAttribute("positionList", positionList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/settings.jsp");
			dispatcher.forward(request,response);
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
		String nextpassword = request.getParameter("nextpassword");
		int branchId = Integer.parseInt(request.getParameter("selectBranch"));
		int positionId = Integer.parseInt(request.getParameter("selectPosition"));
		int id =(Integer.parseInt(request.getParameter("id")));

		UserService userService = new UserService();
		User user = userService.getUser(login_id);

		if (user != null && id != user.getId()) {
			messages.add("このログインIDはすでに使用されています");
		}
		if (10<name.length()) {
			messages.add("名前は10文字以内で入力してください");
		}
		if (name == null) {
			messages.add("名前は10文字以内で入力してください");
		}
		if (!login_id.matches("\\w{6,20}")) {
			messages.add("ログインIDは半角英数字6文字以上20文字以内で入力してください");
		}
		if (StringUtils.isEmpty(password) == false) {
			if (!password.matches("^[-@+*;:#$%&\\w]{6,20}+$")) {
				messages.add("パスワードは記号を含む全ての半角文字6文字以上20文字以下で入力してください");
			}
		}
		if (!(password.matches(nextpassword))) {
			messages.add("入力したパスワードが一致しません");
		}
		if (branchId == 0) {
			messages.add("支店名を選択してください");
		}
		if (positionId == 0) {
			messages.add("部署・役職を選択してください");
		}
		if (branchId==1 && positionId>=3){
			messages.add("支店、部署・役職の組み合わせが不正です");
		}
		if (branchId >=2 && positionId<=2) {
			messages.add("支店、部署・役職の組み合わせが不正です");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}

