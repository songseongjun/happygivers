package controller.mypage.board;

import domain.Board;
import domain.Member;
import domain.Reply;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import service.ReplyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
@Slf4j
@WebServlet("/mypage/reply/list")
public class ReplyList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReplyService service = new ReplyService();
        List<Reply> replys = null;
        HttpSession session = req.getSession(false);
        Member member = null;
        if(session != null && session.getAttribute("member") != null) {
            member = (Member) session.getAttribute("member");
            replys = service.myReplys(member.getMno());
        }
        req.setAttribute("replys", replys);
        req.getRequestDispatcher("/WEB-INF/views/member/mypage/replylist.jsp").forward(req,resp);
    }
}
