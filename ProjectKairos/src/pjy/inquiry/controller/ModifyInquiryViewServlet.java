package pjy.inquiry.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pjy.inquiry.service.InquiryService;
import pjy.inquiry.vo.Inquiry;

/**
 * Servlet implementation class ModifyInquiryViewServlet
 */
@WebServlet(name = "ModifyInquiryView", urlPatterns = { "/modifyInquiryView" })
public class ModifyInquiryViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyInquiryViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inqNo = Integer.parseInt(request.getParameter("inqNo"));
		String mInqTitle = request.getParameter("modifyInquiryTitle");
		String mInqContent = request.getParameter("modifyInquiryContent");
		System.out.println(inqNo);
		System.out.println(mInqTitle);
		System.out.println(mInqContent);
		Inquiry inq = new Inquiry();
		inq.setInqNo(inqNo);
		inq.setInqTitle(mInqTitle);
		inq.setInqContent(mInqContent);
		int result = new InquiryService().modifyInquiryView(inq);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "게시물 수정 완료");
			request.setAttribute("loc", "/myInquiryView?inqNo="+inqNo);
		}else {
			request.setAttribute("msg", "게시물 수정 실패");
			request.setAttribute("loc", "/myInquiryView?inqNo="+inqNo);
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
