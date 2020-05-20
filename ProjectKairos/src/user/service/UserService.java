package user.service;

import java.sql.Connection;

import common.JDBCTemplate;
import user.dao.UserDao;
import user.vo.User;

public class UserService {

	public User selectUser(User user) {
		Connection conn = JDBCTemplate.getConnection();
		User login = new UserDao().selectUser(conn, user);
		JDBCTemplate.close(conn);
		return login;
	}

	public String overLapId(String id) {
		Connection conn = JDBCTemplate.getConnection();
		String userId = new UserDao().overLapId(conn,id);
		JDBCTemplate.close(conn);
		return userId;
	}
	public int buyVoucher(User u) {
		int result =0;
		Connection conn = JDBCTemplate.getConnection();
		result = new UserDao().buyVoucher(conn, u);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public String overLapNick(String nick) {
		Connection conn = JDBCTemplate.getConnection();
		String userNick = new UserDao().overLapNick(conn,nick);
		JDBCTemplate.close(conn);
		return userNick;
	}

	public int insertUser(User u) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new UserDao().insertUser(conn,u);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public User idSearch(String name, String mail) {
			Connection conn = JDBCTemplate.getConnection();
			User user = new UserDao().idSearch(name,mail,conn);
			JDBCTemplate.close(conn);
		return user;
	}

	public User pwSearch(String id, String name, String phone) {
		Connection conn = JDBCTemplate.getConnection();
		User user = new UserDao().pwSearch(id,name,phone,conn);
		JDBCTemplate.close(conn);
	return user;
	}

	public int modifyUser(User u) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new UserDao().modifyUser(conn,u);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteUser(User u) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new UserDao().deleteUser(conn,u);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int voucherUpdate(String personalId, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new UserDao().updatePurchaseLog(conn,personalId,userId);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

}
