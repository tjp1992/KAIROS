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
		String query = "select rownum as rnum, n.* from(select * from song join album on (song.album_no = album.album_no) order by like_count desc)n";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				RankingSong s = new RankingSong();
				list = new ArrayList<RankingSong>();
				s.setRankNo(rset.getInt("rnum"));
				s.setAlbumName(rset.getInt("album_name"));
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

}
