package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import user.vo.User;

public class UserDao {

	public User selectUser(Connection conn, User user) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User login = null;
		String query = "select * from web_user where user_id=? and user_pw =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				login = new User();
				login.setUserId(rset.getString("user_id"));
				login.setUserPw(rset.getString("user_pw"));
				login.setAddr(rset.getString("address"));
				login.setConAgree(rset.getInt("con_agree"));
				login.setEmail(rset.getString("email"));
				login.setExpiredDate(rset.getDate("expired_date"));
				login.setPhone(rset.getString("phone"));
				login.setUserName(rset.getString("user_name"));
				login.setUserNick(rset.getString("user_nickname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return login;
	}

	public String overLapId(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String userId = null;
		String query = "select user_id from web_user where user_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				userId = rset.getString("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return userId;
	}
	public int buyVoucher(Connection conn, User u) {
		PreparedStatement pstmt =null;
		int result=0;
		String query = "update web_user set expired_date = sysdate+30 where user_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, u.getUserId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public String overLapNick(Connection conn, String nick) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String userNick = null;
		String query = "select user_nickname from web_user where user_nickname=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nick);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				userNick = rset.getString("user_nickname");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return userNick;
	}

	public int insertUser(Connection conn, User u) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into web_user values (?,?,?,?,?,?,?,null,'0')";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, u.getUserId());
			pstmt.setString(2, u.getUserPw());
			pstmt.setString(3, u.getUserName());
			pstmt.setString(4, u.getUserNick());
			pstmt.setString(5, u.getPhone());
			pstmt.setString(6, u.getEmail());
			pstmt.setString(7, u.getAddr());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public User idSearch(String name, String mail, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User user = null;
		String query = "select * from web_user where user_name=? and email=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, mail);
			rset= pstmt.executeQuery();
			if(rset.next()){
				user = new User();
				user.setUserId(rset.getString("user_id"));
				user.setUserPw(rset.getString("user_pw"));
				user.setUserName(rset.getString("user_name"));
				user.setUserNick(rset.getString("user_nickname"));
				user.setPhone(rset.getString("phone"));
				user.setEmail(rset.getString("email"));
				user.setAddr(rset.getString("address"));
				user.setExpiredDate(rset.getDate("expired_date"));
				user.setConAgree(rset.getInt("con_agree"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return user;
	}

	public User pwSearch(String id, String name, String phone, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User user = null;
		String query = "select * from web_user where user_id=? and user_name=? and phone=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			rset= pstmt.executeQuery();
			if(rset.next()){
				user = new User();
				user.setUserId(rset.getString("user_id"));
				user.setUserPw(rset.getString("user_pw"));
				user.setUserName(rset.getString("user_name"));
				user.setUserNick(rset.getString("user_nickname"));
				user.setPhone(rset.getString("phone"));
				user.setEmail(rset.getString("email"));
				user.setAddr(rset.getString("address"));
				user.setExpiredDate(rset.getDate("expired_date"));
				user.setConAgree(rset.getInt("con_agree"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return user;
	}

	public int modifyUser(Connection conn, User u) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update web_user set user_pw=?,user_name=?,user_nickname=?,phone=?,email=?,address=? where user_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, u.getUserPw());
			pstmt.setString(2, u.getUserName());
			pstmt.setString(3, u.getUserNick());
			pstmt.setString(4, u.getPhone());
			pstmt.setString(5, u.getEmail());
			pstmt.setString(6, u.getAddr());
			pstmt.setString(7, u.getUserId());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteUser(Connection conn, User u) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from web_user where user_id=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, u.getUserId());
			System.out.println(u.getUserId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updatePurchaseLog(Connection conn, String personalId, String userId) {
		PreparedStatement pstmt =null;
		int result = 0;
		String query = "insert into purchase_log values(?, 1, ?,sysdate,sysdate,sysdate+30)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personalId);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
}
