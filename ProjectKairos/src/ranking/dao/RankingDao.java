package ranking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import song.vo.RankingSong;
import song.vo.Song;

public class RankingDao {

	public ArrayList<RankingSong> getRankBySong(Connection conn) {
		ArrayList<RankingSong> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select rownum, n.* from(select * from song join album on (song.album_no = album.album_no) order by like_count desc)n";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			list = new ArrayList<RankingSong>();				
			while(rset.next()) {
				RankingSong s = new RankingSong();
				s.setRankNo(rset.getInt("rownum"));
				s.setAlbumName(rset.getString("album_name"));
				s.setFilename(rset.getString("filename"));
				s.setFilepath(rset.getString("filepath"));
				s.setLicensed(rset.getInt("licensed"));
				s.setLikeCount(rset.getInt("like_count"));
				s.setPlayCount(rset.getInt("play_count"));
				s.setSongArtist(rset.getString("song_artist"));
				s.setSongGenre(rset.getString("song_genre"));
				s.setSongNo(rset.getInt("song_no"));
				s.setSongTitle(rset.getString("song_title"));
				list.add(s);
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

	public ArrayList<RankingSong> getRankBySong(Connection conn, int start, int end) {
		ArrayList<RankingSong> list = new ArrayList<RankingSong>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum, n.* from(select * from song join album on (song.album_no = album.album_no) order by like_count desc)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				RankingSong s = new RankingSong();
				s.setRankNo(rset.getInt("rnum"));
				s.setAlbumName(rset.getString("album_name"));
				s.setFilename(rset.getString("filename"));
				s.setFilepath(rset.getString("filepath"));
				s.setLicensed(rset.getInt("licensed"));
				s.setLikeCount(rset.getInt("like_count"));
				s.setPlayCount(rset.getInt("play_count"));
				s.setSongArtist(rset.getString("song_artist"));
				s.setSongGenre(rset.getString("song_genre"));
				s.setSongNo(rset.getInt("song_no"));
				s.setSongTitle(rset.getString("song_title"));
				list.add(s);
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

	public ArrayList<RankingSong> getRankByPlay(Connection conn, int start, int end) {
		ArrayList<RankingSong> list = new ArrayList<RankingSong>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum,a.* from (select n.*,album.album_name from (select * from song order by play_count desc)n join album on (n.album_no = album.album_no) order by play_count desc)a) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				RankingSong s = new RankingSong();
				s.setRankNo(rset.getInt("rnum"));
				s.setAlbumName(rset.getString("album_name"));
				s.setFilename(rset.getString("filename"));
				s.setFilepath(rset.getString("filepath"));
				s.setLicensed(rset.getInt("licensed"));
				s.setLikeCount(rset.getInt("like_count"));
				s.setPlayCount(rset.getInt("play_count"));
				s.setSongArtist(rset.getString("song_artist"));
				s.setSongGenre(rset.getString("song_genre"));
				s.setSongNo(rset.getInt("song_no"));
				s.setSongTitle(rset.getString("song_title"));
				list.add(s);
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

	public ArrayList<RankingSong> getRankByArtist(Connection conn, int start, int end) {
		ArrayList<RankingSong> list = new ArrayList<RankingSong>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM(SELECT ROWNUM AS RNUM,A.* FROM (SELECT SONG_ARTIST,SUM(LIKE_COUNT)LIKE_COUNT,SUM(PLAY_COUNT)play_count FROM SONG GROUP BY SONG_ARTIST ORDER BY LIKE_COUNT DESC)A) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				RankingSong s = new RankingSong();
				s.setRankNo(rset.getInt("rnum"));
				s.setLikeCount(rset.getInt("like_count"));
				s.setPlayCount(rset.getInt("play_count"));
				s.setSongArtist(rset.getString("song_artist"));
				list.add(s);
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

	 public ArrayList<RankingSong> getRankByAlbum(Connection conn, int start, int end) {
		 ArrayList<RankingSong> list = new ArrayList<RankingSong>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, B.* FROM (SELECT ALBUM_NAME,A.* FROM ALBUM JOIN (SELECT ALBUM_NO,SUM(LIKE_COUNT)LIKE_COUNT,SONG_ARTIST,SUM(PLAY_COUNT)play_Count FROM SONG GROUP BY ALBUM_NO,SONG_ARTIST)A ON (ALBUM.ALBUM_NO = A.ALBUM_NO) ORDER BY A.LIKE_COUNT DESC)B) WHERE RNUM BETWEEN ? AND ?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					RankingSong s = new RankingSong();
					s.setRankNo(rset.getInt("rnum"));
					s.setAlbumName(rset.getString("album_name"));
					s.setLikeCount(rset.getInt("like_count"));
					s.setPlayCount(rset.getInt("play_count"));
					s.setSongArtist(rset.getString("song_artist"));
					list.add(s);
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
