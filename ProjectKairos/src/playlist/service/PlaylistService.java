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
		
		int unum = 0;		
		int result = new PlaylistDao().deletePlaylist(conn, userId);	
		
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
		
		for(int i=0 ; i<list2.size(); i++) {
			list.add(list2.get(i));
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
		int result = new PlaylistDao().deletePlaylist(conn, userId);	
		
		
		if(result>0) {
			
			for(Playlist p:list) {
				
				result = new PlaylistDao().sortOrderNo(conn, userId, p, index++);
				if(result>0) {
					unum++;
					
				}
			}
		} else {
			JDBCTemplate.rollback(conn);
			return result;
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

	public ArrayList<Playlist> orderArtistAsc(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Playlist> list = new PlaylistDao().orderArtistAsc(conn,userId);		
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Playlist> orderArtistDesc(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Playlist> list = new PlaylistDao().orderArtistDesc(conn, userId);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Playlist> orderTitleAsc(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Playlist> list = new PlaylistDao().orderTitleAsc(conn, userId);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Playlist> orderTitleDesc(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Playlist> list = new PlaylistDao().orderTitleDesc(conn, userId);
		JDBCTemplate.close(conn);
		return list;
	}

	public int frontAdd(ArrayList<Playlist> list, String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Playlist> list2 = new PlaylistDao().myPlaylistView(conn, userId);
		
		int nextnum = list.size()+1;
		
		int result =0;
		int unum=0;
		int inum = 0;
		
		result = new PlaylistDao().deletePlaylist(conn, userId);
		
		
		
		if(result == list2.size()) {
						
			for(Playlist p :list2) {
				
				result = new PlaylistDao().insertPlaylist(conn, userId, p, nextnum++);
				
//				result = new PlaylistDao().sortOrderNo(conn, userId, p, nextnum++);
				if(result >0) {
					unum++;
				}
				
			}
		}				
		
		if(unum==list2.size()) {
			for(int i=0; i<list.size(); i++) {
				result = new PlaylistDao().updateSongPlist(conn, list.get(i).getSongNo(), userId, i);
				if(result>0) {
					inum++;
				}
			}
			if(inum==list.size()) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
				result = 0;
			}
		}else {
			JDBCTemplate.rollback(conn);
			result = 0;
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	

}
