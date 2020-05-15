package likelist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import playlist.vo.Playlist;
import user.vo.User;

public class LikelistDao {



	public int checkLike(Connection conn, String userId, int songNo) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		int result=0;
		String query ="select * from likelist where user_id =? and liked_song_no =?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, songNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteOneLike(Connection conn, String userId, int songNo) {
		PreparedStatement pstmt = null;
		int result =0;
		String query = "delete from likelist where user_id=? and liked_song_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, songNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int addOneLike(Connection conn, String userId, int songNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="insert into likelist values (?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, songNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public void checkLike(Connection conn,User u, ArrayList<Playlist> list) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		int result=0;
		String query ="select * from likelist where user_id =? and liked_song_no =?";
		
		for(Playlist p : list) {
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, u.getUserId());
				pstmt.setInt(2, p.getSongNo());
				rset=pstmt.executeQuery();
				if(rset.next()) {
					p.setLiked(1);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
		}
		
		
	}

	public ArrayList<Playlist> likeListView(Connection conn, User u) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select song_title, song_artist, album_name, song_no, filepath "
				+ "from song join album usig (album_no) where song_no in ";
		return null;
	}
	
	

}
