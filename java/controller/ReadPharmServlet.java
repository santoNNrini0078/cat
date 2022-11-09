package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AllDAO;
import DAO.PharmDAO;
import DTO.PharmDTO;


@WebServlet("/ReadPharm")
public class ReadPharmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//currentPage : 사용자가 현재 보고 있는 페이지번호
		//recordsPerPage : 한 페이지에 보여줄 자료의 개수
		String search = request.getParameter("search");
//		System.out.println(search);
		int currentPage =1;
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
		int recordsPerPage=5;
		
		PharmDAO pDAO =PharmDAO.getInstance();	
		List<PharmDTO> data =pDAO.findList(currentPage, recordsPerPage, search);  //limit을 건 select 가져오기
		request.setAttribute("data",data); //가져온 자료를 담은 리스트를 리퀘스트로 전송
		
		AllDAO aDAO = AllDAO.getInstance();
		int row = aDAO.getNumberOfRows("pharm", search);
		
//		int row = pDAO.getNumberOfRows(search); //총 자료의 갯수를 가져와서 변수에 저장
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
		request.setAttribute("recordsPerPage",recordsPerPage);  //한 페이지당 표시할 자료 수
		request.setAttribute("search", search);				
		request.setAttribute("blockStartNum", blockStartNum);
		request.setAttribute("blockLastNum", blockLastNum );
		RequestDispatcher dis = request.getRequestDispatcher("PharmList.jsp");
		dis.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
