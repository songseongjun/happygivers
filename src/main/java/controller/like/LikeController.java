package controller.like;

import domain.Like;
import domain.Member;
import service.LikeService;
import util.JsonRespUtil;
import util.ParamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/api/like")
public class LikeController extends HttpServlet {

    private LikeService service = new LikeService();

    // 좋아요 수 조회
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bnoStr = req.getParameter("bno");
        String rnoStr = req.getParameter("rno");

        int count = 0;
        if (bnoStr != null) {
            count = service.countByBno(Long.valueOf(bnoStr));
        } else if (rnoStr != null) {
            count = service.countByRno(Long.valueOf(rnoStr));
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(count);
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Member member = null;
        if(session != null && session.getAttribute("member") != null) {
            member = (Member) session.getAttribute("member");
        }

        Like like = ParamUtil.get(req, Like.class);

        boolean liked = service.toggleLike(like);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        JsonRespUtil.writeJson(resp,Map.of("liked", liked));

    }
}
