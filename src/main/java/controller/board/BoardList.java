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
@WebServlet("/board/list")
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

		
		// 기부 게시글 리스트 처리
		if ("/board/list".equals(path)) { 
			cri.setCtype(Ctype.DONATE);
			
			List<Board> boards = boardService.list(cri);
			
			for (Board b : boards) {
				b.setThumbnail(boardService.findThumbnail(b.getContent())); // 썸네일 추출
				b.setRound(boardService.findRound(b.getDrno())); // 모금정보 추출
				b.setCname(boardService.findCname(b.getCno())); // 카테고리명 추출
				b.setName(boardService.findName(b.getMno())); // 작성자 이름 추출
			}
			
			
			req.setAttribute("pageDto", new PageDto(cri, boardService.getCount(cri)));
			log.info("{}", req.getAttribute("pageDto"));
			req.setAttribute("boards", boards);

		}
		// 피드 게시글 리스트 처리
		else if ("/feed/list".equals(path)) { 
			cri.setCtype(Ctype.FEED);
			
			List<Board> boards = boardService.list(cri);
			
			for (Board b : boards) {
				b.setThumbnail(boardService.findThumbnail(b.getContent())); // 썸네일 추출
				b.setNickname(boardService.findNickname(b.getMno())); // 작성자 닉네임
			}
			
			req.setAttribute("pageDto", new PageDto(cri, boardService.getCount(cri)));
			log.info("{}", req.getAttribute("pageDto"));
			req.setAttribute("boards", boards);
		}
		
		log.info("{}", cri);
		req.getRequestDispatcher("/WEB-INF/views" + path + ".jsp").forward(req, resp);
	}
}
