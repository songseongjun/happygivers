package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.DonateRound;
import domain.dto.Criteria;
import domain.dto.PageDto;
import mapper.DonateMapper;
import service.BoardService;
import service.DonateService;
import util.AlertUtil;
import util.ParamUtil;



@WebServlet("/board/view")
public class View extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("bno") == null) {
			AlertUtil.alert("잘못된 접근입니다.", "/board/list", req, resp);
			return;
		}
		
		Criteria cri = Criteria.init(req);
		BoardService service = new BoardService();
		
		Board board = service.findByBno(ParamUtil.get(req, Board.class).getBno());

		board.setRound(service.findRound(board.getDrno()));
		board.setCname(service.findCname(board.getCno()));
		board.setName(service.findName(board.getMno()));
		int replyCount = service.getReplyCount(board.getBno()); 
		
		
		req.setAttribute("cri", cri);
		req.setAttribute("board", board);
		req.setAttribute("replyCount", replyCount);
		req.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(req, resp);
	}
}
