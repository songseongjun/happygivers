package controller.admin.board;

import service.BoardService;
import service.ReplyService;
import util.AlertUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/reply/delete")
public class RemoveReply extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("rno") == null){
			AlertUtil.alert("값 전달에 실패했습니다.", "/admin/reply/list", req, resp);
		}
		ReplyService service = new ReplyService();
		service.remove(Long.parseLong(req.getParameter("rno")));
		AlertUtil.alert("댓글 삭제 성공", "/admin/reply/list", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ReplyService service = new ReplyService();
		String[] rnos = req.getParameterValues("rno");
		if (rnos != null) {
		    for (String rno : rnos) {
		        service.remove(Long.valueOf(rno));
		    }
		}
	}
	
}
