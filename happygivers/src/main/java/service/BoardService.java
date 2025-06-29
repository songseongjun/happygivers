package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.Board;
import domain.en.Status;
import mapper.BoardMapper;
import util.MybatisUtil;

public class BoardService {
	// 게시글 생성
	public void register(Board board) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class); 
			mapper.insert(board);
		}
		catch (Exception e){
			e.printStackTrace();
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
	public List<Board> list() {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class); 
			return mapper.list();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
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
