package playlist.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import playlist.dao.PlaylistDao;
import playlist.vo.Playlist;
import user.vo.User;

public class PlaylistService {

	public ArrayList<Playlist> myPlaylistView(User u) {
		Connection conn =JDBCTemplate.getConnection();
		ArrayList<Playlist> list = new PlaylistDao().myPlaylistView(conn,u);
		JDBCTemplate.close(conn);
		return list;
	}

}
