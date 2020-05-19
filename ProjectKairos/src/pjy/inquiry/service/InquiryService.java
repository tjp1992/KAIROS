package pjy.inquiry.service;

import java.sql.Connection;
import java.util.ArrayList;

import admin.mypage.model.vo.InquiryAnswer;
import common.JDBCTemplate;
import pjy.inquiry.dao.InquiryDao;
import pjy.inquiry.vo.Inquiry;
import pjy.inquiry.vo.InquiryPageData;

public class InquiryService {

	public int insertInquiry(Inquiry i) {
		Connection conn = JDBCTemplate.getConnection();
		System.out.println(i.getUserId());
		int result = new InquiryDao().insertInquiry(conn, i);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public InquiryPageData selectMyList(int reqPage, String myId) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int totalCount = new InquiryDao().totalCount(conn,myId);
		int totalPage = 0;
		if(totalCount % numPerPage ==0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<Inquiry> list = new InquiryDao().selectMyList(conn,start,end,myId);
		String pageNavi ="";
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		if (pageNo != 1) {
			pageNavi += "<a class='btn' href='/inquiryList?reqPage=" + (pageNo - pageNaviSize) + "'>이전</a>";
		}
		for (int i = 0; i < pageNaviSize; i++) {
			if (reqPage == pageNo) {
				pageNavi += "<span class='selectPage'>" + pageNo + "</span>";
			} else {
				pageNavi += "<a class='btn' href='/inquiryList?reqPage=" + pageNo + "'>" + pageNo + "</a>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		if (pageNo <= totalPage) {
			pageNavi += "<a class ='btn' href='/inquiryList?reqPage=" + pageNo + "'>다음</a>";
		}
		InquiryPageData pd = new InquiryPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return pd;
	}

	public Inquiry selectOneInquiry(int inqNo) {
		Connection conn = JDBCTemplate.getConnection();
		Inquiry i = new InquiryDao().selectOneInquiry(conn,inqNo);
		//comment 어디서 가져와야하는지 대해서 물어볼것
		
		JDBCTemplate.close(conn);
		return i;
		
	}

	public InquiryAnswer inquiryAnswer(int inqNo) {
		Connection conn =JDBCTemplate.getConnection();
		InquiryAnswer ia = new InquiryDao().inquiryAnswer(conn,inqNo);
		JDBCTemplate.close(conn);
		return ia;
	}

	public int modifyInquiryView(Inquiry inq) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new InquiryDao().modifyInquiryView(conn,inq);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int inquiryDelete(int inqNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new InquiryDao().inquiryDelete(conn,inqNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
