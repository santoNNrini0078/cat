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

@WebServlet("/modify_noti_R")
public class modify_noti_RServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		String sql="update notice set content=? where num=?;";
		
		AllDAO aDAO = AllDAO.getInstance();
		try {
			conn = aDAO.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,request.getParameter("con")); 
			pstmt.setString(2,request.getParameter("num"));
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("리뷰 수정 접속중오류발생"+e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("리뷰 수정 접속 종료중 오류발생 : "+ex);
			}
		}
		
		String name="currentPage";	
		int i = 15;
		int j = 1;
		
		RequestDispatcher dis = request.getRequestDispatcher("show");
		dis.forward(request, response);
	}

}
