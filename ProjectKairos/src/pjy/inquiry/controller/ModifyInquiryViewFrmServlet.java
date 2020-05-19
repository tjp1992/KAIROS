package pjy.inquiry.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModifyInquiryViewFrmServlet
 */
@WebServlet(name = "ModifyInquiryViewFrm", urlPatterns = { "/modifyInquiryViewFrm" })
public class ModifyInquiryViewFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyInquiryViewFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inqNo = Integer.parseInt(request.getParameter("inqNo"));
		String inqTitle = request.getParameter("inqTitle");
		String inqContent = request.getParameter("inqCon");
		System.out.println(inqNo);
		System.out.println(inqTitle);
		System.out.println(inqContent);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/inquiry/modifyInquiryView.jsp");
		request.setAttribute("inqNo", inqNo);
		request.setAttribute("title", inqTitle);
		request.setAttribute("content", inqContent);
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
