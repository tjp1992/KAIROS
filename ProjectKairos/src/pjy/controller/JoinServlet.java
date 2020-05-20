package pjy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import user.service.UserService;
import user.vo.User;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet(name = "Join", urlPatterns = { "/join" })
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JoinServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User u = new User();
		String addr1 = request.getParameter("postCode");
		String addr2 = request.getParameter("roadAddr");
		String addr3 = request.getParameter("detailAddr");
		String addr = addr1 + "/" + addr2 + "/" + addr3;
		u.setUserId(request.getParameter("id"));
		u.setUserPw(request.getParameter("pw"));
		u.setEmail(request.getParameter("fullMail"));
		u.setPhone(request.getParameter("phone"));
		u.setUserName(request.getParameter("name"));
		u.setUserNick(request.getParameter("nick"));
		u.setAddr(addr);
		int result = new UserService().insertUser(u);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if (result > 0) {
			request.setAttribute("msg", "회원가입 성공");
			request.setAttribute("loc", "/index.jsp");
		} else {
			request.setAttribute("msg", "회원가입 실패");
			request.setAttribute("loc", "/");
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
