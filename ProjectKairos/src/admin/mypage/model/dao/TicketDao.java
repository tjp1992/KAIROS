package admin.mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.mypage.model.vo.Inquiry;
import admin.mypage.model.vo.PurchaseLog;
import common.JDBCTemplate;

public class TicketDao {

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt "
				+ "from voucher "
				+ "left join purchase_log on voucher.VOUCHER_NO = purchase_log.VOUCHER_NO "
				+ "where purchase_log.EXPIRED_DATE>=sysdate";
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

	public ArrayList<PurchaseLog> selectList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseLog> list = new ArrayList<PurchaseLog>();
		String query = "select * from " + 
				"(SELECT ROWNUM AS rnum, n.* from " + 
				"(select voucher.VOUCHER_NAME, purchase_log.* from voucher left join purchase_log on voucher.VOUCHER_NO = purchase_log.VOUCHER_NO where purchase_log.EXPIRED_DATE>=sysdate order by purchase_date desc)n) " + 
				"where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				PurchaseLog pl = new PurchaseLog();
				pl.setBeginDate(rset.getDate("purchase_date"));
				pl.setExpiredDate(rset.getDate("expired_date"));
				pl.setPurchaseDate(rset.getDate("purchase_date"));
				pl.setPurchaseNo(rset.getInt("purchase_no"));
				pl.setUserId(rset.getString("user_id"));
				pl.setVoucherName(rset.getString("voucher_name"));
				pl.setVoucherNo(rset.getInt("voucher_no"));
				list.add(pl);
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
		String query = "select count(*) as cnt "
				+ "from voucher "
				+ "left join purchase_log on voucher.VOUCHER_NO = purchase_log.VOUCHER_NO "
				+ "where purchase_log.EXPIRED_DATE<sysdate";
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

	public ArrayList<PurchaseLog> selectList2(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseLog> list = new ArrayList<PurchaseLog>();
		String query = "select * from " + 
				"(SELECT ROWNUM AS rnum, n.* from " + 
				"(select voucher.VOUCHER_NAME, purchase_log.* from voucher left join purchase_log on voucher.VOUCHER_NO = purchase_log.VOUCHER_NO where purchase_log.EXPIRED_DATE<sysdate order by purchase_date desc)n) " + 
				"where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				PurchaseLog pl = new PurchaseLog();
				pl.setBeginDate(rset.getDate("purchase_date"));
				pl.setExpiredDate(rset.getDate("expired_date"));
				pl.setPurchaseDate(rset.getDate("purchase_date"));
				pl.setPurchaseNo(rset.getInt("purchase_no"));
				pl.setUserId(rset.getString("user_id"));
				pl.setVoucherName(rset.getString("voucher_name"));
				pl.setVoucherNo(rset.getInt("voucher_no"));
				list.add(pl);
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

	public int totalCount3(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt "
				+ "from voucher "
				+ "left join purchase_log on voucher.VOUCHER_NO = purchase_log.VOUCHER_NO "
				+ "where purchase_log.EXPIRED_DATE>=sysdate and user_id like ?";
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

	public ArrayList<PurchaseLog> selectList3(Connection conn, int start, int end, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseLog> list = new ArrayList<PurchaseLog>();
		String query = "select * from " + 
				"(SELECT ROWNUM AS rnum, n.* from " + 
				"(select voucher.VOUCHER_NAME, purchase_log.* from voucher left join purchase_log on voucher.VOUCHER_NO = purchase_log.VOUCHER_NO where purchase_log.EXPIRED_DATE>=sysdate and user_id like ? order by purchase_date desc)n) " + 
				"where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				PurchaseLog pl = new PurchaseLog();
				pl.setBeginDate(rset.getDate("purchase_date"));
				pl.setExpiredDate(rset.getDate("expired_date"));
				pl.setPurchaseDate(rset.getDate("purchase_date"));
				pl.setPurchaseNo(rset.getInt("purchase_no"));
				pl.setUserId(rset.getString("user_id"));
				pl.setVoucherName(rset.getString("voucher_name"));
				pl.setVoucherNo(rset.getInt("voucher_no"));
				list.add(pl);
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
		String query = "select count(*) as cnt "
				+ "from voucher "
				+ "left join purchase_log on voucher.VOUCHER_NO = purchase_log.VOUCHER_NO "
				+ "where purchase_log.EXPIRED_DATE<sysdate and user_id like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%"+search+"%");
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

	public ArrayList<PurchaseLog> selectList4(Connection conn, int start, int end, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseLog> list = new ArrayList<PurchaseLog>();
		String query = "select * from " + 
				"(SELECT ROWNUM AS rnum, n.* from " + 
				"(select voucher.VOUCHER_NAME, purchase_log.* from voucher left join purchase_log on voucher.VOUCHER_NO = purchase_log.VOUCHER_NO where purchase_log.EXPIRED_DATE<sysdate and user_id like ? order by purchase_date desc)n) " + 
				"where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				PurchaseLog pl = new PurchaseLog();
				pl.setBeginDate(rset.getDate("purchase_date"));
				pl.setExpiredDate(rset.getDate("expired_date"));
				pl.setPurchaseDate(rset.getDate("purchase_date"));
				pl.setPurchaseNo(rset.getInt("purchase_no"));
				pl.setUserId(rset.getString("user_id"));
				pl.setVoucherName(rset.getString("voucher_name"));
				pl.setVoucherNo(rset.getInt("voucher_no"));
				list.add(pl);
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
