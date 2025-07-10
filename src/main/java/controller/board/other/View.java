package controller.board.other;

import domain.Board;
import domain.Member;
import domain.dto.Criteria;
import domain.en.Ctype;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import service.DonateService;
import util.AlertUtil;
import util.ParamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Slf4j
@WebServlet(urlPatterns = {"/notice/view", "/qna/view"})
public class View extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("bno") == null) {
			AlertUtil.alert("잘못된 접근입니다.", "/board/list", req, resp);
			return;
		}
		String path = req.getServletPath();

		Criteria cri = ParamUtil.get(req, Criteria.class);
		if (cri == null) {
			cri = new Criteria();
		}

		List<Board> boards = null;
		// 기부 게시글 리스트 처리
		if ("/notice/view".equals(path)) {
			cri.setCtype(Ctype.NOTICE);


		}
		else if ("/qna/view".equals(path)) {
			cri.setCtype(Ctype.QNA);


		}

		BoardService service = new BoardService();

		Board board = service.findByBno(Long.valueOf(req.getParameter("bno")));










		req.setAttribute("cri", cri);
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/views/board/other/view.jsp").forward(req, resp);
	}
}
