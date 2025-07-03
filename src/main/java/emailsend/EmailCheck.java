package emailsend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import service.EmailCheckService;

@WebServlet("/member/email-check")
public class EmailCheck extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");

        if (uuid == null || uuid.trim().isEmpty()) {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println("<script>alert('잘못된 접근입니다. 인증 정보가 없습니다.'); location.href='/index.jsp';</script>");
            return;
        }

        EmailCheckService service = new EmailCheckService();

        if (service.isVerified(uuid)) {
        	  // 1. 먼저 email 꺼내기
            String email = service.getEmail(uuid);

            // 2. 인증 처리 (emailcheck = 1, check = 1, Redis 삭제)
            service.verifyEmail(uuid);

            // 3. 세션 등록
            Member member = service.getMemberByEmail(email);
            req.getSession().setAttribute("member", member);

            // 4. 메인 페이지 이동
            req.setAttribute("msg", "이메일 인증이 완료되었습니다!");
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "이메일 인증이 만료되었거나 잘못된 요청입니다.");
            req.getRequestDispatcher("/WEB-INF/views/member/verifyFail.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        resp.getWriter().write("이 요청은 GET 방식으로만 처리 가능합니다.");
    }
}
