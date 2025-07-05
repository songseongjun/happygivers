package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Board;
import domain.Member;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import service.DonateService;
@Slf4j
@WebServlet("/index")
public class IndexController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = null;
		DonateService donateService = new DonateService();
		long totalAmount = donateService.findTotalAmount();
		long myTotalAmount = 0;
			
		HttpSession session = req.getSession(false);
		
		if(session != null && session.getAttribute("member") != null) {
			member = (Member) session.getAttribute("member");
			myTotalAmount = donateService.findMyTotalAmount(member.getMno());
		}
		BoardService boardService = new BoardService();
		Board deadlineBoard = boardService.findByDeadline();

		
		List<Board> newBoards = boardService.findNewList();
		
		
		
		
		req.setAttribute("newBoards", newBoards);
		req.setAttribute("deadlineBoard", deadlineBoard);
		req.setAttribute("totalAmount", totalAmount);
		req.setAttribute("myTotalAmount", myTotalAmount);
		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
	}
	
} 
