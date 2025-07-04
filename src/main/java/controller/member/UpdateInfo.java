package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import service.MemberService;

@WebServlet("/member/update-info")
public class UpdateInfo extends HttpServlet {

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        // 1. 요청한 글자 인코딩을 UTF-8로 설정 (한글 처리 깨지지 않게)
	        req.setCharacterEncoding("UTF-8");

	        // 2. 로그인한 회원 정보를 세션에서 꺼냄
	        Member member = (Member) req.getSession().getAttribute("member");

	        // 3. 입력 폼에서 받은 값을 파라미터로 가져옴 (form name과 일치해야 함)
	        String name = req.getParameter("name");
	        String nickname = req.getParameter("nickname");
	        String tel = req.getParameter("tel");
	        String location = req.getParameter("location");

	        // 4. 받아온 값으로 member 객체 값 설정 (세션 + DB 동시에 수정할 목적)
	        member.setName(name);
	        member.setNickname(nickname);
	        member.setTel(tel);
	        member.setLocation(location);

	        // 5. Service 호출해서 DB에 반영
	        MemberService service = new MemberService();
	        service.update(member);

	        // 6. 세션 정보도 업데이트 (화면에 최신 정보 반영)
	        req.getSession().setAttribute("member", member);

	        // 7. 마이페이지로 다시 리다이렉트
	        resp.sendRedirect("/member/mypage.jsp");
	    }
	}
