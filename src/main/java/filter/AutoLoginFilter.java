package filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import domain.Member;
import mapper.AutoLoginMapper;
import service.MemberService;
import util.MybatisUtil;
import lombok.extern.slf4j.Slf4j;

@WebFilter("/*")
@Slf4j
public class AutoLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        log.info("[AutoLoginFilter] 세션 상태: {}", (session == null ? "없음" : "존재")); 

        if (session != null && session.getAttribute("member") != null) {
            log.info("[AutoLoginFilter] 이미 로그인된 상태입니다. 자동 로그인 생략"); 
            chain.doFilter(request, response);
            return;
        }

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            log.info("[AutoLoginFilter] 쿠키 검사 시작"); 
            for (Cookie c : cookies) {
                log.info(" [AutoLoginFilter] 쿠키: {} = {}", c.getName(), c.getValue()); 
                if ("autologin".equals(c.getName())) {
                    String token = c.getValue();
                    log.info("[AutoLoginFilter] autologin 토큰 발견: {}", token);

                    try (var session2 = MybatisUtil.getSqlSession()) {
                        Long mno = session2.getMapper(AutoLoginMapper.class).selectMnoByToken(token);

                        if (mno != null) {
                            log.info("[AutoLoginFilter] DB에서 해당 토큰으로 회원 조회 성공. mno = {}", mno);
                            Member member = new MemberService().findByMno(mno);
                            HttpSession newSession = req.getSession();
                            newSession.setAttribute("member", member);
                            newSession.setMaxInactiveInterval(60 * 60);
                            log.info("[AutoLoginFilter] 자동 로그인 완료! 세션에 member 등록"); 
                        } else {
                            log.warn("[AutoLoginFilter] 유효하지 않거나 만료된 토큰");  
                        }
                    }
                    break;
                }
            }
        } else {
            log.info("[AutoLoginFilter] 쿠키가 존재하지 않습니다."); 
        }

        chain.doFilter(request, response);
    }
}
