package controller.admin.donate;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.Pay;

import service.PayService;


@WebServlet("/admin/donate/paylist")
public class PayList extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PayService payService = new PayService();
		List<Pay> pays = payService.allPayList();
		

		req.setAttribute("pays", pays);
		req.getRequestDispatcher("/WEB-INF/views/admin/donate/paylist.jsp").forward(req, resp);
	}
	
}
