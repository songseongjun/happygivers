package emailsend;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;

import service.EmailCheckService;
import util.JsonRespUtil;
import util.MailUtil;
import util.RedisUtil;


@WebServlet("/member/email-auth")
public class EmailAuth extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // 클라이언트로부터 이메일을 전달받음
        String email = req.getParameter("email");

        // 인증용 UUID 생성 (랜덤한 문자열, 유일함)
        String uuid = UUID.randomUUID().toString();

        // Redis에 uuid → email 로 저장 (10분 제한)
        new EmailCheckService().saveToken(uuid, email);
        
     // 링크 생성
        String authLink = req.getRequestURL().toString().replace("email-auth", "email-check") + "?uuid=" + uuid;

        // HTML 내용 만들기
        String html = "<h3>아래 링크를 클릭하여 인증을 완료해주세요</h3>" +
                      "<a href='" + authLink + "' target='_blank'>" + authLink + "</a>";

        // 메일 전송
        MailUtil.sendEmail(email, "Happygivers 인증메일", html);
        // 메일 전송 로직 넣어도 되고, 지금은 Redis에만 저장한다고 가정
        resp.getWriter().println("인증 메일이 발송되었습니다.");
	}
}