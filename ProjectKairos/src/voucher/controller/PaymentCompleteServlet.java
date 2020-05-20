package voucher.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.service.UserService;
import user.vo.User;

/**
 * Servlet implementation class PaymentCompleteServlet
 */
@WebServlet(name = "PaymentComplete", urlPatterns = { "/paymentComplete" })
public class PaymentCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentCompleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// update user expire_date
		HttpSession sessionUp = request.getSession(false);
		User u = (User)(sessionUp.getAttribute("user"));
		int result = new UserService().buyVoucher(u);
		if(result>0) {
			
			
			// sending e-mail
			String personalId = request.getParameter("personalId");
			String merchantId = request.getParameter("merchantId");
			String paidAmt = request.getParameter("amount");
			String verifyNo = request.getParameter("verifyNo");
			
			result= new UserService().voucherUpdate(personalId,u.getUserId());
			if(result>0) {
				
				User update = new UserService().selectUser(u);
				sessionUp.setAttribute("user", update);			
				//2. 메일 설정
				Properties prop = System.getProperties();
				prop.put("mail.smtp.host", "smtp.gmail.com");
				prop.put("mail.smtp.port", 465);
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.ssl.enable", "true");
				prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
				
				Session session = Session.getDefaultInstance(prop,new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("kairos.purchase@gmail.com","khlove123");//비밀번호 입력
					}
				});
				MimeMessage msg = new MimeMessage(session);
				try {
					msg.setSentDate(new Date());
					msg.setFrom(new InternetAddress(update.getEmail(),"kairos.purchase@gmail.com"));
					InternetAddress to = new InternetAddress(update.getEmail());
					msg.setRecipient(Message.RecipientType.TO, to);
					msg.setSubject("결제완료 이메일","UTF-8");
					
					msg.setContent("<table><thead><h1>결제완료</h1></thead><tbody><tr><th>인증번호</th><td>"+personalId+"</td></tr><tr><th>상점거래 아이디</th><td>"+merchantId+"</td></tr><tr><th>결제 금액</th><td>"+paidAmt+"</td></tr><tr><th>카드 승인 번호</th><td>"+verifyNo+"</td></tr></tbody></table>","text/html;charset=UTF-8");
					Transport.send(msg);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher("/").forward(request, response);
			}
		}else {
			request.setAttribute("loc", "/");
			request.setAttribute("msg", "결제 실패");
			request.getRequestDispatcher("/WEB-INF/view/common/msg.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
