package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DTO.HospitalDTO;

public class HospitalDAO extends AllDAO {
	
	private HospitalDAO(){
		super();
	}
	private static HospitalDAO instance = new HospitalDAO();
	
	public static HospitalDAO getInstance() {
		return instance;
	}
		
	public int insertHospital(HospitalDTO h) {
		
		int result = -1;
		String sql="insert into hospital(name,addr,addr2,tel,x,y,stat,star) values(?,?,?,?,?,?,?,0);";
		Connection conn =null;
		PreparedStatement pstmt=null;
		AllDAO aDAO = AllDAO.getInstance();
		try {
			conn=aDAO.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, h.getName());
			pstmt.setString(2, h.getAdd());
			pstmt.setString(3, h.getAdd2());
			pstmt.setString(4, h.getTel());
			pstmt.setString(5, h.getX());
			pstmt.setString(6, h.getY());
			pstmt.setString(7, h.getStat());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("HospitalDAO.insertMember() :" +e);
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("HospitalDAO.insertMember()닫는 중 오류발생 :"+ex);
			}
		}		
		return result;
	}
	// 검색 결과 보여주기
		public List<HospitalDTO> findList(int currentPage, int recordsPerPage, String search){
			List<HospitalDTO> List = new ArrayList<HospitalDTO>();
			int start = currentPage*recordsPerPage-recordsPerPage;
			
//			String test = "'%"+search+"%'";
			//중복 select distinct * from 
			String sql = "select * from hospital where (name like '%"+search+"%' or addr like '%"+search+"%' or addr2 like '%"+search+"%') and stat='정상' limit "+start+", "+recordsPerPage+";";
			AllDAO aDAO = AllDAO.getInstance();
			
			Connection conn=null;		
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			try {
				conn=aDAO.getConnection();
				pstmt=conn.prepareStatement(sql);

				rs = pstmt.executeQuery();

				while(rs.next()) {
					
					HospitalDTO h = new HospitalDTO();
					h.setNum(Integer.parseInt(rs.getString("no")));
					h.setName(rs.getString("name"));
					h.setTel(rs.getString("tel"));
					h.setAdd(rs.getString("addr"));
					h.setX(rs.getString("x"));
					h.setY(rs.getString("y"));
					h.setStar(Integer.parseInt(rs.getString("star")));
													
					List.add(h);
//					System.out.println(h.getNum());
				}
			}catch(Exception e) {
				System.out.println("hospitalDAO.findList() 접속중 오류발생 : "+e);
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				}catch(Exception ex) {
					System.out.println("hospitalDAO.findList() 접속 종료중 오류발생 : "+ex);
				}
			}
			
			return List;
		}
		// 냥지수 높은 것부터 검색 결과 보여주기
				public List<HospitalDTO> findStarList(int currentPage, int recordsPerPage, String search){
					List<HospitalDTO> List = new ArrayList<HospitalDTO>();
					int start = currentPage*recordsPerPage-recordsPerPage;					

					String sql = "select * from hospital where (name like '%"+search+"%' or addr like '%"+search+"%' or addr2 like '%"+search+"%') and stat='정상' order by star desc limit "+start+", "+recordsPerPage+";";
					AllDAO aDAO = AllDAO.getInstance();
					
					Connection conn=null;		
					PreparedStatement pstmt = null;
					ResultSet rs=null;
					try {
						conn=aDAO.getConnection();
						pstmt=conn.prepareStatement(sql);

						rs = pstmt.executeQuery();

						while(rs.next()) {
							
							HospitalDTO h = new HospitalDTO();
							h.setNum(Integer.parseInt(rs.getString("no")));
							h.setName(rs.getString("name"));
							h.setTel(rs.getString("tel"));
							h.setAdd(rs.getString("addr"));
							h.setX(rs.getString("x"));
							h.setY(rs.getString("y"));
							h.setStar(Integer.parseInt(rs.getString("star")));
															
							List.add(h);
//							System.out.println(h.getNum());
						}
					}catch(Exception e) {
						System.out.println("hospitalDAO.findList() 접속중 오류발생 : "+e);
					}finally {
						try {
							if(rs!=null)rs.close();
							if(pstmt!=null)pstmt.close();
							if(conn!=null)conn.close();
						}catch(Exception ex) {
							System.out.println("hospitalDAO.findList() 접속 종료중 오류발생 : "+ex);
						}
					}
					
					return List;
				}	
}
