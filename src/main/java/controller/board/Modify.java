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
import domain.en.Ctype;
import domain.en.Mtype;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import service.DonateService;
import util.AlertUtil;
import util.ParamUtil;



@Slf4j
@WebServlet(urlPatterns = {"/board/modify", "/notice/modify", "/qna/modify"})
public class Modify extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 권한 확인
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");
		BoardService boardService = new BoardService();
		Board board = boardService.findByBno(Long.valueOf(req.getParameter("bno")));

		Criteria cri = ParamUtil.get(req, Criteria.class);
		if (cri == null) {
			cri = new Criteria();
		}

		String path = req.getServletPath();

		// 기부 게시글 수정 처리
		if ("/board/modify".equals(path)) {
			cri.setCtype(Ctype.DONATE);
			if(member == null || !(member.getStatus().equals(Status.ACTIVE) &&  (member.getMno().equals(board.getMno()) || member.getMtype().equals(Mtype.ADMIN)))) {
				AlertUtil.alert("접근 권한이 없습니다.", "/board/view?bno=" + board.getBno(), req, resp);
			}
			board.setRound(boardService.findRound(board.getDrno()));
		}

		// 공지 게시글 수정 처리
		if ("/notice/modify".equals(path)) {
			cri.setCtype(Ctype.NOTICE);
			if(member == null || !(member.getStatus().equals(Status.ACTIVE) &&  (member.getMno().equals(board.getMno()) || member.getMtype().equals(Mtype.ADMIN)))) {
				AlertUtil.alert("접근 권한이 없습니다.", "/notice/view?bno=" + board.getBno(), req, resp);
			}
		}

		// Q&A 게시글 수정 처리
		if ("/qna/modify".equals(path)) {
			cri.setCtype(Ctype.QNA);
			if(member == null || !(member.getStatus().equals(Status.ACTIVE) &&  (member.getMno().equals(board.getMno()) || member.getMtype().equals(Mtype.ADMIN)))) {
				AlertUtil.alert("접근 권한이 없습니다.", "/qna/view?bno=" + board.getBno(), req, resp);
			}
		}


		req.setAttribute("board", board);
		req.setAttribute("cri", cri);
		req.getRequestDispatcher("/WEB-INF/views/board/modify.jsp").forward(req, resp);
	}








	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 권한 확인		
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");
		BoardService boardService = new BoardService();
		Board board = boardService.findByBno(Long.valueOf(req.getParameter("bno")));
		String path = req.getServletPath();
		DonateService donateService = new DonateService();

		Board modifyBoard = ParamUtil.get(req, Board.class);
		Attach attach = null;
		if(ParamUtil.get(req, Attach.class).getUuid() != null) {
			attach = ParamUtil.get(req, Attach.class);
			attach.setMno(null);
			modifyBoard.setAttach(attach);
		}
		List<Attach> images = null;
		String imgListJson = req.getParameter("imgList");
		log.info("{}", imgListJson);
		if(imgListJson != null && !imgListJson.trim().isEmpty()) {
			Gson gson = new Gson();
			Type listType = new TypeToken<List<Attach>>(){}.getType();
			images = gson.fromJson(imgListJson, listType);
			log.info("{}", images);
			modifyBoard.setImages(images);
		}

		// 기부 게시글 수정 처리
		if ("/board/modify".equals(path)) {

			if(member == null || !(member.getStatus().equals(Status.ACTIVE) &&  (member.getMno().equals(board.getMno()) || member.getMtype().equals(Mtype.ADMIN)))) {
				AlertUtil.alert("접근 권한이 없습니다.", "/board/view?bno=" + board.getBno(), req, resp);
			}

			DonateRound round = ParamUtil.get(req, DonateRound.class);
			donateService.updateRound(round);
		}

		// 공지 게시글 수정 처리
		if ("/notice/modify".equals(path)) {
			if(member == null || !(member.getStatus().equals(Status.ACTIVE) &&  (member.getMno().equals(board.getMno()) || member.getMtype().equals(Mtype.ADMIN)))) {
				AlertUtil.alert("접근 권한이 없습니다.", "/notice/view?bno=" + board.getBno(), req, resp);
			}

		}

		// Q&A 게시글 수정 처리
		if ("/qna/modify".equals(path)) {
			if(member == null || !(member.getStatus().equals(Status.ACTIVE) &&  (member.getMno().equals(board.getMno()) || member.getMtype().equals(Mtype.ADMIN)))) {
				AlertUtil.alert("접근 권한이 없습니다.", "/qna/view?bno=" + board.getBno(), req, resp);
			}

		}




		
		
		
		boardService.modify(modifyBoard);





		if ("/board/modify".equals(path)) {
			AlertUtil.alert("글이 수정되었습니다.", "/board/view?bno=" + board.getBno(), req, resp);
		}
		if ("/notice/modify".equals(path)) {
			AlertUtil.alert("글이 수정되었습니다.", "/notice/view?bno=" + board.getBno(), req, resp);
		}
		if ("/qna/modify".equals(path)) {
			AlertUtil.alert("글이 수정되었습니다.", "/qna/view?bno=" + board.getBno(), req, resp);
		}

	}
}
