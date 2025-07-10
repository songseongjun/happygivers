package controller.admin.board;

import domain.Board;
import domain.Reply;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import service.ReplyService;
import util.ParamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/admin/reply/list")
public class ReplyList extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ReplyService service = new ReplyService();

		

		
		List<Reply> replys = service.allList();

		
		req.setAttribute("replys", replys);
		req.getRequestDispatcher("/WEB-INF/views/admin/board/replylist.jsp").forward(req, resp);
	}

	
	
}
