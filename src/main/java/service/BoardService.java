package service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;

import domain.Board;
import domain.Category;
import domain.Donate;
import domain.DonateRound;
import domain.Member;
import domain.dto.Criteria;
import domain.en.Status;
import mapper.BoardMapper;
import mapper.CategoryMapper;
import mapper.DonateMapper;
import mapper.MemberMapper;
import mapper.ReplyMapper;
import util.MybatisUtil;

public class BoardService {
	// 게시글 생성
	public void write(Board board) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class); 
			mapper.insert(board);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// 기부 게시글
	public void write(Board board, Donate donate, DonateRound round) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try {
			DonateMapper donateMapper = session.getMapper(DonateMapper.class);
			BoardMapper boardmapper = session.getMapper(BoardMapper.class);
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
			boardmapper.insert(board);
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
	
	
	
	
	public void modify(Board board) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.update(board);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void modifyStatus(Long bno, Status status) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			Board board = mapper.selectOne(bno);
			board.setStatus(status);
			mapper.update(board);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
	// bno로 삭제
	public void remove(Long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class); 
			mapper.delete(bno);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// 전체 리스트
	public List<Board> list(Criteria cri) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class); 
			List<Board> list = mapper.list(cri);
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
			return mapper.selectOne(bno);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	// 썸네일 가져오기
	public String findThumbnail(String content) {
	    if (content == null) return null;
	    String regex = "!\\[\\]\\((data:image\\/[^;]+;base64,[^)]+)\\)";
	    Matcher matcher = Pattern.compile(regex).matcher(content);
	    if (matcher.find()) return matcher.group(1);
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
				board.setThumbnail(findThumbnail(board.getContent()));
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
					b.setThumbnail(findThumbnail(b.getContent()));
				}
				
				return newBoards;
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
}
