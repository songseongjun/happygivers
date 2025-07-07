package mapper;

import java.util.List;

import domain.Attach;


public interface AttachMapper {
	void insert(Attach attach);
	void update(Attach attach);
	Attach selectOne(Long bno);
	void delete(String uuid);
	void deleteByBno(Long bno);
	
	List<Attach> selectYesterdayList();
	
	String getBoardThumbnail(Long Bno);
}
