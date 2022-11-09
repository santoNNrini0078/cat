package DAO;
import DTO.MemberDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	
	private MemberDAO(){
		
	}
	private static MemberDAO instance= new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
		
	//사용자 인증(로그인)
	public int userCheck(String user_id, String pass) {
		int result = -1;
		String sql = "select pass from member where user_id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			AllDAO aDAO = AllDAO.getInstance();
			conn = aDAO.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	//해당 아이디가 존재하면
				if(rs.getString("pass").equals(pass) && rs.getString("pass")!=null) {
					result = 1;
				}else {
					result= 0 ;
				}
			}else {
				result = -1;
			}
		}catch(Exception e) {
			System.out.println("로그인 중 오류발생"+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("로그인 회선 종료 중 오류발생"+ex);
			}
		}		
		
		return result;
	}
	
	//사용자 정보 조회
	public MemberDTO getMember(String user_id) {
		MemberDTO m = null;
		String sql = "select * from member where user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			AllDAO aDAO = AllDAO.getInstance();
			conn = aDAO.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m= new MemberDTO();
				m.setNum(Integer.parseInt(rs.getString("num")));
				m.setUser_id(rs.getString("user_id"));
				m.setPass(rs.getString("pass"));
				m.setEmail(rs.getString("email"));
				m.setAddress(rs.getString("address"));
				m.setRace(rs.getString("race"));
				m.setAge(Integer.parseInt(rs.getString("age")));
				m.setTimestamp(rs.getString("timestamp"));
				m.setGrade(rs.getString("grade"));			
			}
		}catch(Exception e) {
			System.out.println("멤버 조회중 오류발생"+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("멤버 조회 종료중 오류발생"+ex);
			}
		}		
		return m;
	}
	//아이디 중복 체크
	public int confirmId(String user_id) {
		int result = -1;
		String sql = "select user_id from member where user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			AllDAO aDAO = AllDAO.getInstance();
			conn = aDAO.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1; // 아이디 있음
			}else {
				result = -1; //아이디 없음
			}
		}catch(Exception e) {
			System.out.println("아이디 체크 중 오류발생 : "+e);
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("아이디체크 종료중 오류발생 :"+ex);
			}
		}
		
		return result;
	}
	//회원가입
	public int insertMember(MemberDTO m) {
		int result = -1;
		String sql = "insert into member(user_id, pass, email, address, race, age, timestamp, grade) values(?,?,?,?,?,?,now(),'1');";
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		AllDAO aDAO = AllDAO.getInstance();
		try {
			conn = aDAO.getConnection();			
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, m.getUser_id());
			pstmt.setString(2, m.getPass());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getRace());
			pstmt.setInt(6, m.getAge());
			pstmt.setString(7, m.getGrade());			
			result = pstmt.executeUpdate(); //변경된 수가 리턴 1 반환
					
		}catch(Exception e) {
			System.out.println("가입절차 중 오류발생"+e);
		}finally{
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("가입절차 접속 종료중 오류발생"+ex);
			}
		}
		
		return result;
	}
	//회원정보 수정
	public int updateMember(MemberDTO m) {
		int result = -1;
		String sql = "update member set pass=?, email=?, address=?, race=?, age=?, grade=?;";
				Connection conn=null;
		PreparedStatement pstmt = null;
		try {
			AllDAO aDAO = AllDAO.getInstance();
			conn=aDAO.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getPass());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getAddress());
			pstmt.setString(4, m.getRace());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getGrade());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("DAO.updateMember() 수행 중 접속 오류 발생 :"+e);			
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception ex) {
				System.out.println("DAO.updateMember() 수행중 접속 종료 오류 발생 : "+ex);
			}
		}	
		return result;
	}
	//회원 리스트
		public List<MemberDTO> findList(int currentPage, int recordsPerPage) {
			List<MemberDTO> List = new ArrayList<MemberDTO>();
			MemberDTO m = null;
			String sql = "select * from member";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				AllDAO aDAO = AllDAO.getInstance();
				conn = aDAO.getConnection();
				pstmt = conn.prepareStatement(sql);	
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					m= new MemberDTO();
					m.setNum(Integer.parseInt(rs.getString("num")));
					m.setUser_id(rs.getString("user_id"));
					m.setPass(rs.getString("pass"));
					m.setEmail(rs.getString("email"));
					m.setAddress(rs.getString("address"));
					m.setRace(rs.getString("race"));
					m.setAge(Integer.parseInt(rs.getString("age")));
					m.setTimestamp(rs.getString("timestamp"));
					m.setGrade(rs.getString("grade"));	
					
					List.add(m);
				}
			}catch(Exception e) {
				System.out.println("멤버 조회중 오류발생"+e);
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				}catch(Exception ex) {
					System.out.println("멤버 조회 종료중 오류발생"+ex);
				}
			}		
			return List;
		}
		
//		전체 자료의 갯수를 가져오는 동작
		public int getNumberOfRows() {
			String sql = "select count(*) from member;";
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
				System.out.println("mDAO.getNumberOfRows 접속 중 오류발생 : "+e);
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				}catch(Exception ex) {
					System.out.println("mDAO.getNumberOfRows 접속 종료 중 오류발생 : "+ex);
				}
			}
			return numberOfRows;
		}
}
