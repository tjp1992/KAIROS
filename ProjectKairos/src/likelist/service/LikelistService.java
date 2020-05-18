package likelist.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import likelist.dao.LikelistDao;
import playlist.vo.Playlist;
import user.vo.User;

public class LikelistService {

	public int addOneLike(String userId, int songNo) {
		Connection conn = JDBCTemplate.getConnection();
		int chkResult = new LikelistDao().checkLike(conn,userId,songNo);
		int result = 0;
		if(chkResult>0) {//이미 좋아요 리스트에 있는거
			 result = new LikelistDao().deleteOneLike(conn,userId,songNo);
		}else {//조아요 리스트에 없는거
			result = new LikelistDao().addOneLike(conn,userId,songNo);
		}
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			chkResult = -1;
		}
		
		JDBCTemplate.close(conn);
		return chkResult;
	}

	public ArrayList<Playlist> likeListView(User u) {
		Connection conn= JDBCTemplate.getConnection();
		ArrayList<Playlist> list = new LikelistDao().likeListView(conn, u);
		JDBCTemplate.close(conn);
		return list;
	}

}
