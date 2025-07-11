package controller.mypage.board;

import domain.Board;
import domain.DonateAction;
import domain.Member;
import service.BoardService;
import service.DonateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/mypage/board/list")
public class BoardList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardService  boardService = new BoardService();
        List<Board> boards = null;
        HttpSession session = req.getSession(false);
        Member member = null;
        if(session != null && session.getAttribute("member") != null) {
            member = (Member) session.getAttribute("member");
            boards = boardService.myBoardList(member.getMno());
        }

        req.setAttribute("boards", boards);
        req.getRequestDispatcher("/WEB-INF/views/member/mypage/boardlist.jsp").forward(req,resp);
    }
}
