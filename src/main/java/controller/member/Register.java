package controller.member;

import java.io.IOException;

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
import util.ParamUtil;

@WebServlet("/member/register")
@Slf4j
public class Register extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   String email = req.getParameter("email"); // 입력된 이메일 추출

		    EmailCheckService service = new EmailCheckService();  // 서비스 인스턴스 생성
		    if (!service.isVerified(email)) {    // 인증 여부 확인
		        req.setAttribute("msg", "이메일 인증을 먼저 완료해주세요.");  // 인증이 안되었으면 메시지 저장
		        req.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(req, resp);// 회원가입 페이지로 되돌림
		        return;
		    }
		    
		//회원가입 타입 기관회원 유저회원
		String mtype = req.getParameter("mtype");
		Member member =ParamUtil.get(req, Member.class);//나머지 회원 정보 추출
		member.setMtype(Mtype.valueOf(mtype));

		log.info("회원가입 시도: {}", member);
		new MemberService().register(member);//회원 등록
		resp.sendRedirect("../index");// 메인 페이지로 리디렉션
//		resp.sendRedirect(req.getContextPath() + "/index");
	}
	
}
