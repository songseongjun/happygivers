package controller.board.feed;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import domain.Board;
import domain.Member;
import domain.Reply;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import service.MemberService;
import service.ReplyService;


@Slf4j
@WebServlet("/api/feed/view")
public class FeedServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long bno = Long.parseLong(req.getParameter("bno"));
		BoardService service = new BoardService();
		Board board = service.findByBno(bno);
		HttpSession session = req.getSession(false);
		Member member = (session != null && session.getAttribute("member") != null) ? (Member) session.getAttribute("member") : null;
		Long mno = (member != null) ? member.getMno() : null;
		if (board == null) {
	      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
	      return;
	    }
		ReplyService replyService = new ReplyService();
		List<Reply> replys = replyService.list(bno, mno, null);
		log.info("{}", replys);
		MemberService memberService = new MemberService();
		String profile = memberService.findByMno(board.getMno()).getProfile();

		board.setLiked(service.checkBoardLiked(board.getBno(), mno));

		Map<String, Object> data = Map.of(
				  "board", board,
				  "replys", replys,
				"profile", profile
				);
		
		
		
		resp.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(data, resp.getWriter());
	}
	
}
