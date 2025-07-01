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
import lombok.extern.slf4j.Slf4j;
import service.BoardService;


@Slf4j
@WebServlet("/board/list")
public class BoardList extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardService boardService = new BoardService();
		Criteria cri = Criteria.init(req);
		
		List<Board> boards = boardService.list(cri);
		for (Board b : boards) {
			b.setThumbnail(boardService.findThumbnail(b.getContent()));
			b.setRound(boardService.findRound(b.getDrno()));
			log.info(b.getThumbnail());
		}
		
		req.setAttribute("pageDto", new PageDto(cri, boardService.getCount(cri)));
		req.setAttribute("boards", boards);
		req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req, resp);
	}
}
