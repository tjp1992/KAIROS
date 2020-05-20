package ranking.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import ranking.dao.RankingDao;
import song.vo.RankingPageData;
import song.vo.RankingSong;

public class RankingService {

	
	
	public RankingPageData getRankBySong(int reqPage, String reqType, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 20;
		int totalCount = 100;
		int totalPage = 5;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		ArrayList<RankingSong> list = null;
		switch(reqType) {
		case "song":
			list = new RankingDao().getRankBySong(conn,start,end,userId);			
			break;
		case "play":
			list = new RankingDao().getRankByPlay(conn,start,end,userId);
			break;
		case "artist":
			list = new RankingDao().getRankByArtist(conn,start,end);
			break;
		case "album":
			list = new RankingDao().getRankByAlbum(conn,start,end);
			break;
			default:
				break;
		}
		if(reqType.equals("song")) {
		}else {
		}
		String pageNavi="";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo !=1) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/rankingFrm?reqPage="+(pageNo-pageNaviSize)+"&reqType="+reqType+"'>이전</a></li>";
		}
		for(int i=0;i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<li class='page-item'><span class='page-link'>"+pageNo+"</span></li>";
			}else {
				pageNavi += "<li class='page-item'><a class='page-link' href='/rankingFrm?reqPage="+pageNo+"&reqType="+reqType+"'>"+pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/rankingFrm?reqPage="+pageNo+"&reqType="+reqType+"'>다음</a></li>";
		}


		
		RankingPageData pd = new RankingPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return pd;
	}
	public RankingPageData getRankBySong(int reqPage, String reqType) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 20;
		int totalCount = 100;
		int totalPage = 5;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage * numPerPage;
		ArrayList<RankingSong> list = null;
		switch(reqType) {
		case "song":
			list = new RankingDao().getRankBySong(conn,start,end);			
			break;
		case "play":
			list = new RankingDao().getRankByPlay(conn,start,end);
			break;
		case "artist":
			list = new RankingDao().getRankByArtist(conn,start,end);
			break;
		case "album":
			list = new RankingDao().getRankByAlbum(conn,start,end);
			break;
			default:
				break;
		}
		if(reqType.equals("song")) {
		}else {
		}
		String pageNavi="";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo !=1) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/rankingFrm?reqPage="+(pageNo-pageNaviSize)+"&reqType="+reqType+"'>이전</a></li>";
		}
		for(int i=0;i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<li class='page-item'><span class='page-link'>"+pageNo+"</span></li>";
			}else {
				pageNavi += "<li class='page-item'><a class='page-link' href='/rankingFrm?reqPage="+pageNo+"&reqType="+reqType+"'>"+pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/rankingFrm?reqPage="+pageNo+"&reqType="+reqType+"'>다음</a></li>";
		}


		
		RankingPageData pd = new RankingPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return pd;
	}
}
