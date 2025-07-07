package controller.board;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import domain.Attach;
import domain.Board;
import domain.Donate;
import domain.DonateRound;
import domain.Member;
import domain.dto.Criteria;
import domain.en.Mtype;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.AlertUtil;
import util.ParamUtil;



@Slf4j
@WebServlet("/board/write")
public class Write extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 권한 확인
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");

		if(member == null || !(member.getStatus().equals(Status.ACTIVE) && member.getMtype().equals(Mtype.ORG) || member.getMtype().equals(Mtype.ADMIN))) {
			AlertUtil.alert("접근 권한이 없습니다.", "/board/list", req, resp);
		}
		
		
		req.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 권한 확인		
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");

		if(member == null || !(member.getStatus().equals(Status.ACTIVE) && member.getMtype().equals(Mtype.ORG) || member.getMtype().equals(Mtype.ADMIN))) {
			AlertUtil.alert("접근 권한이 없습니다.", "/board/list", req, resp);
		}
		


		// board 인스턴스 생성
		// donate 인스턴스 생성
		Donate donate = ParamUtil.get(req, Donate.class);
		DonateRound round = ParamUtil.get(req, DonateRound.class);		
		Board board = ParamUtil.get(req, Board.class); 
		Attach attach = null ;
		if(ParamUtil.get(req, Attach.class) != null) {
			attach = ParamUtil.get(req, Attach.class);
			attach.setMno(null);
			board.setAttach(attach);
		}
		log.info("{}", board);
		

		BoardService boardService = new BoardService();
		
			
		boardService.write(board, donate, round);
			
	
		
		
		
		AlertUtil.alert("글이 등록되었습니다.", "/board/list?cno=" + board.getCno(), req, resp);
	}
}
