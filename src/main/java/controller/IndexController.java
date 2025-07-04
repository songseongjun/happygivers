package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import service.DonateService;

@WebServlet("/index")
public class IndexController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = null;
		DonateService donateService = new DonateService();
		long totalAmount = donateService.findTotalAmount();
		int myTotalAmount = 0;
			
		HttpSession session = req.getSession(false);
		
		if(session != null && session.getAttribute("member") != null) {
			member = (Member) session.getAttribute("member");
			myTotalAmount = donateService.findMyTotalAmount(member.getMno());
		}
		
		req.setAttribute("totalAmount", totalAmount);
		req.setAttribute("myTotalAmount", myTotalAmount);
		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
	}
	
} 
