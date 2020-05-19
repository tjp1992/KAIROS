package playlist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.jrockit.jfr.RequestDelegate;

import manageMusic.model.service.SessionPlayListService;
import playlist.service.PlaylistService;
import playlist.vo.SessionPlaylist;
import user.vo.User;

/**
 * Servlet implementation class UPlistServlet
 */
@WebServlet(name = "UPlist", urlPatterns = { "/uPlist" })
public class UPlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UPlistServlet() {
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
		
		String arr [] = request.getParameterValues("songNo");
//		
//		for(String str:arr) {
//			System.out.println(arr);
//		}
		
		
		int result = new PlaylistService().updateSongPlist(arr,userId);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			
//			ArrayList<SessionPlaylist> pList = new SessionPlayListService().readPlayList(userId);
//			session.setAttribute("playList", pList);
			request.setAttribute("msg", "플레이리스트 추가 성공");
			request.setAttribute("loc", "/likeList");
			
		}else {
			request.setAttribute("msg", "플레이리스트 추가실패");
			request.setAttribute("loc", "/likeList");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		}
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
