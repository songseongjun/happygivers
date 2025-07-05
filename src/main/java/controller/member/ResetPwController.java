package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import service.MemberService;
import util.RedisUtil;

@WebServlet("/member/resetpw")
public class ResetPwController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

     
        String uuid = req.getParameter("uuid");
        String pw = req.getParameter("pw");
        String pw2 = req.getParameter("pw2");

    
        if (!pw.equals(pw2)) {
         
            resp.sendRedirect(req.getContextPath() + "/member/resetpw.jsp?uuid=" + uuid + "&error=1");
            return;
        }

      
        String id = RedisUtil.get(uuid);
        if (id == null) {
          
            resp.sendRedirect(req.getContextPath() + "/member/findpw.jsp?error=expired");
            return;
        }

      
        new MemberService().updatePassword(id, pw);

        
        RedisUtil.remove(uuid);

   
        resp.sendRedirect(req.getContextPath() + "/member/login?reset=success");
    }
}
