package search.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import likelist.service.LikelistService;
import user.vo.User;

/**
 * Servlet implementation class AsyncSearchLikeServlet
 */
@WebServlet(name = "AsyncSearchLike", urlPatterns = { "/asyncSearchLike" })
public class AsyncSearchLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncSearchLikeServlet() {
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
		
		int result = new LikelistService().addOneLike(userId, songNo);
		
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
