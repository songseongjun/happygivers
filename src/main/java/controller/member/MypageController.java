package controller.member;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import domain.Member;

@WebServlet("/mypage")
public class MypageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        // 세션에서 로그인 회원 가져오기 (없으면 로그인페이지 이동)
        Member member = (Member) req.getSession().getAttribute("member");
        if (member == null) {
            resp.sendRedirect(req.getContextPath() + "/member/updateinfo");
            return;
        }
        
        req.setAttribute("cp", req.getContextPath());
        
        // mypage.jsp로 이동
        req.setAttribute("member", member);
        req.getRequestDispatcher("/WEB-INF/views/member/realmypage.jsp").forward(req, resp);
    }
}