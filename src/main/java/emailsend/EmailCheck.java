package emailsend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import mapper.MemberMapper;
import service.EmailCheckService;
import util.MybatisUtil;

@WebServlet("/member/email-check")
public class EmailCheck extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. URL 파라미터에서 uuid 추출
        String uuid = req.getParameter("uuid");

        // 2. uuid가 비어있으면 잘못된 요청 처리
        if (uuid == null || uuid.trim().isEmpty()) {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println("<script>alert('잘못된 접근입니다. 인증 정보가 없습니다.'); location.href='/index.jsp';</script>");
            return;
        }

        // 3. Redis에 저장된 인증 토큰 확인
        EmailCheckService service = new EmailCheckService();
        boolean isValid = service.isVerified(uuid);

        if (isValid) {
            // 4. Redis에서 이메일 꺼냄
            String email = service.getEmail(uuid);

            // 5. 인증된 이메일 DB 상태 업데이트
            MemberMapper mapper = MybatisUtil.getSqlSession().getMapper(MemberMapper.class);
            mapper.updateEmailVerifiedByEmail(email);  // tbl_member 테이블 업데이트
            mapper.verifyEmail(uuid);                  // tbl_email_check 테이블 업데이트
            MybatisUtil.getSqlSession().commit();      // 커밋

            // 6. Redis에서 uuid 토큰 삭제 (1회용 인증)
            service.removeToken(uuid);

            // 7. 로그인 세션 부여
            Member member = mapper.selectByEmail(email);
            req.getSession().setAttribute("member", member);
            	
            // 8. index.jsp로 이동
            resp.sendRedirect("../index");
            
        } else {
            // 9. 인증 실패: 토큰 없거나 만료됨
            req.setAttribute("message", "이메일 인증이 만료되었거나 잘못된 요청입니다.");
            req.getRequestDispatcher("/WEB-INF/views/member/verifyFail.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // POST 요청 대비
   
    	
        resp.setContentType("text/plain; charset=UTF-8");
        resp.getWriter().write("이 요청은 GET 방식으로만 처리 가능합니다.");
    }
}
