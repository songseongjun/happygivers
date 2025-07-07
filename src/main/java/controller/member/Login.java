package controller.member;

import java.io.IOException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import domain.AutoLogin;
import domain.Member;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import mapper.AutoLoginMapper;
import service.MemberService;
import util.AlertUtil;
import util.MybatisUtil;
import util.ParamUtil;

@WebServlet("/member/login")
@Slf4j
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mtype", req.getParameter("mtype"));
        req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        String mtypeStr = req.getParameter("mtype");

        Member member = ParamUtil.get(req, Member.class);

        if (mtypeStr == null) {
            resp.sendRedirect("login?msg=missingtype");
            return;
        }

        try {
            domain.en.Mtype mtype = domain.en.Mtype.valueOf(mtypeStr);
            boolean ret = new MemberService().login(id, pw, mtype);

            if (ret) {
                HttpSession session = req.getSession();
                session.setMaxInactiveInterval(60 * 60); // 1시간 유지

                Member loginMember = new MemberService().findById(id);
                session.setAttribute("member", loginMember);

                // 자동로그인 기능 (MyBatis + autologin 쿠키 방식)
                if (req.getParameter("autologin") != null) {
                    String token = UUID.randomUUID().toString();
                    LocalDateTime expireDate = LocalDateTime.now().plusDays(7);

                    Cookie cookie = new Cookie("autologin", token);
                    cookie.setPath("/");
                    cookie.setMaxAge(60 * 60 * 24 * 7);
                    resp.addCookie(cookie);

                    AutoLogin al = AutoLogin.builder()
                            .mno(loginMember.getMno())
                            .token(token)
                            .voiddate(expireDate)
                            .build();

                    try (var session2 = MybatisUtil.getSqlSession()) {
                        session2.getMapper(AutoLoginMapper.class).insert(al);
                        session2.commit();
                    }
                }

                //  이메일 미인증 상태 확인
                if (!loginMember.isEmailcheck()) {
                    AlertUtil.alert("이메일 미인증 상태입니다. 인증을 완료해주세요.", "/mypage", req, resp);
                    return;
                }

                //  리디렉션 URL 처리
                String url = req.getParameter("url");
                if (url == null) {
                    resp.sendRedirect(req.getContextPath() + "/index");
                } else {
                    String decodedUrl = URLDecoder.decode(url, "utf-8");
                    Criteria cri = Criteria.init(req);
                    resp.sendRedirect(decodedUrl + "?" + cri.getQs2());
                }
            } else {
                log.warn("로그인 실패 - 아이디: {}, 타입: {}", id, mtypeStr);
                resp.sendRedirect("login?msg=fail");
            }
        } catch (IllegalArgumentException e) {
            log.warn("mtype 변환 실패 - 잘못된 mtype: {}", mtypeStr);
            resp.sendRedirect("login?msg=invalidtype");
        }
    }
}
