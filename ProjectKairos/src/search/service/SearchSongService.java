package search.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import search.dao.SearchSongDao;
import song.vo.SearchSong;

public class SearchSongService {

	public ArrayList<SearchSong> searchByKeword(String keyword) {
		
		Connection conn = JDBCTemplate.getConnection();
				
		ArrayList<SearchSong> list = new SearchSongDao().searchByKeword(conn, keyword);
		
		JDBCTemplate.close(conn);

		return list;
	}

}
