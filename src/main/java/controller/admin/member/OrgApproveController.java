package controller.admin.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import service.AdminService;

@WebServlet("/admin/member/orgapprove")
public class OrgApproveController extends HttpServlet {
    AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 기관회원 승인 대기 리스트 조회
        List<Member> pendingList = adminService.getPendingOrgMembers();
        req.setAttribute("pendingList", pendingList);
        req.getRequestDispatcher("/WEB-INF/views/admin/member/orgapprove.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 요청 파라미터 수집
        long mno = Long.parseLong(req.getParameter("mno")); // 승인/거절 대상 회원번호
        int appr = Integer.parseInt(req.getParameter("appr")); // 1=승인, 0=거절
        String reason = req.getParameter("reason"); // 거절 사유 (승인일 경우 null이거나 빈 문자열 가능)

        // 세션에서 관리자 번호 추출
        HttpSession session = req.getSession();
        Object admnoObj = session.getAttribute("admno");
        if (admnoObj == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "관리자 세션이 만료되었습니다.");
            return;
        }
        long admno = (long) admnoObj;

        // 서비스 로직 호출
        boolean result = adminService.approveOrReject(mno, appr, reason, admno);
        
        // 결과 처리
        if (result) {
            resp.sendRedirect(req.getContextPath() + "/admin/member/orgapprove");
        } else {
            resp.sendError(500, "기관 회원 승인 처리 실패");
        }
    }
}
