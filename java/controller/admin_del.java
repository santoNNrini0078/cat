package controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AllDAO;


@WebServlet("/admin_del")
public class admin_del extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String del_num = request.getParameter("del_num");
		String del[] = del_num.split(",");
		int num = del.length;
		String[] num2 = new String [num];
		System.out.println(num);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		AllDAO aDAO = AllDAO.getInstance();

		for(int i=0; i<del.length; i++) {
			num2[i] = del[i];
			System.out.println(num2[i]);
			
			String sql = "update review set del=0 where num=?;";
			
			try {
				conn=aDAO.getConnection();			
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,Integer.parseInt(num2[i]));	
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.out.println("admin() 접속 중 오류발생 : "+e);
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				}catch(Exception ex) {
					System.out.println("admin() 접속 종료중 오류발생 : "+ex);
				}
			}
		}				
		
		RequestDispatcher dis = request.getRequestDispatcher("show");
		dis.forward(request, response);
	}

}
