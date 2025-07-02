package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import lombok.extern.slf4j.Slf4j;
import service.MemberService;

@WebServlet("/member/logout")
@Slf4j
public class Logout extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false); //현재세션가져오기
		if(session !=null) {
			session.invalidate();
		}
//		req.getSession().invalidate();
		
//		session.setAttribute("loginUser", member);//로그인한 사용자 객체 저장
		resp.sendRedirect(req.getContextPath()+ "/index");
	}


}
