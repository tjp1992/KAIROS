package manageMusic.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageMusic.model.service.ManageMusicService;
import search.model.service.SearchSongService;
import search.model.vo.MyListPageData;
import search.model.vo.ReqMyList;
import song.vo.SearchSong;
import user.vo.User;

/**
 * Servlet implementation class MyMusicListFrmServlet
 */
@WebServlet(name = "MyMusicListFrm", urlPatterns = { "/myMusicListFrm" })
public class MyMusicListFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyMusicListFrmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		User u = (User) session.getAttribute("user");

		ReqMyList req = new ReqMyList();

		if (u == null) {
			request.setAttribute("loc", "/login");
			request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		}

		req.setUserNick(u.getUserNick());

		if (request.getParameter("numPerPage") != null) {
			req.setNumPerPage(Integer.parseInt(request.getParameter("numPerPage")));
		}
		if (request.getParameter("reqPage") != null) {
			req.setReqPage(Integer.parseInt(request.getParameter("reqPage")));
		}

		if (request.getParameter("reSearch") != null) {
			req.setReSearch(request.getParameter("reSearch"));
		}

		MyListPageData pd = new SearchSongService().searchMyList(req);

		request.setAttribute("req", req);
		request.setAttribute("list", pd.getList());
		request.setAttribute("pageNavi", pd.getPageNavi());
		request.getRequestDispatcher("/WEB-INF/views/manageMusic/myMusicList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
