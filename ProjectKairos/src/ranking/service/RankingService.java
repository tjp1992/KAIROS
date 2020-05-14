package ranking.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import ranking.dao.RankingDao;
import song.vo.RankingPageData;
import song.vo.RankingSong;

public class RankingService {

	

	public RankingPageData getRankBySong(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int totalCount = 100;
		int totalPage = 10;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		ArrayList<RankingSong> list = new RankingDao().getRankBySong(conn,start,end);
		return null;
	}
	
}
