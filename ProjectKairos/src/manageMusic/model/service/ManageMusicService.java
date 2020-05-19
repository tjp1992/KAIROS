package manageMusic.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import manageMusic.controller.FileControl;
import manageMusic.model.dao.ManageMusicDao;
import manageMusic.model.vo.Album;
import manageMusic.model.vo.AlbumDesc;
import manageMusic.model.vo.LicensedArtist;
import song.vo.SearchSong;
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

		if (result > 0) {
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

		if (result > 0 && upload.uploadAlbumImg(root, a)) {
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
		
		String filepath = new ManageMusicDao().readSongPath(conn, songNo);

		int result = new ManageMusicDao().deleteSong(conn, songNo);
		
		
		if (result > 0 && new FileControl().deleteMusic(root, filepath)) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
			result = 0;
		}

		return result;
	}

	public int deleteAlbum(String root, int albumNo) {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<AlbumDesc> list = new ManageMusicDao().readAlbumDesc(conn, albumNo);

		int result1 = 0;
		int uNum = 0;
		
		if(list.size() !=0 && list.get(0).getSongNo() != 0) {
			for(AlbumDesc a : list) {
				result1 = new ManageMusicDao().deleteSong(conn, a.getSongNo());
				if(result1>0) {
					uNum++;
				}
			}
		} else if(list.get(0).getSongNo() == 0) {
			uNum = list.size();
		}		
		
		int result2 = new ManageMusicDao().deleteAlbum(conn, albumNo);
		
		if (uNum == list.size() && result2 > 0) {			
			FileControl control = new FileControl();
			if (list.size() != 0 && list.get(0).getSongNo() != 0) {
				System.out.println("음원 삭제 로직 진행");
				for (AlbumDesc a : list) {
					if (!control.deleteMusic(root, a.getFilepath())) {
						JDBCTemplate.rollback(conn);
						JDBCTemplate.close(conn);
						System.out.println(a.getSongNo() + ".mp3 삭제 에러");
						return 0;
					}
					System.out.println(a.getSongNo()+".mp3 삭제 완료");
				}
			}
			
			if (list.get(0).getAlbumPath() != null) {
				System.out.println("앨범 이미지 삭제 로직 진행");
				if (!control.deleteAlbumImg(root, albumNo)) {
					JDBCTemplate.rollback(conn);
					JDBCTemplate.close(conn);
					System.out.println(albumNo + ". 앨범 이미지 삭제 에러");
					return 0;
				}
				System.out.println(albumNo+".jpg 삭제 완료");
			}

			JDBCTemplate.commit(conn);

		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result2;
	}

	public SearchSong readOneSong(int songNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		SearchSong s = new ManageMusicDao().readOneSong(conn, songNo);
				
		JDBCTemplate.close(conn);
		
		return s;
	}

	public int modifySong(String root, Song s) {

		Connection conn = JDBCTemplate.getConnection();

		int result = new ManageMusicDao().modifySong(conn, s);

		if (result > 0 && new FileControl().uploadMusic(root, s)) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);
		return result;
	}

	public int modifySong(Song s) {
		
		Connection conn = JDBCTemplate.getConnection();

		int result = new ManageMusicDao().modifySong(conn, s);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);
		return result;
	}

	public static int updateLcnArtist(LicensedArtist l) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ManageMusicDao().readOneLcnArtist(conn, l);
		
		if(result>0) {
			result = new ManageMusicDao().updateLcnArtist(conn,l);
		} else {
			result = new ManageMusicDao().insertLcnArtist(conn,l);
		}		
				
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
				
		JDBCTemplate.close(conn);
				
		return result;
	}
}
