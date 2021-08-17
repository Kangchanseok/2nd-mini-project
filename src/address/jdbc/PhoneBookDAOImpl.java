package address.jdbc;


import java.sql.*;
import java.util.*;


public class PhoneBookDAOImpl implements PhoneBookDAO {

	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl,"c##bituser", "bituser");
			
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
		}
		return conn;
	}
	@Override
	public List<PhoneBookVO> getList() {
		List<PhoneBookVO> list = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT name, hp, tel FROM PHONE_BOOK";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String name = rs.getString(1);
				String hp = rs.getString(2);
				String tel = rs.getString(3);
				
				PhoneBookVO vo = new PhoneBookVO(name, hp, tel);
							
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public boolean insert(PhoneBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO PHONE_BOOK VALUES(seq_book_id.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, vo.getPhoneName());
			pstmt.setString(2, vo.getPhoneHp());
			pstmt.setString(3, vo.getPhoneTel());
			
			insertedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		return 1 == insertedCount;
		}


	@Override
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;

		try {
			conn = getConnection();
			String sql = "DELETE FROM PHONE_BOOK WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1 == deletedCount;
	}

	@Override
	public List<PhoneBookVO> search(String keyword) {
		List<PhoneBookVO> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT name, hp, tel FROM PHONE_BOOK WHERE name LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			
			rs = pstmt.executeQuery();
			
			//	ResultSet -> List 변환
			while(rs.next()) {
				
				String name = rs.getString(1);
				String hp = rs.getString(2);
				String tel = rs.getString(3);
				
				PhoneBookVO vo = new PhoneBookVO(name, hp, tel);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
		
	
	
	
	
	
}
