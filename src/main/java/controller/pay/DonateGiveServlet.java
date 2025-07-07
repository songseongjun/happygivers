package controller.pay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import domain.Board;
import service.BoardService;
import util.JsonRespUtil;

@WebServlet("/api/donate")
public class DonateGiveServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> donate = new HashMap<String, Object>();
		BoardService boardService = new BoardService();
		// bno를 받아와서 board 객체 생성 - 썸네일, 모금회차 정보 받아옴
		Board board = boardService.findByBno(Long.valueOf(req.getParameter("bno")));

		
		
		donate.put("storeId", "store-f1ff113e-a12f-48a2-ad88-1a67d77bd7ad");
		donate.put("channelKey", "channel-key-52c3a9b7-5696-4767-8b91-0295776acbd8");
		donate.put("board", board);
		donate.put("currency", "CURRENCY_KRW");
		
	
        JsonRespUtil.writeJson(resp, donate);
	}
	
}
