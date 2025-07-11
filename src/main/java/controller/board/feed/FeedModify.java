package controller.board.feed;

import domain.Attach;
import domain.Board;
import domain.Member;
import domain.dto.Criteria;
import domain.dto.PageDto;
import domain.en.Ctype;
import domain.en.Mtype;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.AlertUtil;
import util.ParamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/feed/modify")
public class FeedModify extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		Member member = (Member) session.getAttribute("member");

		BoardService boardService = new BoardService();

		Board feed = boardService.findByBno(Long.valueOf(req.getParameter("bno")));
		if(member.getMno() !=  feed.getMno() || member.getMtype() != Mtype.ADMIN) {
			AlertUtil.alert("접근 권한이 없습니다.", true, req, resp);
		}


		req.setAttribute("feed" ,feed);
		req.getRequestDispatcher("/WEB-INF/views/board/feed/modify.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Board board = ParamUtil.get(req, Board.class);
		Attach attach = null ;
		if(ParamUtil.get(req, Attach.class).getUuid() != null) {
			attach = ParamUtil.get(req, Attach.class);
			attach.setMno(null);
			board.setAttach(attach);
		}
		BoardService boardService = new BoardService();
		boardService.modify(board);
	}
}
