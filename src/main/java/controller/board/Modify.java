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


import domain.Board;
import domain.Donate;
import domain.DonateRound;
import domain.Member;
import domain.dto.Criteria;
import domain.en.Mtype;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import service.DonateService;
import util.AlertUtil;
import util.ParamUtil;



@Slf4j
@WebServlet("/board/modify")
public class Modify extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 권한 확인		
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");
		BoardService boardService = new BoardService();
		Board board = boardService.findByBno(Long.valueOf(req.getParameter("bno")));
		
		if(member == null || !(member.getStatus().equals(Status.ACTIVE) && member.getMtype().equals(Mtype.ORG) && member.getMno().equals(board.getMno()) || member.getMtype().equals(Mtype.ADMIN))) {
			AlertUtil.alert("접근 권한이 없습니다.", "/board/view?bno=" + board.getBno(), req, resp);
		}
		
		board.setRound(boardService.findRound(board.getDrno()));
		
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/views/board/modify.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 권한 확인		
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");
		BoardService boardService = new BoardService();
		Board board = boardService.findByBno(Long.valueOf(req.getParameter("bno")));
		
		if(member == null || !(member.getStatus().equals(Status.ACTIVE) && member.getMtype().equals(Mtype.ORG) && member.getMno().equals(board.getMno()) || member.getMtype().equals(Mtype.ADMIN))) {
			AlertUtil.alert("접근 권한이 없습니다.", "/board/view?bno=" + board.getBno(), req, resp);
		}
			
		Board modifyBoard = ParamUtil.get(req, Board.class);
		DonateRound round = ParamUtil.get(req, DonateRound.class);
//		round.setDrno(modifyBoard.getDrno());
		DonateService donateService = new DonateService();
		log.info("{}", modifyBoard.getDrno());
		log.info("{}", round);
		donateService.updateRound(round);
		boardService.modify(modifyBoard);
		
		
		AlertUtil.alert("글이 수정되었습니다.", "/board/view?bno=" + board.getBno(), req, resp);
	}
}
