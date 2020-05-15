package admin.mypage.ticket.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.mypage.model.service.NoticeService;
import admin.mypage.model.service.TicketService;
import admin.mypage.model.vo.NoticePageData;

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
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		NoticePageData pd = new TicketService().selectList(reqPage);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/adminMypage/adminNotice.jsp");
		request.setAttribute("list", pd.getList());
		request.setAttribute("pageNavi", pd.getPageNavi());
		request.setAttribute("req", reqPage);
		rd.forward(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/adminMypage/adminTicket.jsp");
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
