package admin.mypage.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.mypage.model.service.NoticeService;
import admin.mypage.model.service.QuestionService;
import admin.mypage.model.vo.Inquiry;
import admin.mypage.model.vo.Notice;

/**
 * Servlet implementation class AdminQuestionDetailFrmServlet
 */
@WebServlet(name = "AdminQuestionDetailFrm", urlPatterns = { "/adminQuestionDetailFrm" })
public class AdminQuestionDetailFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminQuestionDetailFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inqNo = Integer.parseInt(request.getParameter("inqNo"));
		System.out.println(inqNo);
		int req = Integer.parseInt(request.getParameter("reqPage"));
		int req2 = Integer.parseInt(request.getParameter("reqPage2"));
		Inquiry iq = new QuestionService().QuestionDetail(inqNo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/adminMypage/adminQuestionDetail.jsp");
		request.setAttribute("iq", iq);
		request.setAttribute("req", req);
		request.setAttribute("req2", req2);
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
