package admin.mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.mypage.model.vo.Inquiry;
import admin.mypage.model.vo.InquiryAnswer;
import admin.mypage.model.vo.Notice;
import common.JDBCTemplate;

public class QuestionDao {

	public int totalCount(Connection conn) {
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

	public ArrayList<Inquiry> selectList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Inquiry> list = new ArrayList<Inquiry>();
		String query = "select * from " + 
				"(SELECT ROWNUM AS rnum, n.* from " + 
				"(SELECT * FROM inquiry where inq_ans_no=0 order by inq_date desc)n) " + 
				"where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Inquiry iq = new Inquiry();
				iq.setInqAnsNo(rset.getInt("inq_ans_no"));
				iq.setInqContent(rset.getString("inq_content"));
				iq.setInqDate(rset.getDate("inq_date"));
				iq.setInqFileName(rset.getString("inq_filename"));
				iq.setInqFilePath(rset.getString("inq_filepath"));
				iq.setInqNo(rset.getInt("inq_no"));
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
		String query = "SELECT count(*) as cnt FROM inq_ans left join inquiry on inquiry.INQ_NO = inq_ans.INQ_NO where inquiry.inq_ans_no=1";
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
		String query = "select * from (SELECT ROWNUM AS rnum, n.* from "
				+ "(SELECT inq_ans.inq_ans_date, inquiry.* FROM inq_ans left join inquiry on inquiry.INQ_NO = inq_ans.INQ_NO "
				+ "where inquiry.inq_ans_no=1 order by inq_ans.inq_ans_date desc)n) "
				+ "where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Inquiry iq = new Inquiry();
				iq.setInqAnsDate(rset.getDate("inq_ans_date"));
				iq.setInqAnsNo(rset.getInt("inq_ans_no"));
				iq.setInqContent(rset.getString("inq_content"));
				iq.setInqDate(rset.getDate("inq_date"));
				iq.setInqFileName(rset.getString("inq_filename"));
				iq.setInqFilePath(rset.getString("inq_filepath"));
				iq.setInqNo(rset.getInt("inq_no"));
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

	public Inquiry QuestionDetail(Connection conn, int inqNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from inquiry where inq_no=?";
		Inquiry iq = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, inqNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				iq = new Inquiry();
				iq.setInqAnsNo(rset.getInt("inq_ans_no"));
				iq.setInqContent(rset.getString("inq_content"));
				iq.setInqDate(rset.getDate("inq_date"));
				iq.setInqFileName(rset.getString("inq_filename"));
				iq.setInqFilePath(rset.getString("inq_filepath"));
				iq.setInqNo(rset.getInt("inq_no"));
				iq.setInqTitle(rset.getString("inq_title"));
				iq.setUserId(rset.getString("user_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return iq;
	}

	public InquiryAnswer QuestionDetailEnd(Connection conn, int inqNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from inq_ans where inq_no=?";
		InquiryAnswer iqa = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, inqNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				iqa = new InquiryAnswer();
				iqa.setInqAnsContent(rset.getString("inq_ans_content"));
				iqa.setInqAnsDate(rset.getDate("inq_ans_date"));
				iqa.setInqAnsNo(rset.getInt("inq_ans_no"));
				iqa.setInqAnsTitle(rset.getString("inq_ans_title"));
				iqa.setInqNo(rset.getInt("inq_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return iqa;
	}

	public int insertInquiryAnswer(Connection conn, Inquiry iq) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into inq_ans values(SEQ_ANS_NO.NEXTVAL,?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, iq.getInqNo());
			pstmt.setString(2, iq.getInqTitle());
			pstmt.setString(3, iq.getInqContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int changeDefault(Connection conn, Inquiry iq) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update inquiry set inq_ans_no = 1 where inq_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, iq.getInqNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public InquiryAnswer answerContent(Connection conn, int inqAnsNo) {
		PreparedStatement pstmt = null;
		InquiryAnswer iqa = null;
		ResultSet rset = null;
		String query = "select * from INQ_ANS where inq_ans_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, inqAnsNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				iqa = new InquiryAnswer();
				iqa.setInqAnsContent(rset.getString("inq_ans_content"));
				iqa.setInqAnsDate(rset.getDate("inq_ans_date"));
				iqa.setInqAnsNo(rset.getInt("inq_ans_no"));
				iqa.setInqAnsTitle(rset.getString("inq_ans_title"));
				iqa.setInqNo(rset.getInt("inq_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return iqa;
	}

	public int updateAnswer(Connection conn, int inqAnsNo, InquiryAnswer iqa) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update inq_ans set inq_ans_content=?,inq_ans_title=? where inq_ans_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, iqa.getInqAnsContent());
			pstmt.setString(2, iqa.getInqAnsTitle());
			pstmt.setInt(3, inqAnsNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int totalCount3(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from inquiry where inq_ans_no=0 and user_id like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
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

	public ArrayList<Inquiry> selectList3(Connection conn, int start, int end, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Inquiry> list = new ArrayList<Inquiry>();
		String query = "select * from " + 
				"(SELECT ROWNUM AS rnum, n.* from " + 
				"(SELECT * FROM inquiry where inq_ans_no=0 and user_id like ? order by inq_date desc)n) " + 
				"where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Inquiry iq = new Inquiry();
				iq.setInqAnsNo(rset.getInt("inq_ans_no"));
				iq.setInqContent(rset.getString("inq_content"));
				iq.setInqDate(rset.getDate("inq_date"));
				iq.setInqFileName(rset.getString("inq_filename"));
				iq.setInqFilePath(rset.getString("inq_filepath"));
				iq.setInqNo(rset.getInt("inq_no"));
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

	public int totalCount4(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT count(*) as cnt FROM inq_ans left join inquiry on inquiry.INQ_NO = inq_ans.INQ_NO where inquiry.inq_ans_no=1 and user_id like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
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

	public ArrayList<Inquiry> selectList4(Connection conn, int start, int end, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Inquiry> list = new ArrayList<Inquiry>();
		String query = "select * from (SELECT ROWNUM AS rnum, n.* from "
				+ "(SELECT inq_ans.inq_ans_date, inquiry.* FROM inq_ans left join inquiry on inquiry.INQ_NO = inq_ans.INQ_NO "
				+ "where inquiry.inq_ans_no=1 and user_id like ? order by inq_ans.inq_ans_date desc)n) "
				+ "where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Inquiry iq = new Inquiry();
				iq.setInqAnsDate(rset.getDate("inq_ans_date"));
				iq.setInqAnsNo(rset.getInt("inq_ans_no"));
				iq.setInqContent(rset.getString("inq_content"));
				iq.setInqDate(rset.getDate("inq_date"));
				iq.setInqFileName(rset.getString("inq_filename"));
				iq.setInqFilePath(rset.getString("inq_filepath"));
				iq.setInqNo(rset.getInt("inq_no"));
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
