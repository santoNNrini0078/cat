package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AllDAO;

@WebServlet("/del")
public class delServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num =request.getParameter("num");
		System.out.println(num);
		Connection conn=null;		
		PreparedStatement pstmt = null;
		String sql = "update review set del=0 where num=?;"; //리뷰 불러오기
		AllDAO aDAO = AllDAO.getInstance();
		try {
			conn = aDAO.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,num);	
			pstmt.executeUpdate();
					
		}catch(Exception e) {
			System.out.println("pDAO.delete 접속 중 오류발생 : "+e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("pDAO.delete 접속 종료 중 오류발생 : "+ex);
			}
		}
		RequestDispatcher dis = request.getRequestDispatcher("show");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
