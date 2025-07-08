package service;


import java.util.List;


import org.apache.ibatis.session.SqlSession;

import domain.Attach;
import domain.Board;
import domain.Category;
import domain.Donate;
import domain.DonateRound;
import domain.Member;
import domain.dto.Criteria;
import domain.en.Status;
import mapper.AttachMapper;
import mapper.BoardMapper;
import mapper.CategoryMapper;
import mapper.DonateMapper;
import mapper.MemberMapper;
import mapper.ReplyMapper;
import util.MybatisUtil;

public class BoardService {
	// 게시글 생성
	public void write(Board board) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class); 
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			mapper.insert(board);
			if(board.getAttach() != null) {
				Attach attach = board.getAttach();
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			}
			session.commit();
		}
		catch (Exception e){
			session.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	// 기부 게시글
	public void write(Board board, Donate donate, DonateRound round) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try {
			DonateMapper donateMapper = session.getMapper(DonateMapper.class);
			BoardMapper boardMapper = session.getMapper(BoardMapper.class);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			if(donate.getDno() == null) {
				// 1. 모금함 생성 - 첫회차일때
				donateMapper.insert(donate);
			}
			else { // 첫회차가 아닐 때
				round.setRound(donateMapper.findByMaxRound(donate.getDno()) + 1);
			}
			
			// 2-1. 모금함 번호를 회차에 넣고, 회차 생성
			round.setDno(donate.getDno());
			donateMapper.insertRound(round);
			
			
			// 3. 생성된 회차번호를 게시글에 넣고 게시글 생성
			board.setDrno(round.getDrno());
			boardMapper.insert(board);
			
			
			// 4. 첨부파일에 bno 넣고 첨부파일 생성
			if(board.getAttach() != null) {
				Attach attach = board.getAttach();
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			}
			session.commit();
		}
		catch (Exception e){
			session.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	
	
	// 수정하는데 기부회차 정보가 있으면, 회차정보도 상태 동일하게 수정, 썸네일도 수정
	public void modify(Board board) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try{
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			if(board.getDrno() != null) {
				DonateMapper donateMapper = session.getMapper(DonateMapper.class);
				DonateRound round = findRound(board.getDrno());
				round.setStatus(board.getStatus());
				donateMapper.updateRound(round);
				
			}
			if(board.getAttach() != null) {
				if(attachMapper.selectOne(board.getBno()) != null) {
					attachMapper.update(board.getAttach());
				}
				else {
					attachMapper.insert(board.getAttach());
				}				
			}
				
			mapper.update(board);
			session.commit();
		}
		catch (Exception e){
			session.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	
	
	// 게시글, 회차정보 Status값 delete로 변경
	public void remove(Long bno) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			DonateMapper donateMapper = session.getMapper(DonateMapper.class);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			Board board = findByBno(bno);
			if(board.getDrno() != null) {
				DonateRound round = donateMapper.selectOneRound(board.getDrno());
				round.setStatus(Status.DELETE);
				donateMapper.updateRound(round);
			}
			if(findAttach(bno) != null) {
				attachMapper.deleteByBno(bno);
			}
			board.setStatus(Status.DELETE);
			mapper.update(board);
			session.commit();
		}
		catch (Exception e){
			session.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	// 전체 리스트
	public List<Board> list(Criteria cri) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class); 

			List<Board> list = mapper.list(cri);
			for(Board b : list) {
				if(b.getDrno() != null) {
					b.setRound(findRound(b.getDrno()));
					b.setCname(findCname(b.getCno()));
					b.setNickname(findNickname(b.getMno()));
					b.setName(findName(b.getMno()));
					b.setThumbnail(findThumbnail(b.getBno()));
					b.setAttach(findAttach(b.getBno()));
				}
			}
			
			return list;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	

	
	
	// 게시글 개수 가져오기
	public long getCount(Criteria cri) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.getCount(cri);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	// bno로 게시글 가져오기
	public Board findByBno(Long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);

			Board board = mapper.selectOne(bno);
			board.setThumbnail(findThumbnail(bno));
			board.setAttach(findAttach(bno));
			board.setNickname(findNickname(board.getMno()));
			board.setName(findName(board.getMno()));
			if(board.getDrno() != null) {
				board.setRound(findRound(bno));
			}
			
			return board;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	// 썸네일 가져오기
	public String findThumbnail(Long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()){
			if (bno == null) return null;
		    
			AttachMapper mapper = session.getMapper(AttachMapper.class);
		    String thumbnail = mapper.getBoardThumbnail(bno);
			return thumbnail;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	// 첨부파일 가져오기
	public Attach findAttach(Long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()){
			if (bno == null) return null;
			
			AttachMapper mapper = session.getMapper(AttachMapper.class);
			
			Attach attach = null; 
			if(mapper.selectOne(bno) != null) {
				attach = mapper.selectOne(bno); 
			}
					
			return attach;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	// 회차정보 가져오기
		public DonateRound findRound(Long drno) {
			try(SqlSession session = MybatisUtil.getSqlSession()){
				if (drno == null) return null;
			    
				DonateMapper mapper = session.getMapper(DonateMapper.class);
			    DonateRound round = mapper.selectOneRound(drno);
				return round;
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
	// 카테고리명 가져오기
		public String findCname(Integer cno) {
			try(SqlSession session = MybatisUtil.getSqlSession()){
				if (cno == null) return null;
			    
				CategoryMapper mapper = session.getMapper(CategoryMapper.class);
			    Category cate = mapper.selectOne(cno);
				return cate.getCname();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
	// 작성자명 가져오기
		public String findName(Long mno) {
			try(SqlSession session = MybatisUtil.getSqlSession()){
				if (mno == null) return null;
			    
				MemberMapper mapper = session.getMapper(MemberMapper.class);
			    Member member = mapper.findByMno(mno);

			    return member.getName();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
	
		// 닉네임 가져오기
		public String findNickname(Long mno) {
			try(SqlSession session = MybatisUtil.getSqlSession()){
				if (mno == null) return null;
			    
				MemberMapper mapper = session.getMapper(MemberMapper.class);
			    Member member = mapper.findByMno(mno);
			    if(member.getNickname() == null) {
			    	return null;
			    }
			    return member.getNickname();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
		
	// 댓글 개수 가져오기
	public int getReplyCount(Long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			int count = mapper.getReplyCount(bno);
			
			return count;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}	
	
	// 마감임박 게시글 가져오기
		public Board findByDeadline() {
			try(SqlSession session = MybatisUtil.getSqlSession()) {
				BoardMapper mapper = session.getMapper(BoardMapper.class); 

				Board board = mapper.selectOneDeadline();
				board.setRound(findRound(board.getDrno()));
				board.setThumbnail(findThumbnail(board.getBno()));
				board.setName(findName(board.getMno()));
				
				return board;
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}

	// 마감임박 게시글 가져오기
		public List<Board> findNewList() {
			try(SqlSession session = MybatisUtil.getSqlSession()) {
				BoardMapper mapper = session.getMapper(BoardMapper.class); 
				List<Board> newBoards = mapper.listNew();
				for(Board b : newBoards) {
					b.setRound(findRound(b.getDrno()));
					b.setName(findName(b.getMno()));
					b.setThumbnail(findThumbnail(b.getBno()));
				}
				
				return newBoards;
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
	// 이미지 제거한 컨텐츠 가져오기
		public String removeImgContent(String content) {
			if (content == null) return "";
		    return content.replaceAll("!\\[.*?\\]\\(.*?\\)", "");
		}
		
	
}
