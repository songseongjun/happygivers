package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import service.MemberService;

@WebServlet("/mypage/profile")
public class MypageProfile extends HttpServlet {
	//로그인후 프로필 수정 눌렀을때 연결됨
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    														//프로필jsp만들고 여기고치면 될듯
	    req.getRequestDispatcher("/WEB-INF/views/member/mypage/profile.jsp").forward(req, resp);
	}

		
}
