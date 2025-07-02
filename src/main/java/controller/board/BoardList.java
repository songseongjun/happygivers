package controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.dto.Criteria;
import domain.dto.PageDto;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.ParamUtil;


@Slf4j
@WebServlet("/board/list")
public class BoardList extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardService boardService = new BoardService();
		Criteria cri = ParamUtil.get(req, Criteria.class);
		if (cri == null) {
		    cri = new Criteria();
		}
		
		cri.setStatus(Status.ACTIVE);
			
		
		log.info("{}", cri);

		List<Board> boards = boardService.list(cri);
		for (Board b : boards) {
			b.setThumbnail(boardService.findThumbnail(b.getContent()));
			b.setRound(boardService.findRound(b.getDrno()));
			b.setCname(boardService.findCname(b.getCno()));
			b.setName(boardService.findname(b.getMno()));
		}
		
		req.setAttribute("pageDto", new PageDto(cri, boardService.getCount(cri)));
		log.info("{}", req.getAttribute("pageDto"));
		req.setAttribute("boards", boards);
		req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req, resp);
	}
}
