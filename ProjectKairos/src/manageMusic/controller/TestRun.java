package manageMusic.controller;

import manageMusic.model.vo.Album;

public class TestRun {

	public static void main(String[] args) {
		Album a = new Album();
		a.setAlbumPath("1541550154_m.jpg");
		a.setAlbumName("아티스트-앨범명");
		a.setAlbumNo(0);
		String root = "WebContent/";		
				
//		new FileControl().uploadAlbumImg(root, a);

	}

}
