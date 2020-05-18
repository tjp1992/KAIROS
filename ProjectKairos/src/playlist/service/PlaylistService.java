package playlist.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import likelist.dao.LikelistDao;
import playlist.dao.PlaylistDao;
import playlist.vo.Playlist;
import user.vo.User;

public class PlaylistService {

	public ArrayList<Playlist> myPlaylistView(String userId) {
		Connection conn =JDBCTemplate.getConnection();
		ArrayList<Playlist> list = new PlaylistDao().myPlaylistView(conn,userId);
		JDBCTemplate.close(conn);
		return list;
	}

	public int deletePlaylist(String[] arr, String userId) {
		Connection conn =JDBCTemplate.getConnection();
		int result =0;
		int rnum = 0;
		
		for(String str : arr) {
			result = new PlaylistDao().deletePlaylist(conn,Integer.parseInt(str),userId);
			if(result>0) {
				rnum++;
			}
		}
		
		if(rnum==arr.length) {
			JDBCTemplate.commit(conn);
			
			int unum=0;
			
			ArrayList<Playlist> list = new PlaylistDao().myPlaylistView(conn, userId);
			int index =1;
			for(Playlist p : list) {
				result = new PlaylistDao().sortOrderNo(conn, userId, p.getSongNo(), index++);
				if(result>0) {
					unum++;
				}
			}
			
			if(unum == list.size()) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
