package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import DTO.MemberDTO;
import DAO.MemberDAO;

@WebServlet("/join")
public class joinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("/join.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String user_id = request.getParameter("user_id");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String race = request.getParameter("race");	
		int age = Integer.parseInt(request.getParameter("age"));
//		String timestamp = request.getParameter("timestamp");
		String grade = "1";
		int sel = 1;
		
		MemberDTO m = new MemberDTO();
		m.setUser_id(user_id);
		m.setPass(pass);
		m.setEmail(email);
		m.setAddress(address);
		m.setRace(race);
		m.setAge(age);
//		m.setTimestamp(timestamp);
		m.setGrade(grade);
		m.setSel(sel);
			
		MemberDAO mDAO = MemberDAO.getInstance();
		int result = mDAO.insertMember(m);
		
		HttpSession session = request.getSession();
		if(result==1) {
			session.setAttribute("user_id", m.getUser_id());
			request.setAttribute("message", "회원가입에 성공했습니다.");
		}else {
			request.setAttribute("message","회원가입에 실패했습니다.");
		}
			
		RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
		dis.forward(request, response);
		
		
	}

}
