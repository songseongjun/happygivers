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

import com.google.gson.Gson;

import domain.Board;
import domain.Reply;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import service.ReplyService;


@Slf4j
@WebServlet("/api/feed/view")
public class FeedServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long bno = Long.parseLong(req.getParameter("bno"));
		BoardService service = new BoardService();
		Board board = service.findByBno(bno);
		
		if (board == null) {
	      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
	      return;
	    }
		ReplyService replyService = new ReplyService();
		List<Reply> replys = replyService.list(bno, null);
		log.info("{}", replys);
		
		Map<String, Object> data = Map.of(
				  "board", board,
				  "replys", replys
				);
		
		
		
		resp.setContentType("appliction/json; charset=UTF-8");
		new Gson().toJson(data, resp.getWriter());
	}
	
}
