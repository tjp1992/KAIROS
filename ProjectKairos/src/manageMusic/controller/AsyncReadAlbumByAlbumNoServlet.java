package manageMusic.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import manageMusic.model.service.ManageMusicService;
import manageMusic.model.vo.Album;

/**
 * Servlet implementation class AsyncReadAlbumByAlbumNoServlet
 */
@WebServlet(name = "AsyncReadAlbumByAlbumNo", urlPatterns = { "/asyncReadAlbumByAlbumNo" })
public class AsyncReadAlbumByAlbumNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncReadAlbumByAlbumNoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int albumNo = Integer.parseInt(request.getParameter("albumNo"));
		
		Album a = new ManageMusicService().readOneAlbum(albumNo);
	
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		new Gson().toJson(a, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
