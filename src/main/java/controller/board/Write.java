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
import domain.dto.PageDto;
import domain.en.Ctype;
import domain.en.Mtype;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.AlertUtil;
import util.ParamUtil;



@Slf4j
@WebServlet(urlPatterns = {"/board/write", "/notice/write", "/qna/write"})
public class Write extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 권한 확인
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");

		String path = req.getServletPath();
		Criteria cri = ParamUtil.get(req, Criteria.class);
		if (cri == null) {
			cri = new Criteria();
		}

		// 기부 게시글 작성
		if ("/board/write".equals(path)) {
			if(member == null || !(member.getStatus().equals(Status.ACTIVE) && member.getMtype().equals(Mtype.ORG) || member.getMtype().equals(Mtype.ADMIN))) {
				AlertUtil.alert("접근 권한이 없습니다.", "/board/list", req, resp);
			}
			cri.setCtype(Ctype.DONATE);
		}

		// 공지 게시글 작성
		else if ("/notice/write".equals(path)) {
			if(member == null || !(member.getStatus().equals(Status.ACTIVE) && member.getMtype().equals(Mtype.MANAGER) || member.getMtype().equals(Mtype.ADMIN))) {
				AlertUtil.alert("접근 권한이 없습니다.", "/notice/list", req, resp);
			}
			cri.setCtype(Ctype.NOTICE);
		}

		// QNA 게시글 작성
		else if ("/qna/write".equals(path)) {
			if(member == null || !(member.getStatus().equals(Status.ACTIVE) && member.getMtype().equals(Mtype.MANAGER) || member.getMtype().equals(Mtype.ADMIN))) {
				AlertUtil.alert("접근 권한이 없습니다.", "/qna/list", req, resp);
			}
			cri.setCtype(Ctype.QNA);
		}

		req.setAttribute("cri", cri);
		req.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 권한 확인		
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");
		String path = req.getServletPath();
		Criteria cri = ParamUtil.get(req, Criteria.class);
		if (cri == null) {
			cri = new Criteria();
		}

		if ("/board/write".equals(path)) {
			if(member == null || !(member.getStatus().equals(Status.ACTIVE) && member.getMtype().equals(Mtype.ORG) || member.getMtype().equals(Mtype.ADMIN))) {
				AlertUtil.alert("접근 권한이 없습니다.", "/board/list", req, resp);
			}
		}
		// 공지 게시글 작성
		else if ("/notice/write".equals(path)) {
			if(member == null || !(member.getStatus().equals(Status.ACTIVE) && member.getMtype().equals(Mtype.MANAGER) || member.getMtype().equals(Mtype.ADMIN))) {
				AlertUtil.alert("접근 권한이 없습니다.", "/notice/list", req, resp);
			}
		}

		// QNA 게시글 작성
		else if ("/QNA/write".equals(path)) {
			if(member == null || !(member.getStatus().equals(Status.ACTIVE) && member.getMtype().equals(Mtype.MANAGER) || member.getMtype().equals(Mtype.ADMIN))) {
				AlertUtil.alert("접근 권한이 없습니다.", "/qna/list", req, resp);
			}
		}


		Donate donate = ParamUtil.get(req, Donate.class);
		DonateRound round = ParamUtil.get(req, DonateRound.class);		


		Board board = ParamUtil.get(req, Board.class);
		Attach attach = null ;
		if(ParamUtil.get(req, Attach.class).getUuid() != null) {
			attach = ParamUtil.get(req, Attach.class);
			attach.setMno(null);
			board.setAttach(attach);
		}
		log.info("{}", board);
		List<Attach> images = null;
		String imgListJson = req.getParameter("imgList"); 
		if(imgListJson != null && !imgListJson.trim().isEmpty()) {
			Gson gson = new Gson();
		    Type listType = new TypeToken<List<Attach>>(){}.getType();
		    images = gson.fromJson(imgListJson, listType);
			
		    board.setImages(images);
		}
		

		BoardService boardService = new BoardService();

		if ("/board/write".equals(path)) {
		boardService.write(board, donate, round);
		AlertUtil.alert("글이 등록되었습니다.", "/board/list?cno=" + board.getCno(), req, resp);
		}
		else {
			boardService.write(board);
			AlertUtil.alert("글이 등록되었습니다.", "/index", req, resp);
		}
	}
}
