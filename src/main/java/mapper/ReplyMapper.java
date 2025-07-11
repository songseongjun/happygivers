package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import domain.Reply;

public interface ReplyMapper {
	void insert(Reply reply);
	void update(Reply reply);
	void delete(Long rno);
	void deleteByBno(Long bno);
	
	Reply selectOne(Long rno);
	List<Reply> list(@Param("bno") Long bno, @Param("mno") Long mno, @Param("lastRno") Long lastRno);
	List<Reply> listAll();
	int getReplyCount(Long bno);
	List<Reply> selectByMno(Long mno);
}
