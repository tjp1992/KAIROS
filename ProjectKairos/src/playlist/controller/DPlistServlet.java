package playlist.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import playlist.service.PlaylistService;
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
		
		String arr [] = request.getParameterValues("songNo");
		
		int result = new PlaylistService().deletePlaylist(arr,userId);
		if(result==arr.length) {
			request.getRequestDispatcher("/playList").forward(request, response);
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
