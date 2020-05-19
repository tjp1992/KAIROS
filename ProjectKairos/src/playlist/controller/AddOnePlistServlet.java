package playlist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageMusic.model.service.SessionPlayListService;
import playlist.service.PlaylistService;
import playlist.vo.SessionPlaylist;
import user.vo.User;



/**
 * Servlet implementation class AddOnePlistServlet
 */
@WebServlet(name = "AddOnePlist", urlPatterns = { "/addOnePlist" })
public class AddOnePlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOnePlistServlet() {
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
		int songNo = Integer.parseInt(request.getParameter("songNo"));
		
		int result = new PlaylistService().addOnePlist(userId,songNo);
//		if(result>0) {
//			ArrayList<SessionPlaylist> pList = new SessionPlayListService().readPlayList(userId);
//			session.setAttribute("playList", pList);
//		}
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
