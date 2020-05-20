package search.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import search.model.dao.SearchSongDao;
import search.model.vo.MyListPageData;
import search.model.vo.ReqMyList;
import search.model.vo.ReqSearch;
import search.model.vo.SearchPageData;
import search.model.vo.SearchSong;

public class SearchSongService {

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
		if (req.getReSearch() != null) {
			totalCount = new SearchSongDao().getTotalCountReSearchMyList(conn, req.getUserNick(), req.getReSearch());
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

		ArrayList<SearchSong> list = null;

		if (req.getReSearch() != null) {
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
			pageNavi.append(
					"<button type='button' value='1' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-double-left'></i></button>");
			pageNavi.append("<button type='button' value='" + (pageNo - pageNaviSize)
					+ "' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-left'></i></button>");
		}

		for (int i = 0; i < pageNaviSize; i++) {

			if (reqPage == pageNo) {
				pageNavi.append("<button type='button' value='" + pageNo + "' class='btn btn-sm btn-dark page_btn'>"
						+ pageNo + "</button>");

			} else {
				pageNavi.append("<button type='button' value='" + pageNo
						+ "' class='btn btn-sm btn-outline-dark page_btn'>" + pageNo + "</button>");
			}

			pageNo++;

			if (pageNo > totalPage) {
				break;
			}
		}

		if (pageNo <= totalPage) {
			pageNavi.append("<button type='button' value='" + pageNo
					+ "' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-right'></i></button>");
			pageNavi.append("<button type='button' value='" + totalPage
					+ "' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-double-right'></i></button>");
		}

		return new MyListPageData(pageNavi.toString(), list);
	}

	public SearchPageData searchSong(ReqSearch req) {

		Connection conn = JDBCTemplate.getConnection();
		StringBuffer sb = new StringBuffer();
		String countQuery = null;
		String query = null;

		sb.append("SELECT * FROM (SELECT ROWNUM AS RNUM, T.*");

		if (req.getUserId() != null) {
			sb.append(", NVL((SELECT LIKED_SONG_NO FROM LIKELIST WHERE LIKED_SONG_NO = SONG_NO AND LIKELIST.USER_ID = '"
					+ req.getUserId() + "'),0) AS LIKED ");
		}

		sb.append(" FROM(SELECT * FROM SONG S JOIN ALBUM A USING(ALBUM_NO) WHERE ");

		if (req.getCategory() != null) {
			sb.append(req.getCategory() + " LIKE ");
		} else {
			sb.append("SONG_TITLE LIKE '%" + req.getKeyword() + "%' OR SONG_ARTIST LIKE '%" + req.getKeyword()
					+ "%' OR ALBUM_NAME LIKE ");
		}

		sb.append("'%" + req.getKeyword() + "%' ORDER BY S.LIKE_COUNT DESC)T ORDER BY ROWNUM)");

		if (req.getGenre() != null && req.getLicensed() != 0) {
			sb.append(" WHERE SONG_GENRE = '" + req.getGenre() + "' AND LICENSED = " + req.getLicensed());
			countQuery = "SELECT COUNT(*) AS CNT FROM (" + sb.toString() + ")";
		} else if (req.getLicensed() != 0 && req.getLicensed() != 3) {
			if (req.getLicensed() == 2) {
				req.setLicensed(0);
			}
			sb.append(" WHERE LICENSED = " + req.getLicensed());
			countQuery = "SELECT COUNT(*) AS CNT FROM (" + sb.toString() + ")";

			if (req.getLicensed() == 0) {
				req.setLicensed(2);
			}
		} else if (req.getGenre() != null) {
			sb.append(" WHERE SONG_GENRE = '" + req.getGenre() + "'");
			countQuery = "SELECT COUNT(*) AS CNT FROM (" + sb.toString() + ")";
		} else {
			countQuery = "SELECT COUNT(*) AS CNT FROM (" + sb.toString() + ")";
		}

		if (req.getGenre() == null && (req.getLicensed() == 0 || req.getLicensed() == 3)) {
			sb.append(" WHERE RNUM BETWEEN ? AND ?");
			query = sb.toString();
		} else {
			sb.append(" AND RNUM BETWEEN ? AND ?");
			query = sb.toString();
		}

		int totalCount = new SearchSongDao().getTotalCount(conn, countQuery);

		int reqPage = req.getReqPage();
		int numPerPage = req.getNumPerPage();

		int totalPage = 0;

		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}

		int start = (reqPage - 1) * numPerPage + 1;
		int end = reqPage * numPerPage;

		
		ArrayList<SearchSong> list = new SearchSongDao().searchSong(conn, query, start, end);

		JDBCTemplate.close(conn);
		
		// 페이지 네비게이션 작성
		StringBuffer pageNavi = new StringBuffer();
		// 페이지 네비게이션 길이
		int pageNaviSize = 10;

		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		if (pageNo != 1) {
			pageNavi.append(
					"<button type='button' value='1' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-double-left'></i></button>");
			pageNavi.append("<button type='button' value='" + (pageNo - pageNaviSize)
					+ "' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-left'></i></button>");
		}

		for (int i = 0; i < pageNaviSize; i++) {

			if (reqPage == pageNo) {
				pageNavi.append("<button type='button' value='" + pageNo + "' class='btn btn-sm btn-dark page_btn'>"
						+ pageNo + "</button>");

			} else {
				pageNavi.append("<button type='button' value='" + pageNo
						+ "' class='btn btn-sm btn-outline-dark page_btn'>" + pageNo + "</button>");
			}

			pageNo++;

			if (pageNo > totalPage) {
				break;
			}
		}

		if (pageNo <= totalPage) {
			pageNavi.append("<button type='button' value='" + pageNo
					+ "' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-right'></i></button>");
			pageNavi.append("<button type='button' value='" + totalPage
					+ "' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-double-right'></i></button>");
		}

		SearchPageData pd = new SearchPageData();
		
		pd.setList(list);
		pd.setPageNavi(pageNavi.toString());
		pd.setTotalCount(totalCount);

		return pd;
	}
	
	public SearchPageData reSearchSong(ReqSearch req) {

		Connection conn = JDBCTemplate.getConnection();
		StringBuffer sb = new StringBuffer();
		String countQuery = null;
		String query = null;

		sb.append("SELECT * FROM (SELECT ROWNUM AS RNUM, T.*");

		if (req.getUserId() != null) {
			sb.append(", NVL((SELECT LIKED_SONG_NO FROM LIKELIST WHERE LIKED_SONG_NO = SONG_NO AND LIKELIST.USER_ID = '"
					+ req.getUserId() + "'),0) AS LIKED ");
		}

		sb.append(" FROM(SELECT * FROM SONG S JOIN ALBUM A USING(ALBUM_NO) WHERE ");

		if (req.getCategory() != null) {
			sb.append(req.getCategory() + " LIKE ");
		} else {
			sb.append("SONG_TITLE LIKE '%" + req.getKeyword() + "%' OR SONG_ARTIST LIKE '%" + req.getKeyword()
					+ "%' OR ALBUM_NAME LIKE ");
		}

		sb.append("'%" + req.getKeyword() + "%' ORDER BY S.LIKE_COUNT DESC) T WHERE SONG_TITLE LIKE '%"+req.getReSearch()+"%' OR SONG_ARTIST LIKE '%"+req.getReSearch()+"%' OR ALBUM_NAME LIKE '%"+req.getReSearch()+"%' ORDER BY ROWNUM)");

		if (req.getGenre() != null && req.getLicensed() != 0) {
			sb.append(" WHERE SONG_GENRE = '" + req.getGenre() + "' AND LICENSED = " + req.getLicensed());
			countQuery = "SELECT COUNT(*) AS CNT FROM (" + sb.toString() + ")";
		} else if (req.getLicensed() != 0 && req.getLicensed() != 3) {
			if (req.getLicensed() == 2) {
				req.setLicensed(0);
			}
			sb.append(" WHERE LICENSED = " + req.getLicensed());
			countQuery = "SELECT COUNT(*) AS CNT FROM (" + sb.toString() + ")";

			if (req.getLicensed() == 0) {
				req.setLicensed(2);
			}
		} else if (req.getGenre() != null) {
			sb.append(" WHERE SONG_GENRE = '" + req.getGenre() + "'");
			countQuery = "SELECT COUNT(*) AS CNT FROM (" + sb.toString() + ")";
		} else {
			countQuery = "SELECT COUNT(*) AS CNT FROM (" + sb.toString() + ")";
		}

		if (req.getGenre() == null && (req.getLicensed() == 0 || req.getLicensed() == 3)) {
			sb.append(" WHERE RNUM BETWEEN ? AND ?");
			query = sb.toString();
		} else {
			sb.append(" AND RNUM BETWEEN ? AND ?");
			query = sb.toString();
		}

		int totalCount = new SearchSongDao().getTotalCount(conn, countQuery);

		int reqPage = req.getReqPage();
		int numPerPage = req.getNumPerPage();

		int totalPage = 0;

		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}

		int start = (reqPage - 1) * numPerPage + 1;
		int end = reqPage * numPerPage;
		
		ArrayList<SearchSong> list = new SearchSongDao().searchSong(conn, query, start, end);

		JDBCTemplate.close(conn);
		
		// 페이지 네비게이션 작성
		StringBuffer pageNavi = new StringBuffer();
		// 페이지 네비게이션 길이
		int pageNaviSize = 10;

		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		if (pageNo != 1) {
			pageNavi.append(
					"<button type='button' value='1' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-double-left'></i></button>");
			pageNavi.append("<button type='button' value='" + (pageNo - pageNaviSize)
					+ "' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-left'></i></button>");
		}

		for (int i = 0; i < pageNaviSize; i++) {

			if (reqPage == pageNo) {
				pageNavi.append("<button type='button' value='" + pageNo + "' class='btn btn-sm btn-dark page_btn'>"
						+ pageNo + "</button>");

			} else {
				pageNavi.append("<button type='button' value='" + pageNo
						+ "' class='btn btn-sm btn-outline-dark page_btn'>" + pageNo + "</button>");
			}

			pageNo++;

			if (pageNo > totalPage) {
				break;
			}
		}

		if (pageNo <= totalPage) {
			pageNavi.append("<button type='button' value='" + pageNo
					+ "' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-right'></i></button>");
			pageNavi.append("<button type='button' value='" + totalPage
					+ "' class='btn btn-sm btn-outline-dark page_btn'><i class='fas fa-angle-double-right'></i></button>");
		}

		SearchPageData pd = new SearchPageData();
		
		pd.setList(list);
		pd.setPageNavi(pageNavi.toString());
		pd.setTotalCount(totalCount);

		return pd;
	}

}
