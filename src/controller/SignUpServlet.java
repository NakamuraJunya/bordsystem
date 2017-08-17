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
import service.BranchService;
import service.PositionService;
import service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Branch> branchList = new BranchService().getBranch();

		request.setAttribute("branchList", branchList);

		List<Position> positionList = new PositionService().getPosition();

		request.setAttribute("positionList", positionList);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		User user = new User();
		user.setName(request.getParameter("name"));
		user.setLoginId(request.getParameter("loginId"));
		user.setPassword(request.getParameter("password"));
		user.setBranchId(Integer.parseInt(request.getParameter("selectBranch")));
		user.setPositionId(Integer.parseInt(request.getParameter("selectPosition")));


		if (isValid(request, messages) == true) {
			new UserService().register(user);
			response.sendRedirect("usermanagement");

		} else {

			session.setAttribute("errorMessages", messages);
			session.setAttribute("user", user);

			List<Branch> branchList = new BranchService().getBranch();

			request.setAttribute("branchList", branchList);

			List<Position> positionList = new PositionService().getPosition();

			request.setAttribute("positionList", positionList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
			dispatcher.forward(request,response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String name = request.getParameter("name");
		String login_id = request.getParameter("loginId");
		String password = request.getParameter("password");
		int branch_id = Integer.parseInt(request.getParameter("selectBranch"));
		int position_id = Integer.parseInt(request.getParameter("selectPosition"));

		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		}
		if (10< name.length()) {
				messages.add("名前は10文字以内で入力してください");
		}
		if (!login_id.matches("\\w{6,20}")) {
				messages.add("ログインIDは半角英数字6文字以上20文字以内で入力してください");
		}
		if  (!password.matches("\\w{6,20}")) {
			messages.add("パスワードは記号を含む全ての半角文字6文字以上20文字以下で入力してください");
		}
		if (branch_id == 0  ) {
			messages.add("支店名を選択してください");
		}
		if (position_id == 0) {
			messages.add("部署・役職を選択してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
