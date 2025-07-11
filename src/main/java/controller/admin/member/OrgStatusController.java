package controller.admin.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import domain.ApprLog;
import service.AdminService;

@WebServlet("/admin/member/orgstatus")
public class OrgStatusController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ApprLog> logs = new AdminService().getApprovalLogs();

        req.setAttribute("logs", logs);
        req.getRequestDispatcher("/WEB-INF/views/admin/member/orgstatus.jsp")
                .forward(req, resp);
    }
}
