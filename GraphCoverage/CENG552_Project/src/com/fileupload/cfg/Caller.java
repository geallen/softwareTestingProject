package com.fileupload.cfg;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Path;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class Caller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Caller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String criteria = request.getParameter("criteria");
		String name = request.getParameter("location");
		String locationOfOutputFile = request.getParameter("locationOfOutputFile");
		
		ExcelOperations abc = new ExcelOperations();
		List<Path> testPaths = abc.resultPath(criteria, name, locationOfOutputFile);
		System.out.println("Test Paths : " + testPaths);
		doGet(request, response);
		request.setAttribute("testPaths", testPaths);
		request.setAttribute("criteria", criteria);
		request.setAttribute("message", locationOfOutputFile);
		request.getRequestDispatcher("/testPathResults.jsp").forward(request, response);
	}

}
