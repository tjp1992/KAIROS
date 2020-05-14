package search.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import search.model.dao.SearchSongDao;
import search.model.vo.SearchResult;
import song.vo.SearchSong;

public class SearchSongService {

	public SearchResult searchByKeword(String keyword) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int start = 1;
		int end = 50;
		
		int totalResult = new SearchSongDao().getTotalCount(conn,keyword);
		ArrayList<SearchSong> list = new SearchSongDao().searchByKeword(conn, keyword, start, end);
			
		
		JDBCTemplate.close(conn);
		
		return new SearchResult(list, totalResult);
	}

}
