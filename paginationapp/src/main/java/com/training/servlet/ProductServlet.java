package com.training.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.data.Product;
import com.training.data.ProductDao;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {

	int pageSize = 3;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductDao dao = new ProductDao();

		HttpSession session = request.getSession();
		
		Integer cursor = (Integer) session.getAttribute("cursor");
		if(cursor == null)
			cursor = 0;
		
		String action = request.getParameter("action");
		if(action != null) {
			if(action.equals("next"))
				cursor += pageSize;
			else if(action.equals("prev"))
				cursor -= pageSize;
		}
		else
			cursor = 0;
		
		session.setAttribute("cursor", cursor);
		session.setAttribute("pageSize", pageSize);
		session.setAttribute("total", dao.count());
		
		List<Product> list = dao.fetchProducts(cursor, pageSize);
		
		session.setAttribute("listOfProducts", list);
		
		response.sendRedirect("displayProducts.jsp");
	}

}
