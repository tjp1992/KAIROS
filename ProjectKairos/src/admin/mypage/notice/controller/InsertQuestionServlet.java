package admin.mypage.notice.controller;

import java.io.IOException;
import java.sql.Date;

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
 * Servlet implementation class InsertQuestionServlet
 */
@WebServlet(name = "InsertQuestion", urlPatterns = { "/insertQuestion" })
public class InsertQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			Inquiry iq = new Inquiry();
			iq.setInqTitle(request.getParameter("title"));
			iq.setInqContent(request.getParameter("questionContent"));
			iq.setInqNo(Integer.parseInt(request.getParameter("inqNo")));
			
			int result = new QuestionService().insertInquiryAnswer(iq);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			if(result>0) {
				int result2 = new QuestionService().changeDefault(iq);
				if(result2>0) {
				request.setAttribute("msg", "답변 등록이 완료되었습니다.");
				request.setAttribute("loc", "/adminQuestion?reqPage=1&check=1&reqPage2=1");
				}else {
					request.setAttribute("msg", "답변 등록이 실패되었습니다.");
					request.setAttribute("loc", "/adminQuestion?reqPage=1&check=1&reqPage2=1");	
				}
				
				
			}else {
				request.setAttribute("msg", "답변 등록이 취소되었습니다.");
				request.setAttribute("loc", "/adminQuestion?reqPage=1&check=1");
			}
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
