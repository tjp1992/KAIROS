package admin.mypage.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import admin.mypage.model.dao.NoticeDao;
import admin.mypage.model.dao.QuestionDao;
import admin.mypage.model.vo.Inquiry;
import admin.mypage.model.vo.Notice;
import admin.mypage.model.vo.NoticePageData;
import admin.mypage.model.vo.QuestionPageData;
import common.JDBCTemplate;

public class QuestionService {

	public QuestionPageData selectList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10; // 한 페이지당 게시물 수
		// 총 게시물 수를 구해오는 dao 호출
		int totalCount = new QuestionDao().totalCount(conn);
		// 총 페이지 수를 연산
		int totalPage = 0;
		if(totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		}else {
			totalPage = totalCount / numPerPage + 1;
		}
		// 조회해 올 게시물 시작번호와 끝번호연산
		int start = (reqPage - 1) * numPerPage +1;
		int end = reqPage * numPerPage;
		// 해당페이지의 게시물 조회
		ArrayList<Inquiry> list = new QuestionDao().selectList(conn, start, end);
		// 페이지 네비게이션 작성 시작
				String pageNavi = "";
				// 페이지 네비게이션 길이
				int pageNaviSize = 5;
				int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

				if (pageNo != 1) {
					pageNavi += "<li><a href='/adminQuestion?reqPage=" + (pageNo - pageNaviSize) + "&check=1'><span>«</span></a></li>";
				}
				for (int i = 0; i < pageNaviSize; i++) {
					if (reqPage == pageNo) {
						pageNavi += "<li class='active'><a href='#'><span>"+ pageNo  +"<span class='sr-only'>(current)</span></span></a></li>";
					} else {
						pageNavi += "<li><a href='/adminQuestion?reqPage=" + pageNo + "&check=1'>" + pageNo + "</a></li>";
					}
					pageNo++;
					if (pageNo > totalPage) {
						break;
					}
				}
				if (pageNo <= totalPage) {
					pageNavi += "<li><a aria-label='Next' href='/adminQuestion?reqPage=" + pageNo + "&check=1'><span>»</span></a></li>";
				}
				
		QuestionPageData qd = new QuestionPageData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		
		return qd;
	}

	public QuestionPageData selectList2(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10; // 한 페이지당 게시물 수
		// 총 게시물 수를 구해오는 dao 호출
		int totalCount = new QuestionDao().totalCount2(conn);
		// 총 페이지 수를 연산
		int totalPage = 0;
		if(totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		}else {
			totalPage = totalCount / numPerPage + 1;
		}
		// 조회해 올 게시물 시작번호와 끝번호연산
		int start = (reqPage - 1) * numPerPage +1;
		int end = reqPage * numPerPage;
		// 해당페이지의 게시물 조회
		ArrayList<Inquiry> list = new QuestionDao().selectList2(conn, start, end);
		// 페이지 네비게이션 작성 시작
				String pageNavi = "";
				// 페이지 네비게이션 길이
				int pageNaviSize = 5;
				int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
				
				if (pageNo != 1) {
					pageNavi += "<li><a href='/adminQuestion?reqPage=" + (pageNo - pageNaviSize) + "&check=2'><span>«</span></a></li>";
				}
				for (int i = 0; i < pageNaviSize; i++) {
					if (reqPage == pageNo) {
						pageNavi += "<li class='active'><a href='#'><span>"+ pageNo  +"<span class='sr-only'>(current)</span></span></a></li>";
					} else {
						pageNavi += "<li><a href='/adminQuestion?reqPage=" + pageNo + "&check=2'>" + pageNo + "</a></li>";
					}
					pageNo++;
					if (pageNo > totalPage) {
						break;
					}
				}
				if (pageNo <= totalPage) {
					pageNavi += "<li><a aria-label='Next' href='/adminQuestion?reqPage=" + pageNo + "&check=2'><span>»</span></a></li>";
				}
				
		QuestionPageData qd = new QuestionPageData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		
		return qd;
	}

}
