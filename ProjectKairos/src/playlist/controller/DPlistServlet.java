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

import manageMusic.model.service.SessionPlayListService;
import playlist.service.PlaylistService;
import playlist.vo.Playlist;
import playlist.vo.SessionPlaylist;
import user.vo.User;

/**
 * Servlet implementation class DPlistServlet
 */
@WebServlet(name = "DPlist", urlPatterns = { "/dPlist" })
public class DPlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DPlistServlet() {
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
				
		// 두 어레이의 순서는 같음
		String songNo [] = request.getParameterValues("songNo"); // songNo 를 가져옴
		String orderNo [] = request.getParameterValues("orderNo"); // orderNo을 가져옴  !!!! 못가져옴!!! 잘못 가져옴!!! 순서대로 가져옴
		
		ArrayList<Playlist> list = new ArrayList<Playlist>();
		
		for(int i=0; i<songNo.length; i++) {
			
			Playlist p = new Playlist();			
			p.setOrderNo(Integer.parseInt(orderNo[i]));
			p.setSongNo(Integer.parseInt(songNo[i]));
			list.add(p);
		}
	
		
		
		
		int result = new PlaylistService().deletePlaylist(list,userId);
		
		if(result==list.size()) {
			result = new PlaylistService().sortPlaylist(userId);
			
			if(result>0) {
//				ArrayList<SessionPlaylist> pList = new SessionPlayListService().readPlayList(userId);
//				session.setAttribute("playList", pList);
				request.getRequestDispatcher("/playList").forward(request, response);				
			} else {
				request.setAttribute("msg", "삭제실패");
				request.setAttribute("loc", "/playList");
				request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			}
			
		}else {
			request.setAttribute("msg", "삭제실패");
			request.setAttribute("loc", "/playList");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
