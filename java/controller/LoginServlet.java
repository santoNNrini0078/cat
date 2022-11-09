package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import DAO.MemberDAO;
import DTO.MemberDTO;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String user_id = request.getParameter("user_id");
		String pass = request.getParameter("pass");
		
		MemberDAO mDAO = MemberDAO.getInstance();
		int result = mDAO.userCheck(user_id, pass);
		System.out.println("로그인 결과 : "+result);
		
		if(result==1) {
			MemberDTO m = mDAO.getMember(user_id);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",m.getUser_id());
			session.setAttribute("grade", m.getGrade());
			System.out.println(m.getGrade());
			
			request.setAttribute("massage","로그인에 성공했습니다.");		
		}else if(result==0) {
			request.setAttribute("massage", "비밀번호가 맞지 않습니다.");
		}else if(result==-1) {
			request.setAttribute("massage", "존재하지 않는 회원입니다.");
		}
		RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
		dis.forward(request, response);
	}

}
