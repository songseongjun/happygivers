package emailsend;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import mapper.MemberMapper;
import service.EmailCheckService;
import util.JsonRespUtil;
import util.MybatisUtil;
import util.RedisUtil;

@WebServlet("/member/email-check") // 이메일 인증 확인 링크로 들어오면 호출됨
public class EmailCheck extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. 사용자 브라우저에서 URL에 포함된 uuid 파라미터 추출
        String uuid = req.getParameter("uuid");

        // 2. uuid가 null 이거나 비어있으면 잘못된 요청 처리
        if (uuid == null || uuid.isEmpty()) {
            resp.setContentType("text/plain; charset=utf-8");
            resp.getWriter().write("잘못된 접근입니다. 인증값이 없습니다.");
            return;
        }

        //  3. Redis에 저장된 인증 토큰 확인 (서비스에서 처리)
        EmailCheckService service = new EmailCheckService();
        boolean result = service.isVerified(uuid);
        if (result) {
            String email = service.getEmail(uuid);
            service.removeToken(uuid);

            //  DB에 이메일 인증 완료 처리
            MemberMapper mapper = MybatisUtil.getSqlSession().getMapper(MemberMapper.class);
            mapper.updateEmailVerifiedByEmail(email);
            MybatisUtil.getSqlSession().commit();

            // 로그인 세션 자동 부여 (선택적)
            Member member = mapper.selectByEmail(email);
            req.getSession().setAttribute("member", member);

            //  index.jsp로 이동
            resp.sendRedirect("/index.jsp");

        } else {
            // 실패 시
            req.getRequestDispatcher("/WEB-INF/views/member/verifyFail.jsp").forward(req, resp);
        }

     
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // POST 요청이 올 일은 없지만 혹시 모를 호출 대비
        resp.setContentType("text/plain; charset=UTF-8");
        resp.getWriter().write("이 요청은 GET 방식으로만 처리 가능합니다.");
    }
}