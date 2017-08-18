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

		List<String> errors = new ArrayList<String>();

		User user = new User();
		user.setName(request.getParameter("name"));
		user.setLoginId(request.getParameter("loginId"));
		user.setPassword(request.getParameter("password"));
		user.setBranchId(Integer.parseInt(request.getParameter("selectBranch")));
		user.setPositionId(Integer.parseInt(request.getParameter("selectPosition")));


		if (isValid(request, errors) == true) {
			new UserService().register(user);
			response.sendRedirect("usermanagement");

		} else {

			request.setAttribute("errorMessages", errors);
			request.setAttribute("user", user);

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
		String nextpassword = request.getParameter("nextpassword");
		int branchId = Integer.parseInt(request.getParameter("selectBranch"));
		int positionId = Integer.parseInt(request.getParameter("selectPosition"));

		if (name.length()>10) {
			messages.add("名前は10文字以内で入力してください");
		}
		if (!login_id.matches("\\w{6,20}")) {
			messages.add("ログインIDは半角英数字6文字以上20文字以内で入力してください");
		}
		if (!password.matches("^[-@+*;:#$%&\\w]{6,20}+$")) {
			messages.add("パスワードは記号を含む全ての半角文字6文字以上20文字以下で入力してください");
		}
		if (!(password.matches(nextpassword))) {
			messages.add("入力したパスワードが正しくありません");
		}
		if (branchId == 0  ) {
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

