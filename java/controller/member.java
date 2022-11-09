package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import DTO.MemberDTO;

/**
 * Servlet implementation class member
 */
@WebServlet("/member")
public class member extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
