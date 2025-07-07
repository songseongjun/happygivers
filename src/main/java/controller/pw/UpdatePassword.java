package controller.pw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import service.MemberService;
import util.PasswordEncoder;

@WebServlet("/mypage/updatepw")
public class UpdatePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
        request.getRequestDispatcher("/WEB-INF/views/member/mypage/updatepw.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Member member = (Member) request.getSession().getAttribute("member");
        if (member == null) {
            response.sendRedirect(request.getContextPath() + "/member/login");
            return;
        }

     
        String currentPw = request.getParameter("currentPw"); 
        String newPw = request.getParameter("newPw");
        String newPw2 = request.getParameter("newPw2");

        //
        boolean pwMatch = PasswordEncoder.matches(currentPw, member.getPw());

        // 
        if (!pwMatch || !newPw.equals(newPw2)) {
            response.sendRedirect(request.getContextPath() + "/mypage/updatepw?error=1");
            return;
        }

        // 
        MemberService service = new MemberService();
        service.updatePassword(member.getId(), newPw);

        //
        response.sendRedirect(request.getContextPath() + "/mypage/updatepw?success=1");
    }
    }

