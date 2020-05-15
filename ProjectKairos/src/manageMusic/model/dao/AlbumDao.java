package manageMusic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import manageMusic.model.vo.Album;

public class AlbumDao {

	public int insertAlbum(Connection conn, String albumOwner, String albumName) {
		
		int result = 0;
		
		PreparedStatement pst = null;
		String query = "insert into album values(seq_album_no.nextval, ?, ?, ?)";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, albumOwner);
			pst.setString(2, albumName);			
			pst.setString(3, null);
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pst);
		}
		
		
		return result;
	}

	public Album selectOneAlbum(Connection conn, String albumOwner, String albumName) {
		
		Album a = null;
		
		PreparedStatement pst = null;
		ResultSet rset = null;
		
		String query = "select * from album where album_owner = ? and album_name = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, albumOwner);
			pst.setString(2, albumName);
			
			rset = pst.executeQuery();
			
			if(rset.next()) {
				a = new Album();				
				a.setAlbumNo(rset.getInt("album_no"));
				a.setAlbumOwner(rset.getString("album_owner"));
				a.setAlbumName(rset.getString("album_name"));
				a.setAlbumPath(rset.getString("album_path"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}
		
		
		
		return a;
	}

}
