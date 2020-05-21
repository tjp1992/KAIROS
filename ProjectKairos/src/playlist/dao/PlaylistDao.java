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

	public ArrayList<Playlist> myPlaylistView(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Playlist> list = new ArrayList<Playlist>();
//		String query = "select (select order_no from playlist where user_id = ? and listed_song_no=song.song_no) "
//				+ "as order_no,song_no, song_title, song_artist,filepath, album_name "
//				+ "from song join album using (album_no) "
//				+ "where song_no in (select LISTED_SONG_NO from playlist where user_id =?) "
//				+ "order by order_no";

		String query = "SELECT T.*,NVL((SELECT LIKED_SONG_NO FROM LIKELIST WHERE LIKED_SONG_NO = LISTED_SONG_NO AND USER_ID = ?),0)as liked "
				+ "FROM (SELECT * FROM PLAYLIST P JOIN SONG S ON (LISTED_SONG_NO = SONG_NO) JOIN ALBUM A USING(ALBUM_NO) WHERE USER_ID = ?) "
				+ "T ORDER BY ORDER_NO ASC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Playlist p = new Playlist();
				p.setOrderNo(rset.getInt("order_no"));
				p.setSongNo(rset.getInt("song_no"));
				p.setSongTitle(rset.getString("song_title"));
				p.setSongArtist(rset.getString("song_artist"));
				p.setAlbumName(rset.getString("album_name"));
				p.setFilepath(rset.getString("filepath"));
				p.setLiked(rset.getInt("liked"));
				if (p.getLiked() > 0) {
					p.setLiked(1);
				}
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int deletePlaylist(Connection conn, Playlist p, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from playlist where user_id =? and listed_song_no=? and order_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, p.getSongNo());
			pstmt.setInt(3, p.getOrderNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	
	public int insertPlaylist(Connection conn, String userId, Playlist p, int index) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into playlist values(?,?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, p.getSongNo());
			pstmt.setInt(3, index);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}
	
	public int sortOrderNo(Connection conn, String userId, Playlist p, int index) {
		int result = 0;
		PreparedStatement pstmt = null;
//		String query = "update playlist set order_no =? where listed_song_no = ? and user_id =?  and order_no = ?";

		String query = "insert into playlist values(?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, p.getSongNo());
			pstmt.setInt(3, index);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int updateSongPlist(Connection conn, int songNo, String userId, int count) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into playlist values (?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, songNo);
			pstmt.setInt(3, count + 1);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int checkCountPlist(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;

		String query = "SELECT COUNT(*)AS CNT FROM PLAYLIST WHERE USER_ID =?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				count = rset.getInt("CNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return count;
	}

	public ArrayList<Playlist> pSearchSongTitle(Connection conn, String userId, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Playlist> list = new ArrayList<Playlist>();
		String query = "SELECT * FROM (SELECT T.*,NVL((SELECT LIKED_SONG_NO FROM LIKELIST WHERE LIKED_SONG_NO = LISTED_SONG_NO AND USER_ID = ?),0)as liked "
				+ "FROM (SELECT * FROM PLAYLIST P JOIN SONG S "
				+ "ON (LISTED_SONG_NO = SONG_NO) JOIN ALBUM A USING(ALBUM_NO) "
				+ "WHERE USER_ID = ? ) T) WHERE SONG_TITLE LIKE ? ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			pstmt.setString(3, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Playlist p = new Playlist();
				p.setOrderNo(rset.getInt("order_no"));
				p.setSongNo(rset.getInt("song_no"));
				p.setSongTitle(rset.getString("song_title"));
				p.setSongArtist(rset.getString("song_artist"));
				p.setAlbumName(rset.getString("album_name"));
				p.setFilepath(rset.getString("filepath"));
				p.setLiked(rset.getInt("liked"));
				if (p.getLiked() > 0) {
					p.setLiked(1);
				}
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Playlist> pSearchSongArtist(Connection conn, String userId, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Playlist> list = new ArrayList<Playlist>();

		String query = "SELECT * FROM (SELECT T.*,NVL((SELECT LIKED_SONG_NO FROM LIKELIST WHERE LIKED_SONG_NO = LISTED_SONG_NO AND USER_ID = ?),0)as liked "
				+ "FROM (SELECT * FROM PLAYLIST P JOIN SONG S "
				+ "ON (LISTED_SONG_NO = SONG_NO) JOIN ALBUM A USING(ALBUM_NO) "
				+ "WHERE USER_ID = ? ) T) WHERE SONG_ARTIST LIKE ? ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			pstmt.setString(3, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Playlist p = new Playlist();
				p.setOrderNo(rset.getInt("order_no"));
				p.setSongNo(rset.getInt("song_no"));
				p.setSongTitle(rset.getString("song_title"));
				p.setSongArtist(rset.getString("song_artist"));
				p.setAlbumName(rset.getString("album_name"));
				p.setFilepath(rset.getString("filepath"));
				p.setLiked(rset.getInt("liked"));
				if (p.getLiked() > 0) {
					p.setLiked(1);
				}
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Playlist> orderArtistAsc(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Playlist> list = new ArrayList<Playlist>();
		String query = "SELECT T.*,NVL((SELECT LIKED_SONG_NO FROM LIKELIST WHERE LIKED_SONG_NO = LISTED_SONG_NO AND USER_ID = ?),0)as liked "
				+ "FROM (SELECT * FROM PLAYLIST P JOIN SONG S "
				+ "ON (LISTED_SONG_NO = SONG_NO) JOIN ALBUM A USING(ALBUM_NO) "
				+ "WHERE USER_ID = ? ) T ORDER BY SONG_ARTIST ASC";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Playlist p = new Playlist();
				p.setOrderNo(rset.getInt("order_no"));
				p.setSongNo(rset.getInt("song_no"));
				p.setSongTitle(rset.getString("song_title"));
				p.setSongArtist(rset.getString("song_artist"));
				p.setAlbumName(rset.getString("album_name"));
				p.setFilepath(rset.getString("filepath"));
				p.setLiked(rset.getInt("liked"));
				if (p.getLiked() > 0) {
					p.setLiked(1);
				}
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Playlist> orderArtistDesc(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Playlist> list = new ArrayList<Playlist>();
		String query = "SELECT T.*,NVL((SELECT LIKED_SONG_NO FROM LIKELIST WHERE LIKED_SONG_NO = LISTED_SONG_NO AND USER_ID = ?),0)as liked "
				+ "FROM (SELECT * FROM PLAYLIST P JOIN SONG S "
				+ "ON (LISTED_SONG_NO = SONG_NO) JOIN ALBUM A USING(ALBUM_NO) "
				+ "WHERE USER_ID = ? ) T ORDER BY SONG_ARTIST DESC";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Playlist p = new Playlist();
				p.setOrderNo(rset.getInt("order_no"));
				p.setSongNo(rset.getInt("song_no"));
				p.setSongTitle(rset.getString("song_title"));
				p.setSongArtist(rset.getString("song_artist"));
				p.setAlbumName(rset.getString("album_name"));
				p.setFilepath(rset.getString("filepath"));
				p.setLiked(rset.getInt("liked"));
				if (p.getLiked() > 0) {
					p.setLiked(1);
				}
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Playlist> orderTitleAsc(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Playlist> list = new ArrayList<Playlist>();
		String query = "SELECT T.*,NVL((SELECT LIKED_SONG_NO FROM LIKELIST WHERE LIKED_SONG_NO = LISTED_SONG_NO AND USER_ID = ?),0)as liked "
				+ "FROM (SELECT * FROM PLAYLIST P JOIN SONG S "
				+ "ON (LISTED_SONG_NO = SONG_NO) JOIN ALBUM A USING(ALBUM_NO) "
				+ "WHERE USER_ID = ? ) T ORDER BY SONG_TITLE ASC";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Playlist p = new Playlist();
				p.setOrderNo(rset.getInt("order_no"));
				p.setSongNo(rset.getInt("song_no"));
				p.setSongTitle(rset.getString("song_title"));
				p.setSongArtist(rset.getString("song_artist"));
				p.setAlbumName(rset.getString("album_name"));
				p.setFilepath(rset.getString("filepath"));
				p.setLiked(rset.getInt("liked"));
				if (p.getLiked() > 0) {
					p.setLiked(1);
				}
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Playlist> orderTitleDesc(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Playlist> list = new ArrayList<Playlist>();
		String query = "SELECT T.*,NVL((SELECT LIKED_SONG_NO FROM LIKELIST WHERE LIKED_SONG_NO = LISTED_SONG_NO AND USER_ID = ?),0)as liked "
				+ "FROM (SELECT * FROM PLAYLIST P JOIN SONG S "
				+ "ON (LISTED_SONG_NO = SONG_NO) JOIN ALBUM A USING(ALBUM_NO) "
				+ "WHERE USER_ID = ? ) T ORDER BY SONG_TITLE DESC";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Playlist p = new Playlist();
				p.setOrderNo(rset.getInt("order_no"));
				p.setSongNo(rset.getInt("song_no"));
				p.setSongTitle(rset.getString("song_title"));
				p.setSongArtist(rset.getString("song_artist"));
				p.setAlbumName(rset.getString("album_name"));
				p.setFilepath(rset.getString("filepath"));
				p.setLiked(rset.getInt("liked"));
				if (p.getLiked() > 0) {
					p.setLiked(1);
				}
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int deletePlaylist(Connection conn, String userId) {

		PreparedStatement pst = null;
		String query = "delete from playlist where user_id = ?";
		int result = 0;

		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, userId);

			result = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pst);
		}

		return result;
	}

}
