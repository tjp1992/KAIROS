package search.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
 * Servlet implementation class AsyncAddPlayFirstServlet
 */
@WebServlet(name = "AsyncAddPlayFirst", urlPatterns = { "/asyncAddPlayFirst" })
public class AsyncAddPlayFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncAddPlayFirstServlet() {
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
		
		String songNo[] = request.getParameterValues("songNo");
		
		ArrayList<Playlist> list = new ArrayList<Playlist>(); 
		
		for(String str : songNo) {
			Playlist p = new Playlist();
			p.setSongNo(Integer.parseInt(str));
			
			list.add(p);
		}
		
		int result = new PlaylistService().frontAdd(list, userId);
		
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
