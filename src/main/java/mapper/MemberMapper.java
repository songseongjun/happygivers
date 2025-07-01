package mapper;

import domain.Member;

public interface MemberMapper {

	int insert(Member member);
	
	Member findById(String id);
	Member findByMno(Long mno);
}
