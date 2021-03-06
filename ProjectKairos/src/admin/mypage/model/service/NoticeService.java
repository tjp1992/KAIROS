package admin.mypage.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import admin.mypage.model.dao.NoticeDao;
import admin.mypage.model.vo.Notice;
import admin.mypage.model.vo.NoticePageData;
import common.JDBCTemplate;

public class NoticeService {

	public NoticePageData selectList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10; // 한 페이지당 게시물 수
		// 총 게시물 수를 구해오는 dao 호출
		int totalCount = new NoticeDao().totalCount(conn);
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
		ArrayList<Notice> list = new NoticeDao().selectList(conn, start, end);
		// 페이지 네비게이션 작성 시작
				String pageNavi = "";
				// 페이지 네비게이션 길이
				int pageNaviSize = 5;
				int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

				if (pageNo != 1) {
					pageNavi += "<li><a href='/adminNotice?reqPage=" + (pageNo - pageNaviSize) + "'><span>«</span></a></li>";
				}
				for (int i = 0; i < pageNaviSize; i++) {
					if (reqPage == pageNo) {
						pageNavi += "<li class='active'><a href='#'><span>"+ pageNo  +"<span class='sr-only'>(current)</span></span></a></li>";
					} else {
						pageNavi += "<li><a href='/adminNotice?reqPage=" + pageNo + "'>" + pageNo + "</a></li>";
					}
					pageNo++;
					if (pageNo > totalPage) {
						break;
					}
				}
				if (pageNo <= totalPage) {
					pageNavi += "<li><a aria-label='Next' href='/adminNotice?reqPage=" + pageNo + "'><span>»</span></a></li>";
				}
				
		NoticePageData pd = new NoticePageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return pd;
	}

	public Notice noticeDetail(int noticeNum) {
		Connection conn = JDBCTemplate.getConnection();
		Notice n = new NoticeDao().noticeDetail(conn,noticeNum);
		JDBCTemplate.close(conn);
		
		return n;
	}

	public int deleteNotice(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().deleteNotice(conn, noticeNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertNotice(Notice n) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().insertNotice(conn,n);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;

	}

	public Notice updateNotice(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		Notice n = new NoticeDao().insertNotice(conn,noticeNo);
		JDBCTemplate.close(conn);
		return n;
	}

	public int modifyNotice(int noticeNo, String title, String content) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().modifyNotice(conn,noticeNo,title,content);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
		
	}

}
