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

	public ArrayList<Playlist> myPlaylistView(Connection conn, User u) {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		ArrayList<Playlist> list = new ArrayList<Playlist>();
		String query = "select (select order_no from playlist where user_id = ? and listed_song_no=song.song_no) "
				+ "as order_no, song_title, song_artist, album_name "
				+ "from song join album using (album_no) "
				+ "where song_no in (select LISTED_SONG_NO from playlist where user_id =?) "
				+ "order by order_no";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, u.getUserId());
			pstmt.setString(2, u.getUserId());
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Playlist p = new Playlist();
				p.setOrderNo(rset.getInt("order_no"));
				p.setSongTitle(rset.getString("song_title"));
				p.setSongArtist(rset.getString("song_artist"));
				p.setAlbumName(rset.getString("album_name"));
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

}
