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
import playlist.vo.Playlist;
import playlist.vo.SessionPlaylist;
import user.vo.User;

/**
 * Servlet implementation class FrontAddOneServlet
 */
@WebServlet(name = "FrontAddOne", urlPatterns = { "/frontAddOne" })
public class FrontAddOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontAddOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User u = (User)session.getAttribute("user");
		String userId=u.getUserId();
		
		String songNo [] = request.getParameterValues("songNo");
		String orderNo[] = request.getParameterValues("orderNo");
		
		ArrayList<Playlist> list = new ArrayList<Playlist>();
		
		for(int i=0; i<songNo.length; i++) {
			Playlist p = new Playlist();
			
			p.setSongNo(Integer.parseInt(songNo[i]));
			p.setOrderNo(Integer.parseInt(orderNo[i]));
			list.add(p);
		}
		int result = new PlaylistService().frontAdd(list, userId);
		
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
