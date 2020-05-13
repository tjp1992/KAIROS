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
	
}
