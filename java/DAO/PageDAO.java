package DAO;
import java.sql.*;
import java.util.*;
import DTO.PageDTO;
import DTO.noticeDTO;

public class PageDAO {
//리뷰게시판 페이지처리
	private PageDAO() {
		
	}
	private static PageDAO instance = new PageDAO();
	
	public static PageDAO getInstance() {
		return instance;
	}
	
	//목록 출력을 위한 정보처리
		//현재 페이지가 몇번째 페이지를 보고 있는지, 한 페이지에 몇개의 자료를 보여줘야 하는지
		//해당 패러미터를 매개변수로 전달받도록 수정
	public List<PageDTO> findList(int currentPage, int recordsPerPage){
		List<PageDTO> List = new ArrayList<PageDTO>();
		int start = currentPage*recordsPerPage-recordsPerPage;
		//currentPage=1, recoredPerPage=5 :: 0, 5
		//currentPage=2, recoredPerPage=5 :: 5, 5
		//currentPage=3, recoredPerPage=5 :: 10,5
		//현재 페이지 값에서 보여줄 자료 양을 곱한 뒤에 페이지당 자료양을 빼주면 시작점을 특정할 수 있다.
		String sql = "select * from review where del=1 order by num desc limit ?, ?;";
		Connection conn=null;		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		AllDAO aDAO = AllDAO.getInstance();
		try {
			conn=aDAO.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,start);
			pstmt.setInt(2, recordsPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				PageDTO p=new PageDTO();
				p.setNum(rs.getInt("num"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				p.setId(rs.getString("id"));
				p.setView(rs.getInt("view"));
				p.setTime(rs.getString("time"));
				p.setTimestamp(rs.getString("timestamp"));
				List.add(p);
			}
		}catch(Exception e) {
			System.out.println("pageDAO.findList() 접속중 오류발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("pageDAO.findList() 접속 종료중 오류발생 : "+ex);
			}
		}
		
		return List;
	}
	//전체 자료의 갯수를 가져오는 동작
	public int getNumberOfRows() {
		String sql = "select count(num) from review;";
		int numberOfRows = 0; //몇개의 데이터가 있는지를 저장할 공간
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AllDAO aDAO = AllDAO.getInstance();
		try {
			conn = aDAO.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			numberOfRows = Integer.parseInt(rs.getString(1));//"count(num)"
			
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
		return numberOfRows;
	}
	//공지의 내용을 불러오는 동작
	public List<noticeDTO> notice(){
		List<noticeDTO> noticeList = new ArrayList<noticeDTO>();
		String sql = "select * from notice order by num desc;";
		Connection conn=null;		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		AllDAO aDAO = AllDAO.getInstance();
		try {
			conn=aDAO.getConnection();
			pstmt=conn.prepareStatement(sql);			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				noticeDTO p=new noticeDTO();
				p.setNum(rs.getInt("num"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				p.setId(rs.getString("id"));
				p.setView(rs.getInt("view"));
				p.setTime(rs.getString("time"));
				noticeList.add(p);
			}
		}catch(Exception e) {
			System.out.println("noticeDAO.notice() 접속중 오류발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("noticeDAO.notice() 접속 종료중 오류발생 : "+ex);
			}
		}		
		return noticeList;
	}
}
