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

import beans.Message;
import beans.User;
import beans.UserMessage;
import service.MessageService;

@WebServlet(urlPatterns = { "/newmessage" })
public class NewMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		 List<UserMessage> categoryList = new MessageService().getMessageCategory();
		 request.setAttribute("categoryList", categoryList);

		request.getRequestDispatcher("newmessage.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		List<String> errows = new ArrayList<String>();
		User users = (User) session.getAttribute("loginUser");

		Message message = new Message();
		message.setTitle(request.getParameter("title"));
		message.setText(request.getParameter("text"));
		if (StringUtils.isBlank(request.getParameter("category")) == true) {

			message.setCategory(request.getParameter("newCategory"));
		}
//		if (StringUtils.isBlank(request.getParameter("newCategory")) == true) {
//
//			message.setCategory(request.getParameter("category"));
//		}
		message.setUserId(users.getId());
		message.setBranchId(users.getBranchId());
		message.setPositionId(users.getPositionId());

		if (isValid(request, errows) == true) {
			if (StringUtils.isBlank(request.getParameter("newCategory")) == true) {

				message.setCategory(request.getParameter("category"));
			}

			new MessageService().register(message);

			response.sendRedirect("./");
		} else {
			message.setCategory(request.getParameter("newCategory"));
			session.setAttribute("errorMessages", errows);

			request.setAttribute("makeMessage", message);

			request.setAttribute("newMakeMessage", (request.getParameter("category")));

			List<UserMessage> categoryList = new MessageService().getMessageCategory();
			request.setAttribute("categoryList", categoryList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/newmessage.jsp");
			dispatcher.forward(request,response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String text = request.getParameter("text");

		if (StringUtils.isEmpty(title) == true) {
			messages.add("件名を入力してください");
		}
		if (30< title.length()) {
			messages.add("件名は30文字以下で入力してください");
		}
		if (StringUtils.isBlank(request.getParameter("newCategory")) == true && StringUtils.isEmpty(category) == true) {
			messages.add("カテゴリーは新規もしくは既存のどちらかの入力が必要です");
		}
		if (10< category.length()) {
			messages.add("カテゴリーは10文字以下で入力してください");
		}
		if(StringUtils.isBlank(request.getParameter("newCategory")) == false && StringUtils.isBlank(category) == false) {
			messages.add("カテゴリーは新規もしくは既存のどちらかの選択のみです");
		}
		if (StringUtils.isEmpty(text) == true) {
			messages.add("本文を入力してください");
		}
		if (1000< text.length()) {
			messages.add("本文は1000文字以下で入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
