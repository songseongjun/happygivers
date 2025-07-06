package controller.admin.donate;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.dto.Criteria;
import service.BoardService;
import util.ParamUtil;

@WebServlet("/admin/donate/roundlist")
public class DonateRoundList extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardService service = new BoardService();
		Criteria cri = ParamUtil.get(req, Criteria.class);
		List<Board> boards = service.list(cri);
		Integer cno = null;
		if(cri.getCno() != null) {
			cno = cri.getCno();
		}
		
		req.setAttribute("cno", cno);
		req.setAttribute("boards", boards);
		req.getRequestDispatcher("/WEB-INF/views/admin/donate/roundlist.jsp").forward(req, resp);
	}
	
}
