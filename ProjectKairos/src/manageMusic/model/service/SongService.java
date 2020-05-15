package manageMusic.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import manageMusic.model.dao.SongDao;
import song.vo.Song;

public class SongService {

	public int insertSong(Song s) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new SongDao().insertSong(conn, s);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}
	
}
