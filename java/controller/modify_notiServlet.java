package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AllDAO;
import DTO.reviewDTO;

@WebServlet("/modify_noti")
public class modify_notiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String num =request.getParameter("num");
		System.out.println(num);
		Connection conn=null;		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql = "select * from notice where num=?;"; //리뷰 불러오기
		AllDAO aDAO = AllDAO.getInstance();
		reviewDTO r = new reviewDTO();
		try {
			conn = aDAO.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,num);
			rs = pstmt.executeQuery();			
			
			while(rs.next()) {				
				r.setNum(rs.getInt("num"));
				r.setTitle(rs.getString("title"));
				r.setContent(rs.getString("content"));
				r.setId(rs.getString("id"));
				r.setView(rs.getInt("view"));
				r.setTime(rs.getString("time"));
				r.setTimestamp(rs.getString("timestamp"));
				r.setSel(rs.getInt("sel"));
			}			
		}catch(Exception e) {
			System.out.println("pDAO.getNumberOfRows 접속 중 오류발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("pDAO.getNumberOfRows 접속 종료 중 오류발생 : "+ex);
			}
		}
		request.setAttribute("noti",r);
		RequestDispatcher dis = request.getRequestDispatcher("modify_noti.jsp");
		dis.forward(request, response);
	}

}
