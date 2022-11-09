package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import DAO.PageDAO;
import DTO.PageDTO;
import DTO.noticeDTO;

import java.util.*;

@WebServlet("/show")
public class show_review extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//currentPage : 사용자가 현재 보고 있는 페이지번호
		//recordsPerPage : 한 페이지에 보여줄 자료의 개수
		
		int currentPage =1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int recordsPerPage=15;
//		System.out.println("현재 페이지 : "+currentPage+", 페이지 당 데이터 : "+recordsPerPage);
		
		PageDAO pDAO =PageDAO.getInstance();
		List<PageDTO> data = pDAO.findList(currentPage, recordsPerPage);  //limit을 건 select 가져오기
		request.setAttribute("data",data); //가져온 자료를 담은 리스트를 리퀘스트로 전송
		
		List<noticeDTO> data2 = pDAO.notice();
		request.setAttribute("data2", data2);
		
		
		int row = pDAO.getNumberOfRows(); //총 자료의 갯수를 가져와서 변수에 저장
		int nOfPage=row/recordsPerPage;	//전체 페이지를 보여줄 페이지 수로 저장
		if(row%recordsPerPage>0) {
			nOfPage++;					//만약 자료 갯수가 나머지가 있는 경우 한 페이지 추가
		}
		int blockNum=0;
		int pageCount=5;
		int blockStartNum=0;
		int blockLastNum=0;
		blockNum=(int)Math.floor((currentPage-1)/pageCount);
		blockStartNum=(pageCount*blockNum)+1;
		blockLastNum=blockStartNum+(pageCount-1);
		
		request.setAttribute("nOfPage",nOfPage);				//전체 페이지 갯수
		request.setAttribute("currentPage",currentPage);		//현재 보고있는 페이지 번호
		request.setAttribute("recordsPerPage",recordsPerPage);	//한 페이지당 표시할 자료 수
		request.setAttribute("blockStartNum", blockStartNum);
		request.setAttribute("blockLastNum", blockLastNum );
		RequestDispatcher dis = request.getRequestDispatcher("review.jsp");
		dis.forward(request, response);		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}