package manageMusic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.JDBCTemplate;
import song.vo.Song;

public class SongDao {

	public int insertSong(Connection conn, Song s) {
		
		int result = 0;
		PreparedStatement pst = null;
		String query = "insert into song values(seq_song_no.nextval,?,?,?,?,0,0,?,?,?)";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, s.getSongTitle());
			pst.setString(2, s.getSongArtist());
			pst.setString(3, s.getSongGenre());
			pst.setInt(4, s.getAlbumNo());
			pst.setString(5, s.getFilename());
			pst.setString(6, s.getFilepath());
			pst.setInt(7, s.getLicensed());
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pst);
		}
		
		return result;
	}

}
