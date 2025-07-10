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
		BoardService boardService = new BoardService();
		long totalAmount = donateService.findTotalAmount();
		long myTotalAmount = 0;

		HttpSession session = req.getSession(false);

		List<Board> myDonates = null;
		if(session != null && session.getAttribute("member") != null) {
			member = (Member) session.getAttribute("member");
			myTotalAmount = donateService.findMyTotalAmount(member.getMno());
			myDonates = boardService.findMnoDonateList(member.getMno());
		}
		Board deadlineBoard = boardService.findByDeadline();


		List<Board> newBoards = boardService.findNewList();
		List<Board> notices = boardService.findNoticeList();
		List<Board> qnas = boardService.findQnaList();


		req.setAttribute("myDonates", myDonates);
		req.setAttribute("notices", notices);
		req.setAttribute("qnas", qnas);
		req.setAttribute("newBoards", newBoards);
		req.setAttribute("deadlineBoard", deadlineBoard);
		req.setAttribute("totalAmount", totalAmount);
		req.setAttribute("myTotalAmount", myTotalAmount);
		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
	}
	
} 
