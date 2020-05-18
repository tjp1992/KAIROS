package manageMusic.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import manageMusic.model.dao.SessionPlayListDao;
import playlist.vo.SessionPlaylist;

public class SessionPlayListService {

	public ArrayList<SessionPlaylist> readPlayList(String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<SessionPlaylist> list = new SessionPlayListDao().readPlayList(conn, userId );
		
		JDBCTemplate.close(conn);
		
		return list;
	}

}
