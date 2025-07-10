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
import domain.en.Ctype;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.ParamUtil;


@Slf4j
@WebServlet(urlPatterns = {"/board/list", "/notice/list", "/qna/list"})
public class BoardList extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardService boardService = new BoardService();
		Criteria cri = ParamUtil.get(req, Criteria.class);
		if (cri == null) {
		    cri = new Criteria();
		}
		
		String path = req.getServletPath();
		cri.setStatus(Status.ACTIVE);

		List<Board> boards = null;
		// 기부 게시글 리스트 처리
		if ("/board/list".equals(path)) { 
			cri.setCtype(Ctype.DONATE);
			
			boards = boardService.list(cri);
			
			


		}
		// 공지 게시글 리스트 처리
		else if ("/notice/list".equals(path)) {
			cri.setCtype(Ctype.NOTICE);

			boards = boardService.list(cri);



		}
		// Q&A 게시글 리스트 처리
		else if ("/qna/list".equals(path)) {
			cri.setCtype(Ctype.QNA);

			boards = boardService.list(cri);


		}

		req.setAttribute("pageDto", new PageDto(cri, boardService.getCount(cri)));
		log.info("{}", req.getAttribute("pageDto"));
		req.setAttribute("boards", boards);
		log.info("{}", cri);
		req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req, resp);
	}
}
