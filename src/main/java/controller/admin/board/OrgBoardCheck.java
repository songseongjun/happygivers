package controller.admin.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.en.Status;
import service.BoardService;

@WebServlet("/admin/board/orgcheck")
public class OrgBoardCheck extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardService service = new BoardService();
		//파라미터 받아오기
		
		List<Board> boards = service.listOrgCheck(Status.valueOf(req.getParameter("status")));
		for(Board b : boards) {
			b.setRound(service.findRound(b.getDrno()));
		}
		
		req.setAttribute("boards", boards);
		req.getRequestDispatcher("/WEB-INF/views/admin/board/orgcheck.jsp").forward(req, resp);
	}
}
