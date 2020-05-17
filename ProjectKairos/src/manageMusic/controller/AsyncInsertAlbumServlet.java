package manageMusic.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageMusic.model.service.ManageMusicService;

/**
 * Servlet implementation class AsyncInsertAlbumServlet
 */
@WebServlet(name = "AsyncInsertAlbum", urlPatterns = { "/asyncInsertAlbum" })
public class AsyncInsertAlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncInsertAlbumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String albumOwner = request.getParameter("albumOwner");
		String albumName = request.getParameter("albumName");
		
		int result = new ManageMusicService().insertAlbum(albumOwner, albumName);
		
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
