package controller.tos;

import domain.en.Ctype;
import domain.en.Mtype;
import domain.en.Status;
import util.AlertUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/terms", "/privacy"})
public class tosController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        if ("/terms".equals(path)) {
            req.getRequestDispatcher("/WEB-INF/views/tos/terms.jsp").forward(req, resp);
        }
        if ("/privacy".equals(path)) {
            req.getRequestDispatcher("/WEB-INF/views/tos/privacy.jsp").forward(req, resp);
        }
    }
}
