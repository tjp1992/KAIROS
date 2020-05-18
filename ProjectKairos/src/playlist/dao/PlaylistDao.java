package playlist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import playlist.vo.Playlist;
import user.vo.User;

public class PlaylistDao {

	public ArrayList<Playlist> myPlaylistView(Connection conn,String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		ArrayList<Playlist> list = new ArrayList<Playlist>();
//		String query = "select (select order_no from playlist where user_id = ? and listed_song_no=song.song_no) "
//				+ "as order_no,song_no, song_title, song_artist,filepath, album_name "
//				+ "from song join album using (album_no) "
//				+ "where song_no in (select LISTED_SONG_NO from playlist where user_id =?) "
//				+ "order by order_no";
		
		String query ="SELECT T.*,NVL((SELECT LIKED_SONG_NO FROM LIKELIST WHERE LIKED_SONG_NO = LISTED_SONG_NO),0)as liked "
				+ "FROM (SELECT * FROM PLAYLIST P JOIN SONG S ON (LISTED_SONG_NO = SONG_NO) JOIN ALBUM A USING(ALBUM_NO) WHERE USER_ID = ?) T ORDER BY ORDER_NO ASC";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Playlist p = new Playlist();
				p.setOrderNo(rset.getInt("order_no"));
				p.setSongNo(rset.getInt("song_no"));
				p.setSongTitle(rset.getString("song_title"));
				p.setSongArtist(rset.getString("song_artist"));
				p.setAlbumName(rset.getString("album_name"));
				p.setFilepath(rset.getString("filepath"));
				p.setLiked(rset.getInt("liked"));
				if(p.getLiked()>0) {
					p.setLiked(1);
				}
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);			
		}
		return list;
	}

	public int deletePlaylist(Connection conn, int songNo, String userId) {
		PreparedStatement pstmt = null;
		int result = 0; 
		String query = "delete from playlist where user_id =? and listed_song_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, songNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int sortOrderNo(Connection conn, String userId, int songNo, int orderNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update playlist set order_no =? where listed_song_no = ? and user_id =?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, songNo);
			pstmt.setString(3, userId);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}
