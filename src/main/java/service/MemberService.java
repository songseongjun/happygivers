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
	//회원가입 처리 비밀번호 암호화 후 DB에 저장
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
	
	
//로그인 기능 아이디로 회원 조회  비밀번호 일치 여부 확인 로그인 시 입력한 mtype(회원 유형)도 일치하는지 검사
	public boolean login(String id, String pw, Mtype mtype) {
	    log.info("{}", mtype);
	    Member member = findById(id);
	    log.info("{}", member);

	    // 1. mtype이 null인지 먼저 확인
	    if (member == null || mtype == null) {
	        return false;
	    }

	    // 2. 비밀번호 일치 + mtype 일치
	    return PasswordEncoder.matches(pw, member.getPw()) && mtype.equals(member.getMtype());
	}

	
			//아이디로 회원 1명 조회 (로그인, 중복확인사용
	public Member findById(String id) {
		try (SqlSession session = MybatisUtil.getSqlSession()) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//이메일 + 이름으로 아이디 찾기 아이디 찾기(비밀번호 찾기 전단계) 기능에 사용
	public List<Member> findIdsByEmailAndName(String email, String name){
	SqlSession sqlSession = MybatisUtil.getSqlSession();
	try {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.findIdsByEmailAndName(email, name);
	}finally {
		sqlSession.close();
	}
		
	}
	
	
	//이메일로 회원 1명 조회이메일 중복검사, 인증 확인 등에 사용
	public Member findByEmail(String email) {
	    try (SqlSession session = MybatisUtil.getSqlSession()) {
	        MemberMapper mapper = session.getMapper(MemberMapper.class);
	        return mapper.findByEmail(email);
	    }
	}
	

	    // 회원 정보 전체 업데이트 회원 이름, 전화번호, 닉네임 등 일반 정보 수정
	    public void update(Member member) {
	        SqlSession session = MybatisUtil.getSqlSession();

	        try {
	            MemberMapper mapper = session.getMapper(MemberMapper.class);
	            mapper.updateMember(member); 
	            session.commit(); 
	        } finally {
	            session.close(); 
	        }
	    }
	    		//마이페이지에서 프로필 정보 수정 이름, 닉네임, 전화번호, 주소 등 수정 후 DB 반영
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
	    		// 비밀번호 변경 (마이페이지용) 입력한 새 비밀번호를 암호화해서 DB에 저장
	    public void updatePassword(String id, String pw) {
	        SqlSession session = MybatisUtil.getSqlSession();

	        try {
	            MemberMapper mapper = session.getMapper(MemberMapper.class);
	            String encodedPw = PasswordEncoder.encode(pw);
	            mapper.updatePassword(id, encodedPw);

	            session.commit();
	        } finally {
	            session.close();
	        }
	    }
	    
	 // 비밀번호 찾기(이메일 인증 후)에 사용하는 비밀번호 변경 Redis나 인증 링크 통해 받은 uuid 기반으로 새 비밀번호 설정
	    public boolean updatePasswordByUuid(String uuid, String newPassword) {
	        SqlSession session = MybatisUtil.getSqlSession();
	        try {
	            MemberMapper mapper = session.getMapper(MemberMapper.class);
	     
	            String encodedPw = PasswordEncoder.encode(newPassword);

	            int result = mapper.updatePasswordByUuid(uuid, encodedPw);

	            session.commit(); 
	            return result == 1; 
	        } finally {
	            session.close();
	        }
	    }
	    
	    //회원 번호(mno)로 회원 조회 자동 로그인(토큰으로 mno 찾아서 로그인)에서 사용됨
	    public Member findByMno(Long mno) {
	        SqlSession session = MybatisUtil.getSqlSession();
	        try {
	            MemberMapper mapper = session.getMapper(MemberMapper.class);
	            return mapper.findByMno(mno);
	        } finally {
	            session.close();
	        }
	    }




	}





