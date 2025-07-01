package emailsend;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import domain.EmailCheck;
import mapper.EmailCheckMapper;
import service.EmailCheckService;
import util.MybatisUtil;

@WebServlet("/email/verify")     //인증링크클릭처리
public class EmailVerifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uuid = req.getParameter("uuid");//URL에서 uuid파라미터추출
        EmailCheckService service = new EmailCheckService();//서비스객체
        boolean result = service.verifyUUID(uuid);//uuid검증하고 인증처리함
        resp.setContentType("text/html; charset=utf-8");
        if (result) {
            resp.getWriter().write("이메일 인증 완료!");
        } else {
            resp.getWriter().write("인증 실패 또는 유효시간 초과");
        }
      
    }
}

