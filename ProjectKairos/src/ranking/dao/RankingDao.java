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
		String query = "select rownum, n.* from(select * from song join album on (song.album_no = album.album_no) order by like_count desc)n";
		return null;
	}

}
