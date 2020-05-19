package admin.mypage.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.mypage.model.service.QuestionService;
import admin.mypage.model.vo.InquiryAnswer;

/**
 * Servlet implementation class ModifyQuestionAnswerServlet
 */
@WebServlet(name = "ModifyQuestionAnswer", urlPatterns = { "/modifyQuestionAnswer" })
public class ModifyQuestionAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyQuestionAnswerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inqAnsNo = Integer.parseInt(request.getParameter("inqAnsNo"));
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		int reqPage2 = Integer.parseInt(request.getParameter("reqPage2"));
		int inqNo = Integer.parseInt(request.getParameter("inqNo"));
		InquiryAnswer iqa = new InquiryAnswer();
		iqa.setInqAnsTitle(request.getParameter("title"));
		iqa.setInqAnsContent(request.getParameter("questionContent"));
		int result = new QuestionService().updateAnswer(inqAnsNo,iqa);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "답변 수정이 완료되었습니다.");
			request.setAttribute("loc", "/adminQuestionEndDetailFrm?inqNo="+inqNo+"&reqPage="+reqPage+"&reqPage2="+reqPage2);
		}else {
			request.setAttribute("msg", "답변 수정이 취소되었습니다.");
			request.setAttribute("loc", "/adminQuestionEndDetailFrm?inqNo="+inqNo+"&reqPage="+reqPage+"&reqPage2="+reqPage2);
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
