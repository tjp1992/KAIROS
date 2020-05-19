package likelist.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import likelist.service.LikelistService;
import user.vo.User;

/**
 * Servlet implementation class DLlistServlet
 */
@WebServlet(name = "DLlist", urlPatterns = { "/dLlist" })
public class DLlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DLlistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		
		String userId = u.getUserId();
		
		String arr[] =request.getParameterValues("songNo");
		int result = new LikelistService().deleteLikelist(arr,userId);
		if(result==arr.length) {
			request.getRequestDispatcher("/likeList").forward(request, response);
		}else {
			request.setAttribute("msg", "삭제실패");
			request.setAttribute("loc", "/likeList");
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
