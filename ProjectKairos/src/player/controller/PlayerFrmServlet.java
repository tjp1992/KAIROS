package player.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageMusic.model.service.SessionPlayListService;
import playlist.vo.SessionPlaylist;
import user.vo.User;

/**
 * Servlet implementation class PlayerFrmServlet
 */
@WebServlet(name = "PlayerFrm", urlPatterns = { "/playerFrm" })
public class PlayerFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlayerFrmServlet() {
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

		String songNoStr = request.getParameter("songNo");
		
		StringTokenizer sT = new StringTokenizer(songNoStr);
		
		String songNo[] = new String[sT.countTokens()];
		
		if(u == null) {
			
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("loc", "/loginFrm");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			
		} else if (songNo.length != 0) {
			
			
			
			
		} else {
			ArrayList<SessionPlaylist> pList = new SessionPlayListService().readPlayList(u.getUserId());		
			session.setAttribute("playList", pList);
			
		}
		
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
