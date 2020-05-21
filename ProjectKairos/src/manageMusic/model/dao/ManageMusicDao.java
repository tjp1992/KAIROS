package manageMusic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import manageMusic.model.vo.Album;
import manageMusic.model.vo.AlbumDesc;
import manageMusic.model.vo.LicensedArtist;
import oracle.net.aso.a;
import search.model.vo.SearchSong;
import song.vo.Song;

public class ManageMusicDao {
	public int insertSong(Connection conn, Song s) {

		int result = 0;
		PreparedStatement pst = null;
		String query = "insert into song values(seq_song_no.nextval,?,?,?,?,0,0,?,seq_song_no.currval,?)";

		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, s.getSongTitle());
			pst.setString(2, s.getSongArtist());
			pst.setString(3, s.getSongGenre());
			pst.setInt(4, s.getAlbumNo());
			pst.setString(5, s.getFilename());
			pst.setInt(6, s.getLicensed());

			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pst);
		}

		return result;
	}

	public int readSongNo(Connection conn, Song s) {

		PreparedStatement pst = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select song_no from song where album_no = ? and song_title = ?";

		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, s.getAlbumNo());
			pst.setString(2, s.getSongTitle());

			rset = pst.executeQuery();

			if (rset.next()) {
				result = rset.getInt("song_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

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

			if (rset.next()) {
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

	public ArrayList<Album> readAlbums(Connection conn, String albumOwner) {

		ArrayList<Album> list = new ArrayList<Album>();

		PreparedStatement pst = null;
		ResultSet rset = null;
		String query = "select * from album where album_owner = ?";

		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, albumOwner);

			rset = pst.executeQuery();

			while (rset.next()) {
				Album a = new Album();
				a.setAlbumNo(rset.getInt("album_no"));
				a.setAlbumOwner(rset.getString("album_owner"));
				a.setAlbumName(rset.getString("album_name"));
				a.setAlbumPath(rset.getString("album_path"));

				list.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}

		return list;
	}

	public Album readOneAlbum(Connection conn, int albumNo) {

		PreparedStatement pst = null;
		ResultSet rset = null;
		String query = "select * from album where album_no = ?";

		Album a = null;

		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, albumNo);

			rset = pst.executeQuery();

			if (rset.next()) {
				a = new Album();
				a.setAlbumNo(albumNo);
				a.setAlbumName(rset.getString("album_name"));
				a.setAlbumOwner(rset.getString("album_owner"));
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

	public int updateAlbumImg(Connection conn, Album a) {

		PreparedStatement pst = null;
		int result = 0;
		String query = "update album set album_path = ? where album_no = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, String.valueOf(a.getAlbumNo()));
			pst.setInt(2, a.getAlbumNo());

			result = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pst);
		}

		return result;
	}

	public ArrayList<LicensedArtist> searchArtist(Connection conn, String artistName) {

		ArrayList<LicensedArtist> list = new ArrayList<LicensedArtist>();
		PreparedStatement pst = null;
		ResultSet rset = null;
		String query = "select * from licensed_artist where lcn_artist_name like '%" + artistName + "%'";

		try {
			pst = conn.prepareStatement(query);

			rset = pst.executeQuery();

			while (rset.next()) {
				LicensedArtist l = new LicensedArtist();

				l.setLcnArtistName(rset.getString("lcn_artist_name"));
				l.setLcnCompany(rset.getString("lcn_company"));
				l.setLcnAgentPhone(rset.getString("lcn_agent_phone"));
				l.setLcnAgentName(rset.getString("lcn_agent_name"));
				
				list.add(l);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}

		return list;
	}

	public ArrayList<AlbumDesc> readAlbumDesc(Connection conn, int albumNo) {

		ArrayList<AlbumDesc> list = new ArrayList<AlbumDesc>();
		PreparedStatement pst = null;
		ResultSet rset = null;
		String query = "select s.song_no,s.song_title, a.album_path, s.filepath from album a left join song s using(album_no) where album_no = ?";

		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, albumNo);

			rset = pst.executeQuery();

			while (rset.next()) {
				AlbumDesc ad = new AlbumDesc();
				ad.setSongNo(rset.getInt("song_no"));
				ad.setSongTitle(rset.getString("song_title"));
				ad.setAlbumPath(rset.getString("album_path"));
				ad.setFilepath(rset.getString("filepath"));
				list.add(ad);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}

		return list;
	}

	public int updateAlbum(Connection conn, Album a) {

		int result = 0;

		PreparedStatement pst = null;
		String query = "update album set album_name = ? where album_no = ?";

		try {
			pst = conn.prepareStatement(query);

			pst.setString(1, a.getAlbumName());
			pst.setInt(2, a.getAlbumNo());

			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pst);
		}

		return result;
	}

	public int updateAlbumNameAndPath(Connection conn, Album a) {

		int result = 0;

		PreparedStatement pst = null;
		String query = "update album set album_name = ?, album_path = ? where album_no = ?";

		try {
			pst = conn.prepareStatement(query);

			pst.setString(1, a.getAlbumName());
			pst.setString(2, String.valueOf(a.getAlbumNo()));
			pst.setInt(3, a.getAlbumNo());

			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pst);
		}

		return result;
	}

	public int deleteSong(Connection conn, int songNo) {

		int result = 0;
		PreparedStatement pst = null;
		String query = "delete from song where song_no = ?";

		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, songNo);

			result = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pst);
		}

		return result;
	}

	public int deleteAlbum(Connection conn, int albumNo) {

		int result = 0;
		PreparedStatement pst = null;
		String query = "delete from album where album_no = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, albumNo);

			result = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pst);
		}

		return result;
	}

	public String readSongPath(Connection conn, int songNo) {
		
		PreparedStatement pst = null;
		ResultSet rset = null;
		String query = "select filepath from song where song_no = ?";
		String filepath = null;
		
		try {
			pst = conn.prepareStatement(query);
			
			pst.setInt(1, songNo);
			
			rset = pst.executeQuery();
			
			if(rset.next()) {
				filepath = rset.getString("filepath");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}
		
		
		
		return filepath;
	}

	public SearchSong readOneSong(Connection conn, int songNo) {
		
		SearchSong s = null;
		PreparedStatement pst = null;
		ResultSet rset = null;
		String query = "select * from song join album using(album_no) where song_no = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, songNo);
			
			rset = pst.executeQuery();
			
			if(rset.next()) {
				s = new SearchSong();
				
				s.setAlbumPath(rset.getString("album_path"));
				s.setAlbumName(rset.getString("album_name"));
				s.setAlbumNo(rset.getInt("album_no"));
				s.setFilename(rset.getString("filename"));
				s.setFilepath(rset.getString("filepath"));
				s.setLicensed(rset.getInt("licensed"));
				s.setLikeCount(rset.getInt("like_count"));
				s.setPlayCount(rset.getInt("play_count"));
				s.setSongArtist(rset.getString("song_artist"));
				s.setSongGenre(rset.getString("song_genre"));
				s.setSongNo(rset.getInt("song_no"));
				s.setSongTitle(rset.getString("song_title"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}
		
		
		return s;
	}

	public int modifySong(Connection conn, Song s) {
		
		int result = 0;
		PreparedStatement pst = null;
		String query = "update song set song_title = ?, album_no = ?, filename = ? where song_no = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, s.getSongTitle());
			pst.setInt(2, s.getAlbumNo());
			pst.setString(3, s.getFilename());
			pst.setInt(4, s.getSongNo());
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pst);
		}		
		
		return result;
	}

	public int readOneLcnArtist(Connection conn, LicensedArtist l) {

		int result = 0;
		
		PreparedStatement pst = null;
		ResultSet rset = null;
		String query = "select * from licensed_artist where lcn_artist_name = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, l.getLcnArtistName());
			
			rset = pst.executeQuery();
			
			if(rset.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}
		
		
		return result;
	}

	public int updateLcnArtist(Connection conn, LicensedArtist l) {
		
		int result = 0;
		PreparedStatement pst = null;
		String query = "update licensed_artist set lcn_company = ?, lcn_agent_name = ?, lcn_agent_phone = ? where lcn_artist_name = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, l.getLcnCompany());
			pst.setString(2, l.getLcnAgentName());
			pst.setString(3, l.getLcnAgentPhone());
			pst.setString(4, l.getLcnArtistName());
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pst);
		}
		
		return result;
	}

	public int insertLcnArtist(Connection conn, LicensedArtist l) {
		
		int result = 0;
		PreparedStatement pst = null;
		String query = "insert into licensed_artist values(?,?,?,?)";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, l.getLcnArtistName());
			pst.setString(2, l.getLcnCompany());
			pst.setString(3, l.getLcnAgentName());
			pst.setString(4, l.getLcnAgentPhone());
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pst);
		}		
		
		return result;
	}

	public int readPlayCount(Connection conn, int songNo) {
		
		PreparedStatement pst = null;
		
		ResultSet rset = null;
		
		String query = "select play_count from song where song_no = ?";
		int result = 0;
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, songNo);
			
			rset = pst.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("play_count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pst);
		}
		
		
		
		
		
		
		return result;
	}

	public int addPlayCount(Connection conn, int songNo, int count) {

		PreparedStatement pst = null;
		
		int result =0;
		
		String query = "update song set play_count =? where song_no = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, count+1);
			pst.setInt(2, songNo);
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pst);
		}
		
		return result;
	}
	
}
