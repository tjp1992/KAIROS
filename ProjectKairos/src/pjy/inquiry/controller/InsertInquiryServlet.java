package pjy.inquiry.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import pjy.inquiry.service.InquiryService;
import pjy.inquiry.vo.Inquiry;

/**
 * Servlet implementation class InsertInquiryServlet
 */
@WebServlet(name = "InsertInquiry", urlPatterns = { "/insertInquiry" })
public class InsertInquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertInquiryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "공지사항 타입오류[enctype]");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return;
		}
		String root = getServletContext().getRealPath("/");//webcontent를 가져오겠다는 의미
		String saveDirectory = root+"upload/inquiry";
		System.out.println(saveDirectory);
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory/*파일저장 경로*/ , maxSize/*파일 최대크기*/, "UTF-8", new DefaultFileRenamePolicy()/*파일중복가능*/);
		Inquiry i = new Inquiry();
		i.setInqTitle(mRequest.getParameter("inquiryTitle"));
		i.setUserId(mRequest.getParameter("inquiryWriter"));
		i.setInqContent(mRequest.getParameter("inquiryContent"));
		i.setInqFilename(mRequest.getOriginalFileName("filename"));//사용자가 올린 파일명
		i.setInqFilepath(mRequest.getFilesystemName("filename"));//서버에 저장되는 파일명
		
		int result = new InquiryService().insertInquiry(i);
		RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			request.setAttribute("msg", "문의사항 등록 성공");
		}else {
			request.setAttribute("msg", "문의사항 등록 실패");
		}
		request.setAttribute("loc", "/mypageFrm");
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
