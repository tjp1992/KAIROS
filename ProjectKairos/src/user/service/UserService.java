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

}
