package manageMusic.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageMusic.model.service.ManageMusicService;
import manageMusic.model.vo.LicensedArtist;

/**
 * Servlet implementation class SearchArtistServlet
 */
@WebServlet(name = "SearchArtist", urlPatterns = { "/searchArtist" })
public class SearchArtistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchArtistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String artistName = request.getParameter("artistName");
		
		if(artistName != null) {
			
			ArrayList<LicensedArtist> list = new ManageMusicService().searchArtist(artistName);
			request.setAttribute("list", list);
		}
		request.getRequestDispatcher("/WEB-INF/views/manageMusic/searchArtist.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
