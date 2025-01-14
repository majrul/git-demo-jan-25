package com.training.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		HttpSession session = request.getSession();
		String generatedCaptcha = (String) session.getAttribute("generatedCaptcha");		
		String captchaInput = request.getParameter("captchaInput");
		if(!captchaInput.equals(generatedCaptcha)) {
			response.sendRedirect("login.html?error=1");
		}
		else {
			if(uname.equals("majrul") && pwd.equals("123"))
				response.sendRedirect("welcome.html");
			else
				response.sendRedirect("login.html?error=2");
		}
	}

}
