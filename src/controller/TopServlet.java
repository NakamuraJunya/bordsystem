package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import beans.UserComment;
import beans.UserMessage;
import service.CommentService;
import service.MessageService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startDate =  request.getParameter("startDate");
	    String endDate = request.getParameter("endDate");
	    String category = request.getParameter("category");

		if (StringUtils.isNotBlank(startDate) == false) {
		    startDate = "2017-07-31";
		}
		if (StringUtils.isNotBlank(endDate) == false) {
			endDate = format.format(calendar.getTime());
		}
		if (StringUtils.isNotBlank(category) == false) {
			category = request.getParameter("category");
		}
		List<UserMessage> messages = new MessageService().getMessage(startDate,endDate,category);
		List<UserComment> comments = new CommentService().getComment();

		request.setAttribute("messages", messages);
		request.setAttribute("comments", comments);


		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

}
