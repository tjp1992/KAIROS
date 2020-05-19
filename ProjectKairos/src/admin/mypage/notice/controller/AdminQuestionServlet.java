package admin.mypage.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.mypage.model.service.QuestionService;
import admin.mypage.model.service.TicketService;
import admin.mypage.model.vo.NoticePageData;
import admin.mypage.model.vo.QuestionPageData;

/**
 * Servlet implementation class AdminQuestionServlet
 */
@WebServlet(name = "AdminQuestion", urlPatterns = { "/adminQuestion" })
public class AdminQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminQuestionServlet() {
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
		QuestionPageData qd = null;
		QuestionPageData qd2 = null;
		String search = null;
		if(!(request.getParameter("search") == null || request.getParameter("search").equals(""))) {
			search = request.getParameter("search");
			qd = new QuestionService().selectList3(reqPage, search);
			qd2 = new QuestionService().selectList4(reqPage2, search);
		}else {
			qd = new QuestionService().selectList(reqPage);
			qd2 = new QuestionService().selectList2(reqPage2);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/adminMypage/adminQuestion.jsp");
		request.setAttribute("check", check);
		request.setAttribute("list", qd.getList());
		request.setAttribute("pageNavi", qd.getPageNavi());
		request.setAttribute("reqPage", reqPage);
		
		request.setAttribute("list2", qd2.getList());
		request.setAttribute("pageNavi2", qd2.getPageNavi());
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
