package controller.member;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import domain.en.Mtype;
import emailsend.EmailCheck;
import jakarta.mail.Session;
import lombok.extern.slf4j.Slf4j;
import service.EmailCheckService;
import service.MemberService;
import util.MailUtil;
import util.ParamUtil;
import util.RedisUtil;

@WebServlet("/member/register")
@Slf4j
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member member =ParamUtil.get(req, Member.class);//나머지 회원 정보 추출
        log.info("{}", member);


        // 1. 가입을 시켜야함
        new MemberService().register(member);
        EmailCheckService service = new EmailCheckService();
        String uuid = UUID.randomUUID().toString();
        service.saveToken(uuid, member.getEmail());

        // 인증용 링크 생성 (이건 마이페이지 내 인증 확인 페이지로 연결될 예정)
        String authLink = req.getRequestURL().toString().replace("register", "email-check") + "?uuid=" + uuid;

        // 3. HTML 내용 만들기
        String html = "<h3>아래 링크를 클릭하여 인증을 완료해주세요</h3>" +
                      "<a href='" + authLink + "' target='_blank'>" + authLink + "</a>";

        // 4. 메일 전송
        MailUtil.sendEmail(member.getEmail(), "Happygivers 인증메일", html);
        // 메일 전송 로직 넣어도 되고, 지금은 Redis에만 저장한다고 가정
        
        
     // 회원가입 끝난 후 (DB 저장 성공했다고 가정)
        String email = req.getParameter("email");
    
        //  Redis에 uuid → email 저장 (5분 만료)
        RedisUtil.set(uuid, email, 300);

        // 4. 인증 메일 발송
        MailUtil.sendEmail(email, "Happygivers 이메일 인증", authLink);

        // 5. 사용자에게 알림 메시지 전달 후 인덱스로 이동
        resp.setContentType("text/html; charset=UTF-8");
        // 5. alert로 메세지 출력
        resp.getWriter().println("<script>alert('인증메일이 발송되었습니다. 5분 안에 인증하지 않으면 마이페이지 작성 시 인증을 다시 진행해야 합니다.'); location.href='/index.jsp';</script>");


     

        // 6. 인덱스로 보내기(로그인폼으로 보내기)

        // 2. 메일 발송

    }

}