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
		String pageNavi="";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo !=1) {
			pageNavi += "<a class='btn' href='/rankingFrm?reqPage="+(pageNo-pageNaviSize)+"'>이전</a>";
		}
		for(int i=0;i>pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<span class='selectPage'>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='btn' href='/rankingFrm?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='btn' href='/rankingFrm?reqPage="+pageNo+"'>다음</a>";
		}
		RankingPageData pd = new RankingPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return pd;
	}
	
}
