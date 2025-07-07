package controller.admin.donate;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.Pay;
import domain.PayLog;
import service.PayService;


@WebServlet("/admin/donate/refundlist")
public class PayRefundList extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PayService payService = new PayService();
		List<PayLog> logs = payService.payLogList();
		

		req.setAttribute("logs", logs);
		req.getRequestDispatcher("/WEB-INF/views/admin/donate/refundlist.jsp").forward(req, resp);
	}
	
}
