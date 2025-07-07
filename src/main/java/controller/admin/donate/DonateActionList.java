package controller.admin.donate;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.DonateAction;
import domain.dto.Criteria;
import service.BoardService;
import service.DonateService;
import util.ParamUtil;

@WebServlet("/admin/donate/actionlist")
public class DonateActionList extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DonateService donateService = new DonateService();
		List<DonateAction> actions = donateService.actionList();
		
		

		req.setAttribute("actions", actions);
		req.getRequestDispatcher("/WEB-INF/views/admin/donate/actionlist.jsp").forward(req, resp);
	}
	
}
