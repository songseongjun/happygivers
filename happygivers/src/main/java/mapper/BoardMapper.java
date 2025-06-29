package mapper;

import java.util.List;

import domain.Board;

public interface BoardMapper {
	void insert(Board board);
	void delete(Long bno);
	void update(Board board);
	
	
	List<Board> list();
	
	Board selectOne(Long bno);
}
