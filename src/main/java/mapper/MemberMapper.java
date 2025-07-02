package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import domain.Member;

public interface MemberMapper {

	int insert(Member member);
	
	Member findById(String id);
	
	Member findByMno(Long mno);
	
	List<Member> findIdsByEmailAndName(@Param("email") String email, @Param("name") String name);

	void updateEmailVerifiedByEmail(String email);
	
	Member selectByEmail(String email);

}
