package controller;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AllDAO;
import DTO.uname;


@WebServlet("/hosi")
public class hosi extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String num=request.getParameter("num");
		HttpSession session = request.getSession();
		String username=(String)session.getAttribute("loginUser");
		System.out.println("num="+num);
		int sstar=0;
		String sql="update hospital set star=star+1 where no="+num;
		AllDAO aDAO = AllDAO.getInstance();
		Connection conn=null;		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		uname u=uname.getInstance();
		System.out.println(u.getName());
		System.out.println(username);
		if(u.getName()==username) {
			
		}else {
		u.setName(username);
		
		
		try {
			conn=aDAO.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();			
		}catch(Exception e) {
			System.out.println("hospitalDAO.star() 접속중 오류발생 : "+e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("hospitalDAO.star() 접속 종료중 오류발생 : "+ex);
			}
		}
		
		}
		sql="select star from hospital where no="+num;
		try {
			conn=aDAO.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			sstar=rs.getInt("star");
			System.out.println("실행함");
		}catch(Exception e) {
			System.out.println("hospitalDAO.star() 접속중 오류발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("hospitalDAO.star() 접속 종료중 오류발생 : "+ex);
			}
		}
		System.out.println("sstar="+sstar);
		
		
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		
		out.print("<response>");
		out.print("<star>"+sstar+"</star>");
		out.print("</response>");
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
