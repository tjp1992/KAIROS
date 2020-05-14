package ranking.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import ranking.dao.RankingDao;
import song.vo.RankingSong;
import song.vo.Song;

public class RankingService {

	public ArrayList<RankingSong> getRankBySong() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList <RankingSong> list = new RankingDao().getRankBySong(conn);
		JDBCTemplate.close(conn);
		return list;
	}
	
}
