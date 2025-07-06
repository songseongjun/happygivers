package controller.member;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import domain.Member;
import service.MemberService;
import util.MailUtil;
import util.RedisUtil;

@WebServlet("/member/send-reset-mail")
public class ResetMailSendController extends HttpServlet {
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        
        req.setCharacterEncoding("UTF-8");

     
        String id = req.getParameter("id");
        String email = req.getParameter("email");

    
        MemberService service = new MemberService();
        Member member = service.findById(id);

        if (member == null || !member.getEmail().equals(email)) {
         
            resp.sendRedirect(req.getContextPath() + "/member/findpw.jsp?error=1");
            return;
        }

     
        String uuid = UUID.randomUUID().toString();
        RedisUtil.set(uuid, id, 300); 

       
        String link = req.getRequestURL().toString().replace("send-reset-mail", "resetpw") 
                    + "?uuid=" + uuid;

        String subject = "[해피기버즈] 비밀번호 재설정 링크";
        String content = "비밀번호 재설정 링크입니다.\n아래 링크를 클릭하세요:\n" + link;

        MailUtil.sendEmail(email, subject, content);

    
        resp.sendRedirect(req.getContextPath() + "/member/findpw.jsp?sent=1");
    }
}
