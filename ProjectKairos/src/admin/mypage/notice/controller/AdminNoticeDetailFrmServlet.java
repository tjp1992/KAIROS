package admin.mypage.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.mypage.model.dao.NoticeDao;
import admin.mypage.model.service.NoticeService;
import admin.mypage.model.vo.Notice;

/**
 * Servlet implementation class AdminNoticeDetailFrmServlet
 */
@WebServlet(name = "AdminNoticeDetailFrm", urlPatterns = { "/adminNoticeDetailFrm" })
public class AdminNoticeDetailFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminNoticeDetailFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noticeNum = Integer.parseInt(request.getParameter("noticeNo"));
		int req = Integer.parseInt(request.getParameter("reqPage"));
		Notice n = new NoticeService().noticeDetail(noticeNum);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/adminMypage/adminNoticeDetail.jsp");
		request.setAttribute("n", n);
		request.setAttribute("req", req);
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
