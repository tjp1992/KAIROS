package manageMusic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import search.model.service.SearchSongService;
import search.model.vo.MyListPageData;
import search.model.vo.ReqMyList;
import search.model.vo.ReqSearch;
import search.model.vo.SearchPageData;
import user.vo.User;

/**
 * Servlet implementation class AdminMusicListFrmServlet
 */
@WebServlet(name = "AdminMusicListFrm", urlPatterns = { "/adminMusicListFrm" })
public class AdminMusicListFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMusicListFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession session = request.getSession(false);

		User u = (User) session.getAttribute("user");

		ReqSearch req = new ReqSearch();

		if (u == null) {
			request.setAttribute("loc", "/login");
			request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		} else if (!u.getUserId().equals("admin")) {
			request.setAttribute("loc", "/login");
			request.setAttribute("msg", "관리자 페이지입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		}

		// 사용자가 검색 키워드를 입력했는지 확인
		if(request.getParameter("keyword") != null) { 
			req.setKeyword(request.getParameter("keyword"));
		} else {
			req.setKeyword("");
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
		request.getRequestDispatcher("/WEB-INF/views/manageMusic/adminMusicList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
