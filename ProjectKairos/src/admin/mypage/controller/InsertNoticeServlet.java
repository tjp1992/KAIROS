package admin.mypage.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.mypage.model.service.NoticeService;
import admin.mypage.model.vo.Notice;


/**
 * Servlet implementation class InsertNoticeServlet
 */
@WebServlet(name = "InsertNotice", urlPatterns = { "/insertNotice" })
public class InsertNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Notice n = new Notice();
		n.setNoticeTitle(request.getParameter("noticeTitle"));
		n.setNoticeContent(request.getParameter("noticeContent"));
		System.out.println(request.getParameter("noticeTitle"));
		int result = new NoticeService().insertNotice(n);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "등록이 완료되었습니다.");
			request.setAttribute("loc", "/adminNotice?reqPage=1");
		}else {
			request.setAttribute("msg", "등록이 취소되었습니다.");
			request.setAttribute("loc", "/adminNoticeWriteFrm");
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
