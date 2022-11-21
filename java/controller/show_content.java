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
import DTO.noticeDTO;

@WebServlet("/show_content")
public class show_content extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String num =request.getParameter("num");
		String view = request.getParameter("view");
		System.out.println(view);
		int view2 = Integer.parseInt(view)+1;
		System.out.println(view2);		
		
		Connection conn=null;		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs=null;
		String sql = "update notice set view=? where num=?;"; //뷰 수 올리기
		String sql2 ="select * from notice where num=?;"; //공지사항 불러오기
		AllDAO aDAO = AllDAO.getInstance();
		noticeDTO r = new noticeDTO();
		try {
			conn = aDAO.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,view2);			
			pstmt.setString(2,num);
			pstmt.executeUpdate();					
			
			pstmt2 = conn.prepareStatement(sql2);			
			pstmt2.setString(1,num);
			rs = pstmt2.executeQuery();	
			
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
			System.out.println("show_content 접속 중 오류발생 : "+e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();								
				if(rs!=null)rs.close();				
				if(pstmt2!=null)pstmt2.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("show_content 접속 종료 중 오류발생 : "+ex);
			}
		}
		request.setAttribute("noti",r);
		RequestDispatcher dis = request.getRequestDispatcher("content_noti.jsp");
		dis.forward(request, response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String num =request.getParameter("num");
		String view = request.getParameter("view");
		int view2 = Integer.parseInt(view)+1;
		System.out.println(view);		
		System.out.println(num);
		
		Connection conn=null;		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs=null;
		String sql = "update review set view=? where num=?;"; //뷰 수 올리기
		String sql2 ="select * from review where num=?;"; //리뷰 불러오기
		AllDAO aDAO = AllDAO.getInstance();
		reviewDTO r = new reviewDTO();
		try {
			conn = aDAO.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,view2);			
			pstmt.setString(2,num);
			pstmt.executeUpdate();
			
			pstmt2 = conn.prepareStatement(sql2);			
			pstmt2.setString(1,num);
			rs = pstmt2.executeQuery();			
			
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
				if(pstmt!=null)pstmt.close();				
				if(rs!=null)rs.close();
				if(pstmt2!=null)pstmt2.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("pDAO.getNumberOfRows 접속 종료 중 오류발생 : "+ex);
			}
		}
		request.setAttribute("review",r);
		RequestDispatcher dis = request.getRequestDispatcher("content.jsp");
		dis.forward(request, response);
	}

}
