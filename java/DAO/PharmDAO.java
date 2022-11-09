package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DTO.PharmDTO;

public class PharmDAO extends AllDAO{
	private PharmDAO() {
		super();
	}
	private static PharmDAO instance = new PharmDAO();
	
	public static PharmDAO getInstance() {
		return instance;
	}
	
public int insertPharm(PharmDTO p) {
		
		int result = -1;
		String sql="insert into pharm(name,addr,addr2,tel,x,y,stat) values(?,?,?,?,?,?,?);";
		Connection conn =null;
		PreparedStatement pstmt=null;
		AllDAO aDAO = AllDAO.getInstance();
		try {
			conn=aDAO.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getAdd());
			pstmt.setString(3, p.getAdd2());
			pstmt.setString(4, p.getTel());
			pstmt.setString(5, p.getX());
			pstmt.setString(6, p.getY());
			pstmt.setString(7, p.getStat());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("PharmDAO.insertMember() :" +e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("PharmDAO.insertMember()닫는 중 오류발생 :"+ex);
			}
		}		
		return result;
	}
	// 검색 결과 보여주기
		public List<PharmDTO> findList(int currentPage, int recordsPerPage, String search){
			List<PharmDTO> List = new ArrayList<PharmDTO>();
			int start = currentPage*recordsPerPage-recordsPerPage;
			
//			String test = "'%"+search+"%'";
			//중복 select distinct * from 
			String sql = "select * from pharm where (name like '%"+search+"%' or addr like '%"+search+"%' or addr2 like '%"+search+"%') and stat='정상' limit "+start+", "+recordsPerPage+";";
			AllDAO aDAO = AllDAO.getInstance();
			
			Connection conn=null;		
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			try {
				conn=aDAO.getConnection();
				pstmt=conn.prepareStatement(sql);

				rs = pstmt.executeQuery();

				while(rs.next()) {
					
					PharmDTO p = new PharmDTO();
					p.setName(rs.getString("name"));
					p.setTel(rs.getString("tel"));
					p.setAdd(rs.getString("addr"));
					p.setX(rs.getString("x"));
					p.setY(rs.getString("y"));
													
					List.add(p);
//					System.out.println(b);
				}
			}catch(Exception e) {
				System.out.println("PharmDAO.findList() 접속중 오류발생 : "+e);
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				}catch(Exception ex) {
					System.out.println("PharmDAO.findList() 접속 종료중 오류발생 : "+ex);
				}
			}
			
			return List;
		}	
}
