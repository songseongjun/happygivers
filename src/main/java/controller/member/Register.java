package controller.member;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import lombok.extern.slf4j.Slf4j;
import service.EmailCheckService;
import service.MemberService;
import util.MailUtil;
import util.ParamUtil;

@WebServlet("/member/register")
@Slf4j
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 회원가입 페이지로 이동
        req.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //요청 파라미터를 Member 객체로 변환
        Member member = ParamUtil.get(req, Member.class);
        log.info("{}", member);

        //이름이 null이면 기본값 설정
        if (member.getName() == null) {
            member.setName("이름없음");
        }
        
        //mtype에 따른 닉네임 처리 추가
        String mtype = req.getParameter("mtype");

        if ("USER".equalsIgnoreCase(mtype)) {
            // 일반회원 > 닉네임 자동 생성
            if(member.getNickname() == null || member.getNickname().trim().isEmpty()) {
            	String rand = String.valueOf((int)(Math.random() * 900000) + 100000);
            	member.setNickname("기부천사" + rand);
            }
            
        } else if ("ORG".equalsIgnoreCase(mtype)) {
            // 기관회원 >기관명 필수
            String orgname = req.getParameter("orgname");
            if (orgname == null || orgname.trim().isEmpty()) {
                resp.setContentType("text/html; charset=UTF-8");
                resp.getWriter().println(
                    "<script>" +
                        "alert('기관명은 필수입니다.');" +
                        "history.back();" +
                    "</script>"
                );
                return;
            }
            member.setName(orgname); // 기관명 > 네임으로 저장
        }
        
        
        

        // 아이디 및 이메일 중복 확인
        MemberService memberService = new MemberService();

        if (memberService.findById(member.getId()) != null) {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println(
                "<script>" +
                    "alert('이미 사용 중인 아이디입니다. 다른 아이디를 입력해주세요.');" +
                    "history.back();" +
                "</script>"
            );
            return;
        }

        if (memberService.findByEmail(member.getEmail()) != null) {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println(
                "<script>" +
                    "alert('이미 사용 중인 이메일입니다. 로그인하거나 다른 이메일을 사용해주세요.');" +
                    "history.back();" +
                "</script>"
            );
            return;
        }
        
        //프로필자동부여
        if (member.getProfile() == null || member.getProfile().trim().isEmpty()) {
            member.setProfile("https://i.ibb.co/NghYQGR7/images-1.png");
        }

        //  회원 정보 등록 (DB 저장)
        memberService.register(member);

        // 이메일 인증을 위한 UUID 생성 및 Redis 저장
        String uuid = UUID.randomUUID().toString();
        EmailCheckService service = new EmailCheckService();
        service.saveToken(uuid, member.getEmail());

        //인증 링크 생성
        String authLink = req.getRequestURL().toString().replace("register", "email-check") + "?uuid=" + uuid;

        //  인증 메일 HTML 작성
        String html = "<h3>아래 링크를 클릭하여 인증을 완료해주세요</h3>" +
                      "<a href='" + authLink + "' target='_blank'>" + authLink + "</a>";

        //  메일 전송
        MailUtil.sendEmail(member.getEmail(), "Happygivers 이메일 인증", html);

        // 성공 메시지 + 메인페이지 이동
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println(
            "<script>" +
                "alert('인증메일이 발송되었습니다. 5분 안에 인증하지 않으면 마이페이지에서 다시 인증해야 합니다.');" +
                "location.href='" + req.getContextPath() + "/index';" +
            "</script>"
        );
    }
}
