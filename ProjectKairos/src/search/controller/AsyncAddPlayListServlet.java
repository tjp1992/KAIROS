package search.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import playlist.service.PlaylistService;
import user.vo.User;

/**
 * Servlet implementation class AsyncAddPlayListServlet
 */
@WebServlet(name = "AsyncAddPlayList", urlPatterns = { "/asyncAddPlayList" })
public class AsyncAddPlayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncAddPlayListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		User u = (User) session.getAttribute("user");		
		
		String songNoStr = request.getParameter("songNo");
		
		StringTokenizer sT = new StringTokenizer(songNoStr,",");
		
		String songNo[] = new String[sT.countTokens()];
		
		for(int i=0; i<songNo.length; i++) {
			songNo[i] = sT.nextToken();
		}
				
		int result = new PlaylistService().updateSongPlist(songNo, u.getUserId());
		
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
