package search.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import song.vo.SearchSong;
import user.vo.User;

public class SearchSongDao {

	public ArrayList<SearchSong> searchByKeword(Connection conn, String keyword, int start, int end) {
		
		ArrayList<SearchSong> list = new ArrayList<SearchSong>();
		PreparedStatement pst = null;
		ResultSet rset = null;
		
		String query = "SELECT ROWNUM, T.* FROM(SELECT * FROM SONG S JOIN ALBUM A USING(ALBUM_NO) WHERE song_title like '%"+keyword+"%' or song_artist like '%"+keyword+"%' or album_name like '%"+keyword+"%' ORDER BY S.LIKE_COUNT DESC)T WHERE ROWNUM BETWEEN ? AND ? ORDER BY ROWNUM";
		
		
		try {
			pst = conn.prepareStatement(query);
			
			pst.setInt(1, start);
			pst.setInt(2, end);
			
			rset = pst.executeQuery();
			
			while(rset.next()) {
				SearchSong s = new SearchSong();
				
				s.setAlbumName(rset.getString("album_name"));
				s.setAlbumNo(rset.getInt("album_no"));
				s.setFilename(rset.getString("filename"));
				s.setFilepath(rset.getString("filepath"));
				s.setLicensed(rset.getInt("licensed"));
				s.setLikeCount(rset.getInt("like_count"));
				s.setPlayCount(rset.getInt("play_count"));
				s.setRowNum(rset.getInt("rownum"));
				s.setSongArtist(rset.getString("song_artist"));
				s.setSongGenre(rset.getString("song_genre"));
				s.setSongNo(rset.getInt("song_no"));
				s.setSongTitle(rset.getString("song_title"));
				s.setLiked(0);
				
				list.add(s);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}
		
		return list;
	}

	public int getTotalCount(Connection conn, String keyword) {
		
		int totalResult = 0; 
		
		PreparedStatement pst = null;
		ResultSet rset = null;
		String query = "select count(*) from (SELECT * FROM SONG S JOIN ALBUM A USING(ALBUM_NO) WHERE song_title like '%"+keyword+"%' or song_artist like '%"+keyword+"%' or album_name like '%"+keyword+"%' ORDER BY S.LIKE_COUNT DESC)";
		
		try {
			pst = conn.prepareStatement(query);
			
			rset = pst.executeQuery();
			
			if(rset.next()) {
				totalResult = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}
				
		return totalResult;
	}

	public ArrayList<SearchSong> searchByKeword(Connection conn, String keyword, String category, User u, int start, int end) {


		ArrayList<SearchSong> list = new ArrayList<SearchSong>();
		PreparedStatement pst = null;
		ResultSet rset = null;
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT ROWNUM, T.*");
		
		if(u != null) {
			sb.append(",NVL((SELECT LIKED_SONG_NO FROM LIKELIST WHERE LIKED_SONG_NO = SONG_NO),0)AS LIKED ");
		}
		
		sb.append("FROM(SELECT * FROM SONG S JOIN ALBUM A USING(ALBUM_NO)");
		
		if(category != null) {
			sb.append("WHERE "+category+" like '%"+keyword+"%' ORDER BY S.LIKE_COUNT DESC)T WHERE ROWNUM BETWEEN ? AND ? ORDER BY ROWNUM");
		} else {
			sb.append("WHERE song_title like '%"+keyword+"%' or song_artist like '%"+keyword+"%' or album_name like '%"+keyword+"%' ORDER BY S.LIKE_COUNT DESC)T WHERE ROWNUM BETWEEN ? AND ? ORDER BY ROWNUM");
		}		
		
		String query = sb.toString();
					
		try {
			pst = conn.prepareStatement(query);
			
			pst.setInt(1, start);
			pst.setInt(2, end);
			
			rset = pst.executeQuery();
			
			while(rset.next()) {
				SearchSong s = new SearchSong();
				
				s.setAlbumName(rset.getString("album_name"));
				s.setAlbumNo(rset.getInt("album_no"));
				s.setFilename(rset.getString("filename"));
				s.setFilepath(rset.getString("filepath"));
				s.setLicensed(rset.getInt("licensed"));
				s.setLikeCount(rset.getInt("like_count"));
				s.setPlayCount(rset.getInt("play_count"));
				s.setRowNum(rset.getInt("rownum"));
				s.setSongArtist(rset.getString("song_artist"));
				s.setSongGenre(rset.getString("song_genre"));
				s.setSongNo(rset.getInt("song_no"));
				s.setSongTitle(rset.getString("song_title"));
				if(u!=null) {
					s.setLiked(rset.getInt("liked"));					
				} else {
					s.setLiked(0);
				}
				
				list.add(s);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}
		
		return list;
	}

}
