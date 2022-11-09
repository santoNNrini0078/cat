package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DTO.ByeDTO;

public class ByeDAO{
	
	private ByeDAO() {
		
	}
	private static ByeDAO instance = new ByeDAO();
	
	public static ByeDAO getInstance() {
		return instance;
	}	

	public int insertBye(ByeDTO b) {
		
		int result = -1;
		String sql="insert into bye(name,addr,addr2,tel,x,y,stat) values(?,?,?,?,?,?,?);";
		Connection conn =null;
		PreparedStatement pstmt=null;
		AllDAO aDAO = AllDAO.getInstance();
		try {
			conn=aDAO.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getName());
			pstmt.setString(2, b.getAdd());
			pstmt.setString(3, b.getAdd2());
			pstmt.setString(4, b.getTel());
			pstmt.setString(5, b.getX());
			pstmt.setString(6, b.getY());
			pstmt.setString(7, b.getStat());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("ByeDAO.insertMember() :" +e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("ByeDAO.insertMember() :"+ex);
			}
		}		
		return result;
	}
	// 검색 결과 보여주기
	public List<ByeDTO> findList(int currentPage, int recordsPerPage, String search){
		List<ByeDTO> List = new ArrayList<ByeDTO>();
		int start = currentPage*recordsPerPage-recordsPerPage;
		
//		String test = "'%"+search+"%'";
		//중복 select distinct * from 
		String sql = "select * from bye where (name like '%"+search+"%' or addr like '%"+search+"%' or addr2 like '%"+search+"%') and stat='정상' limit "+start+", "+recordsPerPage+";";
		
//		String sql = "select * from bye where name like '%?%' or addr like '%?%' or addr2 like '%?%' and stat='정상' limit ?, ?;";
		
		Connection conn=null;		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		AllDAO aDAO = AllDAO.getInstance();
		try {
			conn=aDAO.getConnection();
			pstmt=conn.prepareStatement(sql);
//			pstmt.setString(1,search);
//			pstmt.setString(2,search);
//			pstmt.setString(3,search);
//			pstmt.setInt(4,start);
//			pstmt.setInt(5, recordsPerPage);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				ByeDTO b = new ByeDTO();
				b.setName(rs.getString("name"));
				b.setTel(rs.getString("tel"));
				b.setAdd(rs.getString("addr"));
				b.setX(rs.getString("x"));
				b.setY(rs.getString("y"));
												
				List.add(b);
//				System.out.println(b);
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
}
