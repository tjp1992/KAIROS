package search.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import search.model.service.SearchSongService;
import search.model.vo.ReqSearch;
import search.model.vo.SearchPageData;
import search.model.vo.SearchResult;
import search.model.vo.SearchSong;
import user.vo.User;

/**
 * Servlet implementation class SearchSongServlet
 */
@WebServlet(name = "SearchSong", urlPatterns = { "/searchSong" })
public class SearchSongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchSongServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session =request.getSession(false); 
				
		User u = (User)session.getAttribute("user");
		
		ReqSearch req = new ReqSearch();
		
		// 유저가 로그인 되어있으면 검색 조건에 포함 (좋아요 여부 확인필요)
		if(u!=null) { 
			req.setUserId(u.getUserId());
		}
		
		// 사용자가 검색 키워드를 입력했는지 확인
		if(request.getParameter("keyword") != null) { 
			req.setKeyword(request.getParameter("keyword"));
		}
		
		// 사용자가 카테고리를 선택했는지 확인
		if(request.getParameter("category") != null) { 
			req.setCategory(request.getParameter("category"));
		}
		
		// 사용자가 장르를 선택했는지 확인
		if(request.getParameter("genre") != null) { 
			req.setGenre(request.getParameter("genre"));
		}
		
		// 사용자가 유료/무료 선택했는지 확인
		if(request.getParameter("licensed") != null) { 
			req.setLicensed(Integer.parseInt(request.getParameter("licensed")));
		}
		
		// 사용자가 출력 건수 선택했는지 확인
		if(request.getParameter("numPerPage") != null) {
			req.setNumPerPage(Integer.parseInt(request.getParameter("numPerPage")));
		} else {
			req.setNumPerPage(25);
		}
		
		// 사용자 요청 페이지번호 확인
		if(request.getParameter("reqPage") != null) {
			req.setReqPage(Integer.parseInt(request.getParameter("reqPage")));
		} else { // 요청 없을시 1 페이지
			req.setReqPage(1);
		}
		
		SearchPageData pd = null;
		
		// 결과내 재검색 했는지 여부		
		if(request.getParameter("reSearch") != null) {			
			req.setReSearch(request.getParameter("reSearch"));			
			pd = new SearchSongService().reSearchSong(req);
		} else {
			
			pd = new SearchSongService().searchSong(req);
		}
		
		
		request.setAttribute("list", pd.getList());
		request.setAttribute("pageNavi", pd.getPageNavi());
		request.setAttribute("totalCount", pd.getTotalCount());
		request.setAttribute("req", req);
		request.getRequestDispatcher("/WEB-INF/views/search/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
