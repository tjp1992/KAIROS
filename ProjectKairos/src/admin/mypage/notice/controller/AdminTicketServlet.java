package admin.mypage.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.mypage.model.service.TicketService;
import admin.mypage.model.vo.TicketPageData;

/**
 * Servlet implementation class AdminTicketServlet
 */
@WebServlet(name = "AdminTicket", urlPatterns = { "/adminTicket" })
public class AdminTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminTicketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		int reqPage2 = Integer.parseInt(request.getParameter("reqPage2"));
		int check = Integer.parseInt(request.getParameter("check"));
		TicketPageData tpa = null;
		TicketPageData tpa2= null;
		String search = null;
		
		if(!(request.getParameter("search") == null || request.getParameter("search").equals(""))) {
			search = request.getParameter("search");
			tpa = new TicketService().listSelect3(reqPage,search);
			tpa2 = new TicketService().listSelect4(reqPage2,search);
		}else {
			tpa = new TicketService().listSelect(reqPage);
			tpa2 = new TicketService().listSelect2(reqPage2);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/adminMypage/adminTicket.jsp");
		request.setAttribute("check", check);
		request.setAttribute("list", tpa.getList());
		request.setAttribute("pageNavi", tpa.getPageNavi());
		request.setAttribute("reqPage", reqPage);
		
		request.setAttribute("list2", tpa2.getList());
		request.setAttribute("pageNavi2", tpa2.getPageNavi());
		request.setAttribute("reqPage2", reqPage2);
		request.setAttribute("search", search);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
