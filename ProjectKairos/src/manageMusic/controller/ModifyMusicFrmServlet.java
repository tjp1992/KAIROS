package manageMusic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageMusic.model.service.ManageMusicService;
import song.vo.SearchSong;
import song.vo.Song;

/**
 * Servlet implementation class ModifyMusicServlet
 */
@WebServlet(name = "ModifyMusicFrm", urlPatterns = { "/modifyMusicFrm" })
public class ModifyMusicFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMusicFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int songNo = Integer.parseInt(request.getParameter("songNo"));
		
		System.out.println(songNo);
		
		SearchSong s = new ManageMusicService().readOneSong(songNo);
		
		
		if(s != null) {
			request.setAttribute("song", s);
			
			request.getRequestDispatcher("/WEB-INF/views/manageMusic/modifyMusic.jsp").forward(request, response);;
			
		} else {
			request.setAttribute("msg", "정보 조회에 실패하였습니다.");
			request.setAttribute("loc", "/myMusicListFrm");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);;
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
