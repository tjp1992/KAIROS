package pjy.inquiry.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.mypage.model.vo.InquiryAnswer;
import pjy.inquiry.service.InquiryService;
import pjy.inquiry.vo.Inquiry;

/**
 * Servlet implementation class MyInquiryViewServlet
 */
@WebServlet(name = "MyInquiryView", urlPatterns = { "/myInquiryView" })
public class MyInquiryViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInquiryViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int InqNo = Integer.parseInt(request.getParameter("inqNo"));
		Inquiry i = new InquiryService().selectOneInquiry(InqNo);
		InquiryAnswer ia = new InquiryService().inquiryAnswer(InqNo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/inquiry/inquiryView.jsp");
		request.setAttribute("i", i);
		request.setAttribute("ia", ia);
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
