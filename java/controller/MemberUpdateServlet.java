package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import DTO.MemberDTO;

@WebServlet("/MemberUpdateServlet")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		MemberDAO mDAO = MemberDAO.getInstance();
		MemberDTO m = mDAO.getMember(user_id);
		request.setAttribute("mem", m);
		RequestDispatcher dis = request.getRequestDispatcher("memberUpdate.jsp");
		dis.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String user_id = request.getParameter("user_id");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String race = request.getParameter("race");	
		int age = Integer.parseInt(request.getParameter("age"));
		String timestamp = request.getParameter("timestamp");
		String grade = request.getParameter("grade");
		int sel = 1;
		
		MemberDTO m = new MemberDTO();
		m.setUser_id(user_id);
		m.setPass(pass);
		m.setEmail(email);
		m.setAddress(address);
		m.setRace(race);
		m.setAge(age);
		m.setTimestamp(timestamp);
		m.setGrade(grade);
		m.setSel(sel);
			
		MemberDAO mDAO = MemberDAO.getInstance();
		mDAO.updateMember(m);
		response.sendRedirect("login");
	}

}
