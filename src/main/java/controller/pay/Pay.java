package controller.pay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.DonateRound;
import service.BoardService;
import util.ParamUtil;
@WebServlet("/donate/pay")
public class Pay extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardService boardService = new BoardService();
		Board board = boardService.findByBno(Long.parseLong(req.getParameter("bno")));
		board.setRound(boardService.findRound(board.getDrno()));
		
		
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/views/pay/pay.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
