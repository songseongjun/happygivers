package controller.admin.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.DonateRound;
import domain.dto.Criteria;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.AlertUtil;
import util.ParamUtil;

@Slf4j
@WebServlet("/admin/board/orgboardstatuschange")
public class OrgBoardStatusChange extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardService service = new BoardService();
		//파라미터 받아오기
		Board tmp = ParamUtil.get(req, Board.class);
		Board board = service.findByBno(tmp.getBno());
		board.setStatus(tmp.getStatus());
		
		
		log.info("{}", board);
		service.modify(board);
		
		
		
		AlertUtil.alert("[ " + board.getBno() + "번 ] 글의 상태가 변경되었습니다.", true, req, resp);
	}
}
