package com.training.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class JSONServlet
 */
@WebServlet("/JSONServlet")
public class JSONServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee emp = new Employee();
		emp.setEmpno(1001);
		emp.setName("John");
		emp.setSalary(20000);
		
		ObjectMapper mapper = new ObjectMapper();
		String empJson = mapper.writeValueAsString(emp);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(empJson);
	}

	class Employee {
		int empno;
		String name;
		double salary;
		public int getEmpno() {
			return empno;
		}
		public void setEmpno(int empno) {
			this.empno = empno;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getSalary() {
			return salary;
		}
		public void setSalary(double salary) {
			this.salary = salary;
		}
		
	}
}
