package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.Member;
import lombok.extern.slf4j.Slf4j;
import service.MemberService;
import util.ParamUtil;


@WebServlet("/member/register/user")
@Slf4j
public class RegisterUser extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/registeruser.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = ParamUtil.get(req, Member.class);		
		
		new MemberService().register(member);
			resp.sendRedirect("../index");
	
	}	
	}
	

