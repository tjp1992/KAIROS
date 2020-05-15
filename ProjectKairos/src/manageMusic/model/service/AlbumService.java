package manageMusic.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import manageMusic.model.dao.AlbumDao;
import manageMusic.model.vo.Album;

public class AlbumService {

	public int insertAlbum(String albumOwner, String albumName) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Album a = new AlbumDao().selectOneAlbum(conn, albumOwner, albumName);
		
		if(a != null) {
			return -1;
		}
		
		int result = new AlbumDao().insertAlbum(conn, albumOwner, albumName);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);			
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
