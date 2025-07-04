package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.Member;
import domain.en.Mtype;
import jakarta.mail.Session;
import lombok.extern.slf4j.Slf4j;
import mapper.MemberMapper;
import util.MybatisUtil;
import util.PasswordEncoder;

@Slf4j
public class MemberService {
	// 회원가입
	public int register(Member member) {
		try (SqlSession session = MybatisUtil.getSqlSession()) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			member.setPw(PasswordEncoder.encode(member.getPw()));
			int result = mapper.insert(member);
		    session.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public boolean login(String id, String pw, Mtype mtype) {
		log.info("{}", mtype);
		Member member = findById(id);
		log.info("{}",member);
		if(member == null) {
			return false;
		}
		return PasswordEncoder.matches(pw, member.getPw()) && mtype.equals(member.getMtype());
//		return pw.equals(member.getPw()) && mtype.equals(member.getMtype());
		}

			//로그인 구현 기능
	public Member findById(String id) {
		try (SqlSession session = MybatisUtil.getSqlSession()) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//아이디 찾기 이메일
	public List<Member> findIdsByEmailAndName(String email, String name){
	SqlSession sqlSession = MybatisUtil.getSqlSession();
	try {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.findIdsByEmailAndName(email, name);
	}finally {
		sqlSession.close();
	}
		
	}
	
	public Member findByEmail(String email) {
	    try (SqlSession session = MybatisUtil.getSqlSession()) {
	        MemberMapper mapper = session.getMapper(MemberMapper.class);
	        return mapper.findByEmail(email);
	    }
	}
	

	    // 회원정보 수정
	    public void update(Member member) {
	        SqlSession session = MybatisUtil.getSqlSession();

	        try {
	            MemberMapper mapper = session.getMapper(MemberMapper.class);
	            mapper.updateMember(member); // Mapper에 정의된 쿼리 호출
	            session.commit(); // 실제 DB 반영
	        } finally {
	            session.close(); // 자원 정리
	        }
	    }
	    		//회원 이름,닉네임,전화번호,주소 등록수정
	    public void updateProfile(Member member) {
	        SqlSession session = MybatisUtil.getSqlSession();
	        try {
	            MemberMapper mapper = session.getMapper(MemberMapper.class);
	            mapper.updateProfile(member);
	            session.commit();
	        } finally {
	            session.close();
	        }
	    }

	}





