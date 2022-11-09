package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AllDAO;
import DAO.MemberDAO;
import DTO.MemberDTO;


@WebServlet("/grade")
public class grade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String grade = request.getParameter("grade");
		int num = Integer.parseInt(request.getParameter("num"));
		AllDAO aDAO = AllDAO.getInstance();
		Connection conn=null;		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
				
		System.out.println(num);
		System.out.println(grade);
		String sql="update member set grade=? where num=?;";
		
		try {
			conn=aDAO.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,request.getParameter("grade"));
			pstmt.setString(2,request.getParameter("num")); 
			pstmt.executeUpdate();			
		}catch(Exception e) {
			System.out.println("grade() 접속중 오류발생 : "+e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("grade() 접속 종료중 오류발생 : "+ex);
			}
		}
		
		
		int endPage;
		int currentPage =1;
		int nOfPage;
		
		//currentPage = Integer.parseInt(request.getParameter("currentPage"));		
		int recordsPerPage=10;
		
		MemberDAO mDAO = MemberDAO.getInstance();
		List<MemberDTO> data = mDAO.findList(currentPage, recordsPerPage);  //limit을 건 select 가져오기
		request.setAttribute("data",data);	
		
		int row = mDAO.getNumberOfRows();
		
		nOfPage=row/recordsPerPage;	//전체 페이지를 보여줄 페이지 수로 저장
		if(row%recordsPerPage>0) {
			nOfPage++;					//만약 자료 갯수가 나머지가 있는 경우 한 페이지 추가
		}
		
		endPage = (int)Math.ceil((currentPage/10)*10);
		
		request.setAttribute("nOfPage",nOfPage);				//전체 페이지 갯수
		request.setAttribute("currentPage",currentPage);		//현재 보고있는 페이지 번호
		request.setAttribute("recordsPerPage",recordsPerPage);
		
		
		
		RequestDispatcher dis = request.getRequestDispatcher("member.jsp");
		dis.forward(request, response);
	}

}
