package emailsend;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import mapper.EmailCheckMapper;
import mapper.MemberMapper;
import service.EmailCheckService;
import util.MailUtil;
import util.MybatisUtil;
      //이메일 인증 요청 처리 서블릿!
@WebServlet("/email/send")
public class EmailSendServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email"); // 사용자가 입력한 이메일
        String uuid = UUID.randomUUID().toString(); // 인증에 사용할 고유 UUID
        LocalDateTime voiddate = LocalDateTime.now().plusMinutes(5); // 5분 후 만료시간

        EmailCheckService service = new EmailCheckService(); // 인증정보 저장용 서비스
        service.save(email, uuid, voiddate); // DB에 이메일+UUID+만료시간 저장

        // 이메일 본문에 들어갈 링크 생성
        String link = "http://localhost:8080/happygivers/email/verify?uuid=" + uuid;

        //  HTML 형식으로 메일 전송
        MailUtil.sendEmail(email, "HappyGivers 인증메일", link);

        resp.getWriter().write("success"); // 클라이언트 응답 (AJAX 등에서 받을 수 있음)
    }
}





