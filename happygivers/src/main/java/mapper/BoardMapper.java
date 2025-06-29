package mapper;

import java.util.List;

import domain.Board;
import domain.dto.Criteria;

public interface BoardMapper {
	void insert(Board board);
	void delete(Long bno);
	void update(Board board);
	
	
	List<Board> list(Criteria cri);
	long getCount(Criteria cri);
	
	
	Board selectOne(Long bno);
}
