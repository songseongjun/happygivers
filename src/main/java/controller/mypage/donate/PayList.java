package controller.mypage.donate;

import domain.Board;
import domain.DonateAction;
import domain.Member;
import domain.Pay;
import service.PayService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mypage/pay/list")
public class PayList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PayService payService = new PayService();
        List<Pay> pays = null;
        HttpSession session = req.getSession(false);
        Member member = null;

        if(session != null && session.getAttribute("member") != null) {
            member = (Member) session.getAttribute("member");
            pays = payService.findByMno(member.getMno());
        }

        req.setAttribute("pays", pays);
        req.getRequestDispatcher("/WEB-INF/views/member/mypage/paylist.jsp").forward(req,resp);
    }
}
