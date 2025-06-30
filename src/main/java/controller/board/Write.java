package controller.board;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import domain.Board;
import domain.Donate;
import domain.dto.Criteria;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.AlertUtil;



@Slf4j
@WebServlet("/board/write")
public class Write extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 멤버 도매인 등록시
//		Criteria cri = Criteria.init(req);
//		Member loginMember = req.getSession().getAttribute("member");
//		if(loginMember == null) {
//			AlertUtil.alert("로그인 후 글 작성해주세요", "/member/login?" + cri.getQs2(), req, resp, true);
//			return;
//		}
//		if(loginMember.mtype != Mtype.ORG) {
//			AlertUtil.alert("글 작성 권한이 없습니다.", "/index", req, resp);
//			return;
//		}
		
		req.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// 파라미터 수집
		Long mno = Long.valueOf(req.getParameter("mno"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		Integer cno = Integer.valueOf(req.getParameter("cno"));
		Status status = Status.valueOf(req.getParameter("status"));
		
		Integer goalamount = Integer.valueOf(req.getParameter("goalamount"));
		String voiddate = req.getParameter("voiddate");
		
		
		

		// board 인스턴스 생성
		BoardService boardService = new BoardService();
		Board board = Board.builder().title(title).content(content).mno(mno).cno(cno).status(status).build();
		log.info("{}", board);
		
		// donate 인스턴스 생성
		Donate donate = Donate.builder().mno(mno).goalamount(goalamount).voiddate(voiddate).build();
		
		
		boardService.write(board, donate);
		AlertUtil.alert("글이 등록되었습니다.", "/board/list?cno=" + cno, req, resp);
	}
}
