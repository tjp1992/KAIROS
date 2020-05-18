package manageMusic.controller;

import manageMusic.model.vo.Album;
import search.model.service.SearchSongService;
import search.model.vo.ReqMyList;
import search.model.vo.ReqSearch;

public class TestRun {

	public static void main(String[] args) {
		
		ReqSearch req = new ReqSearch();
		req.setKeyword("아이유");
		req.setCategory("song_artist");
		req.setGenre("kpop");
		req.setLicensed(1);
		req.setReSearch("밤");
		new SearchSongService().searchSong(req);

	}

}
