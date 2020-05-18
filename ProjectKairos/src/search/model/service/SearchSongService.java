package search.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import search.model.dao.SearchSongDao;
import search.model.vo.MyListPageData;
import search.model.vo.ReqMyList;
import search.model.vo.SearchResult;
import song.vo.SearchSong;
import user.vo.User;

public class SearchSongService {

	public SearchResult searchByKeword(String keyword, User u) {

		Connection conn = JDBCTemplate.getConnection();

		int start = 1;
		int end = 50;

		int totalResult = new SearchSongDao().getTotalCount(conn, keyword);
		ArrayList<SearchSong> list = new SearchSongDao().searchByKeword(conn, keyword, start, end);

		JDBCTemplate.close(conn);

		return new SearchResult(list, totalResult);
	}

	public SearchResult searchByKeword(String keyword, String category, User u) {

		Connection conn = JDBCTemplate.getConnection();

		int start = 1;
		int end = 50;

		int totalResult = new SearchSongDao().getTotalCount(conn, keyword);

		if (category != null) {
			switch (Integer.parseInt(category)) {
			case 1:
				category = "song_title";
				break;

			case 2:
				category = "song_artist";
				break;

			case 3:
				category = "album_name";
				break;
			}
		}

		ArrayList<SearchSong> list = new SearchSongDao().searchByKeword(conn, keyword, category, u, start, end);

		JDBCTemplate.close(conn);

		return new SearchResult(list, totalResult);
	}

	public MyListPageData searchMyList(ReqMyList req) {

		Connection conn = JDBCTemplate.getConnection();
		String userNick = req.getUserNick();

		int reqPage = req.getReqPage();

		if (reqPage == 0) {
			reqPage = 1;
		}

		int numPerPage = req.getNumPerPage();

		if (numPerPage == 0) {
			numPerPage = 25;
			req.setNumPerPage(numPerPage);
		}
		
		
		int totalCount = 0;
		if(req.getReSearch() != null) {
			totalCount = new SearchSongDao().getTotalCountReSearchMyList(conn,req.getUserNick(),req.getReSearch());
		} else {
			totalCount = new SearchSongDao().getTotalCountMyList(conn, req.getUserNick());			
		}
		

		int totalPage = 0;

		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}

		int start = (reqPage - 1) * numPerPage + 1;
		int end = reqPage * numPerPage;

		ArrayList<SearchSong> list = new ArrayList<SearchSong>();
		
		if(req.getReSearch() != null) {
			list = new SearchSongDao().searchMyList(conn, userNick, req.getReSearch(), start, end);
		} else {
			list = new SearchSongDao().searchMyList(conn, userNick, start, end);
			
		}
		

		JDBCTemplate.close(conn);

		// 페이지 네비게이션 작성
		StringBuffer pageNavi = new StringBuffer();
		// 페이지 네비게이션 길이
		int pageNaviSize = 10;
		
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		if (pageNo != 1) {
			pageNavi.append("<button type='button' value='1' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-double-left'></i></button>");
			pageNavi.append("<button type='button' value='"+(pageNo - pageNaviSize)+"' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-left'></i></button>");
		}

		for (int i = 0; i < pageNaviSize; i++) {

			if (reqPage == pageNo) {
				pageNavi.append("<button type='button' value='"+pageNo+"' class='btn btn-sm btn-dark page_btn'>"+pageNo+"</button>");

			} else {
				pageNavi.append("<button type='button' value='"+pageNo+"' class='btn btn-sm btn-outline-dark page_btn'>"+pageNo+"</button>");
			}

			pageNo++;

			if (pageNo > totalPage) {
				break;
			}
		}

		if (pageNo <= totalPage) {
			pageNavi.append("<button type='button' value='"+pageNo+"' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-right'></i></button>");
			pageNavi.append("<button type='button' value='"+totalPage+"' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-double-right'></i></button>");
		}

		return new MyListPageData(pageNavi.toString(), list);
	}

}
