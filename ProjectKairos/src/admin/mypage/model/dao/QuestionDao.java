package admin.mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.mypage.model.vo.Inquiry;
import admin.mypage.model.vo.Notice;
import common.JDBCTemplate;

public class QuestionDao {

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from inquiry where inq_ans_no=1";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;

	}

	public ArrayList<Inquiry> selectList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Inquiry> list = new ArrayList<Inquiry>();
		String query = "select * from " + 
				"(SELECT ROWNUM AS rnum, n.* from " + 
				"(SELECT * FROM inquiry where inq_ans_no=1 order by inq_no desc)n) " + 
				"where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Inquiry iq = new Inquiry();
				iq.setInqAnsNo(rset.getInt("inq_no"));
				iq.setInqContent(rset.getString("inq_content"));
				iq.setInqDate(rset.getDate("inq_date"));
				iq.setInqFileName(rset.getString("inq_filename"));
				iq.setInqFilePath(rset.getString("inq_filepath"));
				iq.setInqNo(rset.getInt("inq_ans_no"));
				iq.setInqTitle(rset.getString("inq_title"));
				iq.setUserId(rset.getString("user_id"));
				list.add(iq);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
		
	}

	public int totalCount2(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from inquiry where inq_ans_no=0";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}

	public ArrayList<Inquiry> selectList2(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Inquiry> list = new ArrayList<Inquiry>();
		String query = "select * from " + 
				"(SELECT ROWNUM AS rnum, n.* from " + 
				"(SELECT * FROM inquiry where inq_ans_no=0 order by inq_no desc)n) " + 
				"where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Inquiry iq = new Inquiry();
				iq.setInqAnsNo(rset.getInt("inq_no"));
				iq.setInqContent(rset.getString("inq_content"));
				iq.setInqDate(rset.getDate("inq_date"));
				iq.setInqFileName(rset.getString("inq_filename"));
				iq.setInqFilePath(rset.getString("inq_filepath"));
				iq.setInqNo(rset.getInt("inq_ans_no"));
				iq.setInqTitle(rset.getString("inq_title"));
				iq.setUserId(rset.getString("user_id"));
				list.add(iq);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

}
