package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;

@WebServlet("/idCheck")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user_id = request.getParameter("user_id");
		MemberDAO mDAO = MemberDAO.getInstance();
		int result = mDAO.confirmId(user_id);
		request.setAttribute("user_id", user_id);
		request.setAttribute("result", result);
		
		RequestDispatcher dis = request.getRequestDispatcher("idCheck.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
