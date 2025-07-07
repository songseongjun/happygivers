package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import domain.Member;
import lombok.extern.slf4j.Slf4j;
import mapper.AutoLoginMapper;
import util.MybatisUtil;

@WebServlet("/member/logout")
@Slf4j
public class Logout extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session = req.getSession(false); // 현재 세션 가져오기

	if (session != null) {

//자동 로그인 쿠키 삭제
		Cookie cookie = new Cookie("autologin", null);
		cookie.setMaxAge(0); // 쿠키 즉시 삭제
		cookie.setPath("/");
		resp.addCookie(cookie);

//DB에서 자동 로그인 토큰 삭제
		Member member = (Member) session.getAttribute("member");
		if (member != null) {
			try (var session2 = MybatisUtil.getSqlSession()) {
				session2.getMapper(AutoLoginMapper.class).delete(member.getMno());
				session2.commit();
			}
		}

//세션 무효화
		session.invalidate();
	}

//메인 페이지로 리디렉션
	resp.sendRedirect(req.getContextPath() + "/index");
}
}