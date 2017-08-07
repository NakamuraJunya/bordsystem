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

import beans.Comment;
import beans.User;
import service.CommentService;

@WebServlet(urlPatterns = { "/newcomment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		List<String> comments = new ArrayList<String>();
		User users = (User) session.getAttribute("loginUser");

		Comment comment = new Comment();
		comment.setText(request.getParameter("text"));
		comment.setMessageId(Integer.parseInt(request.getParameter("messageId")));
		comment.setUserId(Integer.parseInt(request.getParameter("userId")));
		comment.setBranchId(users.getBranchId());
		comment.setPositionId(users.getPositionId());

		if (isValid(request, comments) == true) {

			new CommentService().register(comment);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", comments);

			request.setAttribute("makeComment", comment);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/top.jsp");
			dispatcher.forward(request,response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> comments) {

		String text = request.getParameter("text");

		if (StringUtils.isEmpty(text) == true) {
			comments.add("コメントを入力してください");
		}
		if (500< text.length()) {
			comments.add("コメントは500文字以下で入力してください");
		}
		if (comments.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
