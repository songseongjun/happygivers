package controller.admin;

import domain.dto.Criteria;
import domain.en.Status;
import service.BoardService;
import service.DonateService;
import service.PayService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class AdminController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DonateService donateService = new DonateService();
		PayService payService = new PayService();
		BoardService boardService = new BoardService();

		// 새 게시글 수
		int todayBoardCount = boardService.todayBoardCount();
		// 승인 대기 기부게시글 수
		Criteria cri = new Criteria();
		cri.setStatus(Status.READY);
		int readyCount = boardService.list(cri).size();

		// 모금기간 만료 게시글 수
		int totalVoidCount = boardService.totalVoidCount();

		// 전체 게시글 수
		int totalBoardCount = boardService.totalBoardCount();
		// 신규 결제 수
		int todayPayCount = payService.todayPaidCount();
		// 환불 횟수
		int totalRefundCount = payService.allRefundCount();

		// 전체 결제 횟수
		int totalPayCount = payService.allPayCount();

		// 전체 결제 금액
		long totalAmount = donateService.findTotalAmount();


		req.setAttribute("todayBoardCount", todayBoardCount);
		req.setAttribute("readyCount", readyCount);
		req.setAttribute("totalVoidCount", totalVoidCount);
		req.setAttribute("totalBoardCount", totalBoardCount);
		req.setAttribute("todayPayCount", todayPayCount);
		req.setAttribute("totalRefundCount", totalRefundCount);
		req.setAttribute("totalPayCount", totalPayCount);
		req.setAttribute("totalAmount", totalAmount);
		req.getRequestDispatcher("/WEB-INF/views/admin/adminIndex.jsp").forward(req, resp);
	}
	
}
