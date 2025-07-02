package controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import service.MemberService;

@WebServlet("/member/find-id")
public class FindID extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.getRequestDispatcher("/WEB-INF/views/member/findId.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		
		List<Member> result = new MemberService().findIdsByEmailAndName(email, name);
		
		req.setAttribute("memberList", result);
		req.getRequestDispatcher("/WEB-INF/views/member/findIdResult.jsp").forward(req, resp);
		System.out.println("아이디 찾기 요청:" + email +"/" +name);
	}
	
}
