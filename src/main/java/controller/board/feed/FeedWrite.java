package controller.board.feed;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Board;
import domain.Donate;
import domain.DonateRound;
import domain.Member;
import domain.en.Mtype;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.AlertUtil;
import util.ParamUtil;

@Slf4j
@WebServlet("/feed/write")
public class FeedWrite extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 권한 확인		
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");

		if(member == null || !(member.getStatus().equals(Status.ACTIVE))) {
			AlertUtil.alert("회원가입이 완료된 회원만 글을 작성하실 수 있습니다.", "/feed/list", req, resp);
		}
		
		req.getRequestDispatcher("/WEB-INF/views/board/feed/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 권한 확인		
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");

		if(member == null || !(member.getStatus().equals(Status.ACTIVE))) {
			AlertUtil.alert("회원가입이 완료된 회원만 글을 작성하실 수 있습니다.", "/feed/list", req, resp);
		}
		
		// board 인스턴스 생성	
		Board board = ParamUtil.get(req, Board.class); 
		log.info("{}", board);

		BoardService boardService = new BoardService();
			
		boardService.write(board);
			
		AlertUtil.alert("글이 등록되었습니다.", "/feed/list", req, resp);
	}
}
