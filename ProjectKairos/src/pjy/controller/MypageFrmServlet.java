package pjy.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.vo.User;

/**
 * Servlet implementation class MypageFrmServlet
 */
@WebServlet(name = "MypageFrm", urlPatterns = { "/mypageFrm" })
public class MypageFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/mypage.jsp");
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		 Date expire = u.getExpiredDate();
		 System.out.println("이용권 만기 날짜 : "+expire);
		 Date today = new Date();
		 System.out.println("오늘날짜 : "+today);
		 if(expire==null) {
			 request.setAttribute("date", null);
		 }else {
			 Long expireDate = expire.getTime();
			 Long todayDate = today.getTime();
			 System.out.println("이용권 만기날짜 롱형: "+expireDate);
			 System.out.println("오늘날짜 롱형 : "+todayDate);
			 Long gabDate = (expireDate-todayDate)/(1000*60*60*24);
			 request.setAttribute("date", gabDate);
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
