package controller.admin.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;

@WebServlet("/admin/board/delete")
public class Remove extends HttpServlet{


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardService boardService = new BoardService();
		String[] bnos = req.getParameterValues("bno");
		if (bnos != null) {
		    for (String bno : bnos) {
		        boardService.remove(Long.valueOf(bno));
		    }
		}
	}
	
}
