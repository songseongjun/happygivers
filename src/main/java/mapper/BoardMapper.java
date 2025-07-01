package mapper;

import domain.en.Status;
import java.util.List;

import domain.Board;
import domain.dto.Criteria;

public interface BoardMapper {
	void insert(Board board);
	void delete(Long bno);
	void update(Board board);
	
	
	List<Board> list(Criteria cri);
	List<Board> listOrgCheck(Status status);
	long getCount(Criteria cri);
	
	
	Board selectOne(Long bno);
}
