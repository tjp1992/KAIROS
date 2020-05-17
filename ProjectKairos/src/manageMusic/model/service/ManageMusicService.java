package manageMusic.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import manageMusic.controller.FileControl;
import manageMusic.model.dao.ManageMusicDao;
import manageMusic.model.vo.Album;
import manageMusic.model.vo.AlbumDesc;
import manageMusic.model.vo.LicensedArtist;
import song.vo.Song;

public class ManageMusicService {

	public int insertSong(String root, Song s) {

		Connection conn = JDBCTemplate.getConnection();

		
		int result = new ManageMusicDao().insertSong(conn, s);
		s.setSongNo(new ManageMusicDao().readSongNo(conn, s));

		if (result > 0 && new FileControl().uploadMusic(root, s)) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);
		return result;
	}

	public int insertSong(String root, Song s, Album a) {

		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		int sResult = new ManageMusicDao().insertSong(conn, s);
		s.setSongNo(new ManageMusicDao().readSongNo(conn, s));
		int aResult = new ManageMusicDao().updateAlbumImg(conn, a);

		FileControl upload = new FileControl();

		if (aResult > 0 && sResult > 0 && upload.uploadMusic(root, s) && upload.uploadAlbumImg(root, a)) {
			JDBCTemplate.commit(conn);
			result = 1;
		} else {
			JDBCTemplate.rollback(conn);
			result = 0;
		}

		JDBCTemplate.close(conn);
		return result;
	}

	public int insertAlbum(String albumOwner, String albumName) {

		Connection conn = JDBCTemplate.getConnection();

		Album a = new ManageMusicDao().selectOneAlbum(conn, albumOwner, albumName);

		if (a != null) {
			return -1;
		}

		int result = new ManageMusicDao().insertAlbum(conn, albumOwner, albumName);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;
	}

	public ArrayList<Album> readAlbums(String albumOwner) {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Album> list = new ManageMusicDao().readAlbums(conn, albumOwner);

		JDBCTemplate.close(conn);

		return list;
	}

	public Album readOneAlbum(int albumNo) {

		Connection conn = JDBCTemplate.getConnection();

		Album a = new ManageMusicDao().readOneAlbum(conn, albumNo);

		JDBCTemplate.close(conn);

		return a;
	}

	public ArrayList<LicensedArtist> searchArtist(String artistName) {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<LicensedArtist> list = new ManageMusicDao().searchArtist(conn, artistName);

		JDBCTemplate.close(conn);

		return list;
	}

	public ArrayList<AlbumDesc> readAlbumDesc(int albumNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<AlbumDesc> list = new ManageMusicDao().readAlbumDesc(conn, albumNo);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public int updateAlbum(Album a) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ManageMusicDao().updateAlbum(conn, a); 
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
			
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int updateAlbum(String root, Album a) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ManageMusicDao().updateAlbumNameAndPath(conn, a); 
		
		FileControl upload = new FileControl();
		
		if(result > 0 && upload.uploadAlbumImg(root, a)) {
			JDBCTemplate.commit(conn);
			result = 1;
		} else {
			JDBCTemplate.rollback(conn);
			result = 0;
		}
			
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int deleteSong(String root, int songNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ManageMusicDao().deleteSong(conn, songNo); 
				
		if(result > 0 && new FileControl().deleteMusic(root, songNo)) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
			result = 0;
		}
		
		return result;
	}
}
