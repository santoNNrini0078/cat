package DAO;

import java.sql.*;

public class AllDAO {
	protected AllDAO() {
		
	}
	private static AllDAO instance = new AllDAO();
	
	public static AllDAO getInstance() {
		return instance;
	}
	public Connection getConnection() throws Exception{
		Connection conn=null;
		String url="jdbc:mysql://localhost:3306/cat";
		String db_id="root";
		String db_pw="rlaxogml1";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection(url,db_id,db_pw);	
		
		return conn;
	}
	
//	전체 자료의 갯수를 가져오는 동작
			public int getNumberOfRows(String str, String search) {
				String sql = "select count(*) from "+str+" where (name like '%"+search+"%' or addr like '%"+search+"%' or addr2 like '%"+search+"%') and stat='정상';";
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
					System.out.println("aDAO.getNumberOfRows 접속 중 오류발생 : "+e);
				}finally {
					try {
						if(rs!=null)rs.close();
						if(pstmt!=null)pstmt.close();
						if(conn!=null)conn.close();
					}catch(Exception ex) {
						System.out.println("aDAO.getNumberOfRows 접속 종료 중 오류발생 : "+ex);
					}
				}
				return numberOfRows;
			}
}
