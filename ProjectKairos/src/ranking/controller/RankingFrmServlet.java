package ranking.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ranking.service.RankingService;
import song.vo.RankingPageData;
import song.vo.RankingSong;
import song.vo.Song;
import user.vo.User;

/**
 * Servlet implementation class RankingFrmServlet
 */
@WebServlet(name = "RankingFrm", urlPatterns = { "/rankingFrm" })
public class RankingFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankingFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("user");
		RankingPageData pd = null;
		String reqType = request.getParameter("reqType");
		
		if(user == null) {
			pd = new RankingService().getRankBySong(reqPage,reqType);
		} else {			
			String userId = user.getUserId();
			pd = new RankingService().getRankBySong(reqPage,reqType,userId);
		}
		request.setAttribute("list", pd.getList());
		request.setAttribute("pageNavi", pd.getPageNavi());
		request.setAttribute("reqType", reqType);
		request.getRequestDispatcher("/WEB-INF/views/ranking/rank.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
