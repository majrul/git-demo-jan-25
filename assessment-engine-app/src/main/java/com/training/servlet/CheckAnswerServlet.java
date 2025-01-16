package com.training.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.datamodel.Option;
import com.training.datamodel.Question;

/**
 * Servlet implementation class CheckAnswerServlet
 */
@WebServlet("/CheckAnswerServlet")
public class CheckAnswerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		Integer score = (Integer) session.getAttribute("score");
		if(score == null)
			score = 0;
		
		Question q = (Question) session.getAttribute("question");
		int op = Integer.parseInt(request.getParameter("op"));
		Option selectedOption = q.getOptions().get(op);
		
		if(selectedOption.isRightAnswer())
			score ++;
			
		session.setAttribute("score", score);
		
		response.sendRedirect("QuestionLoaderServlet");
		
	}

}
