package controller.pw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

public class ResetPassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String newPassword = request.getParameter("pw");

        boolean result = new MemberService().updatePasswordByUuid(uuid, newPassword);

        if (result) {
            response.sendRedirect(request.getContextPath() + "/member/login?reset=success");
        } else {
            response.sendRedirect(request.getContextPath() + "/member/resetpw?uuid=" + uuid + "&error=1");
        }
    }
}
