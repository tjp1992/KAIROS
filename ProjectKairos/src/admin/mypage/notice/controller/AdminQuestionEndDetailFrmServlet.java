package admin.mypage.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.mypage.model.service.QuestionService;
import admin.mypage.model.vo.Inquiry;
import admin.mypage.model.vo.InquiryAnswer;

/**
 * Servlet implementation class AdminQuestionEndDetailFrmServlet
 */
@WebServlet(name = "AdminQuestionEndDetailFrm", urlPatterns = { "/adminQuestionEndDetailFrm" })
public class AdminQuestionEndDetailFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminQuestionEndDetailFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inqNo = Integer.parseInt(request.getParameter("inqNo"));
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		int reqPage2 = Integer.parseInt(request.getParameter("reqPage2"));
		Inquiry iq = new QuestionService().QuestionDetail(inqNo);
		InquiryAnswer iqa = new QuestionService().QuestionDetailEnd(inqNo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/adminMypage/adminQuestionEndDetail.jsp");
		request.setAttribute("iqa", iqa);
		request.setAttribute("iq", iq);
		request.setAttribute("reqPage", reqPage);
		request.setAttribute("reqPage2", reqPage2);
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
