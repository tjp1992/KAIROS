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

	public int deletePlaylist(ArrayList<Playlist> list, String userId) {
		Connection conn =JDBCTemplate.getConnection();
		int result =0;
		int rnum = 0;
		
		for(Playlist p : list) {
			result = new PlaylistDao().deletePlaylist(conn, p ,userId);
			if(result>0) {
				rnum++;
			}
		}
		
		
		if(rnum==list.size()) {
			
			JDBCTemplate.commit(conn);			
		}else {
			
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return rnum;
	}

	public int updateSongPlist(String[] arr, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0; 
		int rnum = 0;
		int count = new PlaylistDao().checkCountPlist(conn,userId);
		for(String str:arr) {
			result = new PlaylistDao().updateSongPlist(conn,Integer.parseInt(str),userId,count);
			if(result>0) {
				rnum++;
				count++;
			}
		}
		if(rnum==arr.length) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int sortPlaylist(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Playlist> list = new PlaylistDao().myPlaylistView(conn, userId);			
		int index = 1;
		int result = 0;
		int unum = 0;
		
		for(Playlist p : list) {
			
			result = new PlaylistDao().sortOrderNo(conn, userId, p, index++);
			if(result>0) {
				unum++;
			}
		}
				
		if(unum == list.size()) {
			
			JDBCTemplate.commit(conn);
			result = 1;
		}else {
		
			JDBCTemplate.rollback(conn);
			result = 0;
		}
		
		
		return result;
	}

	public int deleteOnePlaylist(Playlist p, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PlaylistDao().deletePlaylist(conn, p, userId);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Playlist> pSearchKeyword(String userId, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Playlist> list = new PlaylistDao().pSearchSongTitle(conn,userId,keyword);
		ArrayList<Playlist>list2 = new PlaylistDao().pSearchSongArtist(conn,userId,keyword);
		ArrayList<Playlist>list3 = new PlaylistDao().pSearchAlbumName(conn,userId,keyword);
		for(int i=0 ; i<list2.size(); i++) {
			list.add(list2.get(i));
		}
		for(int i=0; i<list3.size(); i++) {
			list.add(list3.get(i));
		}
		JDBCTemplate.close(conn);
		return list;
	}

	public int addOnePlist(String userId, int songNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new PlaylistDao().checkCountPlist(conn,userId);
		int result = new PlaylistDao().updateSongPlist(conn, songNo, userId, count);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int sortPlaylist(ArrayList<Playlist> list, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int unum = 0;
		int index=1;
		int result =0;
		for(Playlist p:list) {
			result = new PlaylistDao().sortOrderNo(conn, userId, p, index++);
			if(result>0) {
				unum++;
				System.out.println(unum);
			}
		}
		
		if(unum==list.size()) {
			JDBCTemplate.commit(conn);
			result=1;
		}else {
			JDBCTemplate.rollback(conn);
			result=0;
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	

}
