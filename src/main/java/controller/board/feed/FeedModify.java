package controller.board.feed;

import domain.Board;
import domain.dto.Criteria;
import domain.dto.PageDto;
import domain.en.Ctype;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.ParamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/feed/modify")
public class FeedModify extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardService boardService = new BoardService();

		Board feed = boardService.findByBno(Long.valueOf(req.getParameter("bno")));

		req.setAttribute("feed" ,feed);
		req.getRequestDispatcher("/WEB-INF/views/board/feed/modify.jsp").forward(req, resp);
	}
	
}
