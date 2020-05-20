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
			orgFile.delete();
			
			return false;
		}
	}
	
	public Boolean deleteMusic(String root, String filepath) {
		
		
		int index = Integer.parseInt(filepath);
		
		if(index < 21) {
			System.out.println("default곡은 삭제를 진행하지 않습니다.");
			return true;
		}
		
		String directory = root+"src/songs/";
		
		File music = new File(directory+filepath+".mp3");
		
		if(music.exists()) {
			return music.delete();
		} else {
			System.out.println(filepath+".mp3를 찾을수 없습니다.");
			return false;
		}
		
	}

	public boolean deleteAlbumImg(String root, int albumNo) {
		
		String directory = root + "src/imgs/albumImg/";
		
		File albumImg = new File(directory+albumNo+".jpg");
		
		if(albumImg.exists()) {
			return albumImg.delete();
		} else {
			System.out.println(albumNo+".jpg를 찾지 못하였습니다.");
			return false;
		}
	}

}
