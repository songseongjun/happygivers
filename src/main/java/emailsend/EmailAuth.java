package emailsend;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmailCheckService;
import util.MailUtil;

@WebServlet("/member/email-auth")
public class EmailAuth extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 이메일 파라미터 가져오기
        String email = req.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println("<script>alert('이메일이 전달되지 않았습니다.'); history.back();</script>");
            return;
        }

        // 2. UUID 생성 및 Redis 저장 (5분 유효)
        String uuid = UUID.randomUUID().toString();
        new EmailCheckService().saveToken(uuid, email);

        // 3. 인증 링크 생성
        String authLink = req.getRequestURL().toString().replace("email-auth", "email-check") + "?uuid=" + uuid;

        // 4. 메일 발송 (html 형식 내용)
        String html = "<h3>아래 링크를 클릭하여 인증을 완료해주세요</h3>" +
                      "<a href='" + authLink + "' target='_blank'>" + authLink + "</a>";
        MailUtil.sendEmail(email, "Happygivers 이메일 재인증", html);

        // 5. 사용자에게 alert로 안내 후 마이페이지로 이동
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(
            "<script>" +
                "alert('인증 메일이 재발송되었습니다. 5분 안에 클릭해주세요.');" +
                "location.href='/mypage.jsp';" +
            "</script>"
        );
    }
}
