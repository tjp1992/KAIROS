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
import admin.mypage.model.vo.Notice;

/**
 * Servlet implementation class ModifyNoticeServlet
 */
@WebServlet(name = "ModifyNotice", urlPatterns = { "/modifyNotice" })
public class ModifyNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		int req = Integer.parseInt(request.getParameter("reqPage"));
		String title = request.getParameter("noticeTitle");
		String content = request.getParameter("noticeContent");
		int result = new NoticeService().modifyNotice(noticeNo,title,content);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "수정이 완료되었습니다.");
			request.setAttribute("loc", "/adminNotice?reqPage="+req);
		}else {
			request.setAttribute("msg", "수정이 취소되었습니다.");
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
