package search.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import song.vo.SearchSong;

public class SearchSongDao {
	
	public ArrayList<SearchSong> searchMyList(Connection conn, String userNick, int start, int end) {

		PreparedStatement pst = null;
		ResultSet rset = null;

		ArrayList<SearchSong> list = new ArrayList<SearchSong>();

		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, T.* FROM(SELECT * FROM SONG S JOIN ALBUM A USING(ALBUM_NO) WHERE song_artist = ? ORDER BY S.LIKE_COUNT DESC)T ORDER BY ROWNUM) WHERE RNUM BETWEEN ? AND ?";

		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, userNick);
			pst.setInt(2, start);
			pst.setInt(3, end);

			rset = pst.executeQuery();

			while (rset.next()) {
				SearchSong s = new SearchSong();

				s.setRowNum(rset.getInt("rnum"));
				s.setSongNo(rset.getInt("song_no"));
				s.setSongTitle(rset.getString("song_title"));
				s.setSongArtist(rset.getString("song_artist"));
				s.setSongGenre(rset.getString("song_genre"));
				s.setAlbumName(rset.getString("album_name"));
				s.setLicensed(rset.getInt("licensed"));
				s.setLikeCount(rset.getInt("like_count"));
				s.setPlayCount(rset.getInt("play_count"));

				list.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}

		return list;
	}

	public int getTotalCountMyList(Connection conn, String userNick) {

		int totalCount = 0;

		PreparedStatement pst = null;
		ResultSet rset = null;

		String query = "select count(*) as cnt from(SELECT ROWNUM, T.* FROM(SELECT * FROM SONG S JOIN ALBUM A USING(ALBUM_NO) WHERE song_artist = ? ORDER BY S.LIKE_COUNT DESC)T ORDER BY ROWNUM)";

		try {
			pst = conn.prepareStatement(query);

			pst.setString(1, userNick);

			rset = pst.executeQuery();

			if (rset.next()) {
				totalCount = rset.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}

		return totalCount;
	}

	public int getTotalCountReSearchMyList(Connection conn, String userNick, String reSearch) {

		int totalCount = 0;

		PreparedStatement pst = null;
		ResultSet rset = null;

		String query = "select count(*) as cnt from(SELECT ROWNUM, T.* FROM(SELECT * FROM SONG S JOIN ALBUM A USING(ALBUM_NO) WHERE song_artist = ? ORDER BY S.LIKE_COUNT DESC)T WHERE SONG_TITLE LIKE '%"
				+ reSearch + "%' OR ALBUM_NAME LIKE '%" + reSearch + "%' ORDER BY ROWNUM)";

		try {
			pst = conn.prepareStatement(query);

			pst.setString(1, userNick);

			rset = pst.executeQuery();

			if (rset.next()) {
				totalCount = rset.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}

		return totalCount;
	}

	public ArrayList<SearchSong> searchMyList(Connection conn, String userNick, String reSearch, int start, int end) {

		ArrayList<SearchSong> list = new ArrayList<SearchSong>();

		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, T.* FROM(SELECT * FROM SONG S JOIN ALBUM A USING(ALBUM_NO) WHERE song_artist = ? ORDER BY S.LIKE_COUNT DESC)T WHERE SONG_TITLE LIKE '%"
				+ reSearch + "%' OR ALBUM_NAME LIKE '%" + reSearch + "%' ORDER BY ROWNUM) WHERE RNUM BETWEEN ? AND ?";

		PreparedStatement pst = null;
		ResultSet rset = null;

		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, userNick);
			pst.setInt(2, start);
			pst.setInt(3, end);

			rset = pst.executeQuery();

			while (rset.next()) {
				SearchSong s = new SearchSong();

				s.setRowNum(rset.getInt("rnum"));
				s.setSongNo(rset.getInt("song_no"));
				s.setSongTitle(rset.getString("song_title"));
				s.setSongArtist(rset.getString("song_artist"));
				s.setSongGenre(rset.getString("song_genre"));
				s.setAlbumName(rset.getString("album_name"));
				s.setLicensed(rset.getInt("licensed"));
				s.setLikeCount(rset.getInt("like_count"));
				s.setPlayCount(rset.getInt("play_count"));

				list.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}

		return list;
	}
	public int getTotalCount(Connection conn, String query) {

		int result = 0;
		
		PreparedStatement pst = null;
		ResultSet rset = null;
		
		try {
			pst = conn.prepareStatement(query);
			rset = pst.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}
	
		
		return result;
	}

	public ArrayList<SearchSong> searchSong(Connection conn, String query, int start, int end) {
		
		ArrayList<SearchSong> list = new ArrayList<SearchSong>();
		PreparedStatement pst = null;
		ResultSet rset = null;
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, start);
			pst.setInt(2, end);
			rset = pst.executeQuery();
			
			while(rset.next()) {
				SearchSong s = new SearchSong();
				
				s.setAlbumName(rset.getString("album_name"));
				s.setLicensed(rset.getInt("licensed"));
				s.setLikeCount(rset.getInt("like_count"));
				s.setPlayCount(rset.getInt("play_count"));
				s.setRowNum(rset.getInt("rnum"));
				s.setSongArtist(rset.getString("song_artist"));
				s.setSongTitle(rset.getString("song_title"));
				s.setSongNo(rset.getInt("song_no"));
				if(query.contains("NVL")) {
					s.setLiked(rset.getInt("liked"));
				}
				
				list.add(s);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}		
		
		return list;
	}

}
