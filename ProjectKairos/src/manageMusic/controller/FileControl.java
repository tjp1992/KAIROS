package manageMusic.controller;

import java.io.File;

import manageMusic.model.vo.Album;
import song.vo.Song;

public class FileControl {

	public Boolean uploadAlbumImg(String root, Album a) {

		String tempDirectory = root + "src/tempUpload/";
		String saveDirectory = root + "src/imgs/albumImg/";

		File orgFile = new File(tempDirectory + a.getAlbumPath());
		File newFile = new File(saveDirectory + a.getAlbumNo() + ".jpg");

		if (orgFile.exists()) {

			if (newFile.exists()) {
				newFile.delete();
			}

			orgFile.renameTo(newFile);

			return true;
		} else {
			System.out.println("사용자가 업로드한 tempImg를 찾을수 없습니다.");
			return false;

		}
	}

	public Boolean uploadMusic(String root, Song s) {
		
		String tempDirectory = root + "src/tempUpload/";
		String saveDirectory = root + "src/songs/";
		
		File orgFile = new File(tempDirectory + s.getFilepath());
		File newFile = new File(saveDirectory + s.getSongNo() + ".mp3");
		
		if (orgFile.exists()) {

			if (newFile.exists()) {
				newFile.delete();
			}

			orgFile.renameTo(newFile);

			return true;
		} else {
			System.out.println("사용자가 업로드한 tempMp3를 찾을수 없습니다.");
			return false;

		}
	}
	
	public Boolean deleteMusic(String root, int songNo) {
		String directory = root+"src/songs/";
		
		File music = new File(directory+songNo+".mp3");
		
		if(music.exists()) {
			return music.delete();
		} else {
			System.out.println(songNo+".mp3를 찾을수 없습니다.");
			return false;
		}
		
	}
}
