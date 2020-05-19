package admin.mypage.notice.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.mypage.model.service.QuestionService;
import admin.mypage.model.vo.InquiryAnswer;

/**
 * Servlet implementation class AdminQuestionAnswerModifyServlet
 */
@WebServlet(name = "AdminQuestionAnswerModify", urlPatterns = { "/adminQuestionAnswerModify" })
public class AdminQuestionAnswerModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminQuestionAnswerModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inqNo = Integer.parseInt(request.getParameter("inqNo"));
		int inqAnsNo = Integer.parseInt(request.getParameter("inqAnsNo"));
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		int reqPage2 = Integer.parseInt(request.getParameter("reqPage2"));
		InquiryAnswer iqa = new QuestionService().answerContent(inqAnsNo);
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
		Date time = new Date();
		String today = format1.format(time);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/adminMypage/adminInquiryAnswerModify.jsp");
		request.setAttribute("iqa", iqa);
		request.setAttribute("reqPage", reqPage);
		request.setAttribute("reqPage2", reqPage2);
		request.setAttribute("inqNo", inqNo);
		request.setAttribute("inqAnsNo", inqAnsNo);
		request.setAttribute("today", today);
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
