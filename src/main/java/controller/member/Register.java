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

@WebServlet("/member/register")
@Slf4j
public class Register extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mtype = req.getParameter("mtype");
		Member member = ParamUtil.get(req, Member.class);

		log.info("{}", member);
		new MemberService().register(member);
		resp.sendRedirect("../index");
//		resp.sendRedirect(req.getContextPath() + "/index");
	}
}
