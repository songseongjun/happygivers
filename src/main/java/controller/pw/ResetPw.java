package controller.pw;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import service.MemberService;
import util.RedisUtil;

@WebServlet("/member/resetpw")
public class ResetPw extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String uuid = req.getParameter("uuid");

	    // uuid로 Redis에서 id 조회
	    String id = RedisUtil.get(uuid);
	    if (id == null) {
	        resp.sendRedirect(req.getContextPath() + "/member/findpw.jsp?error=expired");
	        return;
	    }

	    // 임시 비밀번호 생성 (8자리)
	    String tempPw = UUID.randomUUID().toString().substring(0, 8);

	    // DB에 반영
	    new MemberService().updatePassword(id, tempPw);

	    // Redis삭제
	    RedisUtil.remove(uuid);

	    // JSP로임시 비번 넘김
	    req.setAttribute("tempPw", tempPw);
	    req.setAttribute("cp", req.getContextPath());
	    req.getRequestDispatcher("/WEB-INF/views/member/showtemppw.jsp").forward(req, resp);
	}


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
