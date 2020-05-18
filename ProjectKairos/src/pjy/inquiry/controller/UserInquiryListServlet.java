package pjy.inquiry.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.mypage.model.vo.InquiryAnswer;
import pjy.inquiry.service.InquiryService;
import pjy.inquiry.vo.Inquiry;
import pjy.inquiry.vo.InquiryPageData;
import user.vo.User;

/**
 * Servlet implementation class UserInquiryListServlet
 */
@WebServlet(name = "UserInquiryList", urlPatterns = { "/userInquiryList" })
public class UserInquiryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInquiryListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String myId = user.getUserId();
		InquiryPageData pd = new InquiryService().selectMyList(reqPage,myId);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/inquiry/userInquiryList.jsp");
		request.setAttribute("list", pd.getList());
		request.setAttribute("pageNavi", pd.getPageNavi());
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
