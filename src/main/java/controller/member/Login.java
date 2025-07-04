package controller.member;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import service.MemberService;
import util.AlertUtil;
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
		Member member = ParamUtil.get(req, Member.class);
		log.info("{} {}",id,pw);
		boolean ret = new MemberService().login(member.getId(), member.getPw(), member.getMtype());

		log.info("{}",ret);
		if (ret) { // 로그인
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(60 * 60);// 10분뒤에 로그아웃되는상황만든것
			Member loginMember = new MemberService().findById(req.getParameter("id")); 
			session.setAttribute("member", loginMember);
			if(!loginMember.isEmailcheck()) {
				AlertUtil.alert("이메일 미인증 상태입니다. 인증을 완료해주세요.", "/mypage", req, resp);
				return;
			}
			String url = req.getParameter("url");
			if (url == null) {
				resp.sendRedirect(req.getContextPath() + "/index");
			} else {
				String decodedUrl = URLDecoder.decode(url, "utf-8");
				Criteria cri = Criteria.init(req);
				resp.sendRedirect(decodedUrl + "?" + cri.getQs2());
			}

		} else { // 로그인실패
			resp.sendRedirect("login?msg=fail"); 

		}
		
	}

}
