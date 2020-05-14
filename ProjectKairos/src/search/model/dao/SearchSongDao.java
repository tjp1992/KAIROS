package search.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import song.vo.SearchSong;

public class SearchSongDao {

	public ArrayList<SearchSong> searchByKeword(Connection conn, String keyword, int start, int end) {
		
		ArrayList<SearchSong> list = new ArrayList<SearchSong>();
		PreparedStatement pst = null;
		ResultSet rset = null;
		
		String query = "select * from (select rownum,t.* from (select s.*,(select album_name from album a where a.album_no = s.album_no and a.album_owner = s.song_artist)as album_name from song s order by S.LIKE_COUNT desc)t where song_title like '%"+keyword+"%' or song_artist like '%"+keyword+"%' or album_name like '%"+keyword+"%') where rownum between ? and ?";
		
		
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
		String query = "select count(*) from (select rownum,t.* from (select s.*,(select album_name from album a where a.album_no = s.album_no and a.album_owner = s.song_artist)as album_name from song s order by S.LIKE_COUNT desc)t where song_title like '%"+keyword+"%' or song_artist like '%"+keyword+"%' or album_name like '%"+keyword+"%')";
		
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

}
