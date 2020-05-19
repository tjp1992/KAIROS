package playlist.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import playlist.service.PlaylistService;
import playlist.vo.Playlist;
import user.vo.User;

/**
 * Servlet implementation class PsearchKeywordServlet
 */
@WebServlet(name = "PsearchKeyword", urlPatterns = { "/psearchKeyword" })
public class PsearchKeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PsearchKeywordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User u = (User)session.getAttribute("user");
		String userId = u.getUserId();
		String keyword = request.getParameter("keyword");
		
		ArrayList<Playlist> list = new PlaylistService().pSearchKeyword(userId, keyword);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/myMusic/psearchResult.jsp");
		request.setAttribute("list", list);
		request.setAttribute("keyword", keyword);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
