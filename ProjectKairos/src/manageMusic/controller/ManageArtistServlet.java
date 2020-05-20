package manageMusic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageMusic.model.service.ManageMusicService;
import manageMusic.model.vo.LicensedArtist;

/**
 * Servlet implementation class ManageArtistServlet
 */
@WebServlet(name = "ManageArtist", urlPatterns = { "/manageArtist" })
public class ManageArtistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageArtistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LicensedArtist l = new LicensedArtist();
		l.setLcnArtistName(request.getParameter("artist"));
		l.setLcnCompany(request.getParameter("lcn_company"));
		l.setLcnAgentName(request.getParameter("lcn_agent_name"));
		l.setLcnAgentPhone(request.getParameter("lcn_agent_phone"));
		
		int result = ManageMusicService.updateLcnArtist(l);
		
		if(result >0) {
			request.setAttribute("msg", "정보 변경 완료");
		} else {
			request.setAttribute("msg", "정보 변경 완료");
		}
		request.setAttribute("loc", "/manageArtistFrm");
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
