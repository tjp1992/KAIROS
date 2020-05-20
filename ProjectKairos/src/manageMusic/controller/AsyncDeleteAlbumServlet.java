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
 * Servlet implementation class AsyncDeleteAlbumServlet
 */
@WebServlet(name = "AsyncDeleteAlbum", urlPatterns = { "/asyncDeleteAlbum" })
public class AsyncDeleteAlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncDeleteAlbumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int albumNo = Integer.parseInt(request.getParameter("albumNo"));
		
		String root = request.getSession().getServletContext().getRealPath("/");
		
		int result = new ManageMusicService().deleteAlbum(root, albumNo);
				
		PrintWriter out =  response.getWriter();
		
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
