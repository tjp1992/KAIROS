package search.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import search.model.service.SearchSongService;
import search.model.vo.SearchResult;
import song.vo.SearchSong;

/**
 * Servlet implementation class SearchSongServlet
 */
@WebServlet(name = "SearchSong", urlPatterns = { "/searchSong" })
public class SearchSongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchSongServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String keyword = request.getParameter("keyword");
		
		SearchResult sResult = new SearchSongService().searchByKeword(keyword);
		request.setAttribute("keyword", keyword);
		request.setAttribute("list", sResult.getList());
		request.setAttribute("totalNum", sResult.getTotalResult());
		
		request.getRequestDispatcher("/WEB-INF/views/search/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
