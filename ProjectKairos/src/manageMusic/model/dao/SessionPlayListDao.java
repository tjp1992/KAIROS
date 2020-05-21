package manageMusic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import playlist.vo.SessionPlaylist;

public class SessionPlayListDao {

	public ArrayList<SessionPlaylist> readPlayList(Connection conn, String userId) {
		
		ArrayList<SessionPlaylist> list = new ArrayList<SessionPlaylist>();

		PreparedStatement pstmt = null;
		ResultSet rset=null;
		String query ="SELECT T.*,NVL((SELECT LIKED_SONG_NO FROM LIKELIST WHERE LIKED_SONG_NO = LISTED_SONG_NO AND USER_ID = ?),0)as liked "
				+ "FROM (SELECT * FROM PLAYLIST P JOIN SONG S ON (LISTED_SONG_NO = SONG_NO) JOIN ALBUM A USING(ALBUM_NO) WHERE USER_ID = ?) T ORDER BY ORDER_NO ASC";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				SessionPlaylist sp = new SessionPlaylist();
				sp.setSongNo(rset.getInt("song_no"));
				sp.setOrderNo(rset.getInt("order_no"));
				sp.setSongTitle(rset.getString("song_title"));
				sp.setSongArtist(rset.getString("song_artist"));
				sp.setFilepath(rset.getString("filepath"));
				sp.setAlbumPath(rset.getString("album_path"));
				sp.setLiked(rset.getInt("liked"));		
				sp.setAlbumName(rset.getString("album_name"));
				sp.setLicensed(rset.getInt("licensed"));
				if(sp.getLiked()>0) {
					sp.setLiked(1);
				}
				
				list.add(sp);
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
