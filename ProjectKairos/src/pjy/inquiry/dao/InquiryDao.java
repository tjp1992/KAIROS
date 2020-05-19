package pjy.inquiry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.mypage.model.vo.InquiryAnswer;
import common.JDBCTemplate;
import pjy.inquiry.vo.Inquiry;

public class InquiryDao {

	public int insertInquiry(Connection conn, Inquiry i) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into inquiry values(seq_inq_no.nextval,?,?,?,sysdate,0,null,null)";
		try {
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, i.getUserId());
			pstmt.setString(index++, i.getInqTitle());
			pstmt.setString(index++, i.getInqContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int totalCount(Connection conn, String myId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*)as cnt from inquiry where user_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, myId);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Inquiry> selectMyList(Connection conn, int start, int end, String myId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Inquiry> list = new ArrayList<Inquiry>();
		String query = "select*from(select rownum as rnum, n.* from (select*from inquiry where user_id=? order by inq_no desc)n)where rnum between ? and ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, myId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset=  pstmt.executeQuery();
			while(rset.next()) {
				Inquiry i = new Inquiry();
				i.setInqNo(rset.getInt("inq_no"));
				i.setUserId(rset.getString("user_id"));
				i.setInqTitle(rset.getString("inq_title"));
				i.setInqContent(rset.getString("inq_content"));
				i.setInqDate(rset.getDate("inq_date"));
				i.setInqAnsNo(rset.getInt("inq_ans_no"));
				i.setInqFilename(rset.getString("inq_filename"));
				i.setInqFilepath(rset.getString("inq_filepath"));
				list.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public Inquiry selectOneInquiry(Connection conn, int inqNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Inquiry i = null;
		String query = "select * from inquiry where inq_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, inqNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				i = new Inquiry();
				i.setInqNo(rset.getInt("inq_no"));
				i.setUserId(rset.getString("user_id"));
				i.setInqTitle(rset.getString("inq_title"));
				i.setInqContent(rset.getString("inq_content"));
				i.setInqDate(rset.getDate("inq_date"));
				i.setInqAnsNo(rset.getInt("inq_ans_no"));
				i.setInqFilename(rset.getString("inq_filename"));
				i.setInqFilepath(rset.getString("inq_filepath"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return i;
	}

	public InquiryAnswer inquiryAnswer(Connection conn, int inqNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		InquiryAnswer ia = new InquiryAnswer();
		String query = "select * from inq_ans where inq_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, inqNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				ia.setInqAnsNo(rset.getInt("inq_ans_no"));
				ia.setInqNo(rset.getInt("inq_no"));
				ia.setInqAnsTitle(rset.getString("inq_ans_title"));
				ia.setInqAnsContent(rset.getString("inq_ans_content"));
				ia.setInqAnsDate(rset.getDate("inq_ans_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return ia;
	}

	public int modifyInquiryView(Connection conn, Inquiry inq) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update inquiry set inq_title=?,inq_content=? where inq_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inq.getInqTitle());
			pstmt.setString(2, inq.getInqContent());
			pstmt.setInt(3, inq.getInqNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int inquiryDelete(Connection conn, int inqNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from inquiry where inq_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, inqNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

}
