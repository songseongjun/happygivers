package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import domain.Member;

public interface MemberMapper {

	int insert(Member member); //회원가입
	
	Member findById(String id);//아이디조회
	
	Member findByMno(Long mno);//회원번호조회
	
	List<Member> findIdsByEmailAndName(@Param("email") String email, @Param("name") String name);//아이디찾기용

	void updateEmailVerifiedByEmail(String email);
	
	Member selectByEmail(String email);
	
		//이메일찾기
	Member findByEmail(String email);
	
	void verifyEmail(String uuid);


}
