package controller.mypage.donate;

import domain.DonateAction;
import domain.Member;
import domain.Pay;
import service.DonateService;
import service.PayService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/mypage/action/list")
public class ActionList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DonateService donateService = new DonateService();
        List<DonateAction> actions = null;
        HttpSession session = req.getSession(false);
        Member member = null;
        if(session != null && session.getAttribute("member") != null) {
            member = (Member) session.getAttribute("member");
            actions = donateService.myActionList(member.getMno());
        }

        req.setAttribute("actions", actions);
        req.getRequestDispatcher("/WEB-INF/views/member/mypage/actionlist.jsp").forward(req,resp);
    }
}
