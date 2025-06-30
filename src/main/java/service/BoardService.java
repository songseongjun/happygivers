package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.Board;
import domain.Donate;
import domain.DonateRound;
import domain.dto.Criteria;
import domain.en.Status;
import mapper.BoardMapper;
import mapper.DonateMapper;
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
	
}
