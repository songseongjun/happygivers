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
	// �쉶�썝媛��엯
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
	
	//濡쒓렇�씤
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

			//濡쒓렇�씤 援ы쁽 湲곕뒫
	public Member findById(String id) {
		try (SqlSession session = MybatisUtil.getSqlSession()) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//�븘�씠�뵒 李얘린 �씠硫붿씪
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
	

	    // �쉶�썝�젙蹂� �닔�젙
	    public void update(Member member) {
	        SqlSession session = MybatisUtil.getSqlSession();

	        try {
	            MemberMapper mapper = session.getMapper(MemberMapper.class);
	            mapper.updateMember(member); // Mapper�뿉 �젙�쓽�맂 荑쇰━ �샇異�
	            session.commit(); // �떎�젣 DB 諛섏쁺
	        } finally {
	            session.close(); // �옄�썝 �젙由�
	        }
	    }
	    		//�쉶�썝 �씠由�,�땳�꽕�엫,�쟾�솕踰덊샇,二쇱냼 �벑濡앹닔�젙
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
	    		// 鍮꾨�踰덊샇蹂�寃쎌뾽寃뚯씠�듃
	    public void updatePassword(String id, String pw) {
	        SqlSession session = MybatisUtil.getSqlSession();

	        try {
	            MemberMapper mapper = session.getMapper(MemberMapper.class);

	            // 鍮꾨�踰덊샇 �븫�샇�솕 (瑗� �빐�빞 �븿)
	            String encodedPw = PasswordEncoder.encode(pw);

	            // DB �뾽�뜲�씠�듃
	            mapper.updatePassword(id, encodedPw);

	            session.commit();
	        } finally {
	            session.close();
	        }
	    }
	    
	 // 鍮꾨�踰덊샇 蹂�寃� 硫붿꽌�뱶
	    public boolean updatePasswordByUuid(String uuid, String newPassword) {
	        SqlSession session = MybatisUtil.getSqlSession();
	        try {
	            MemberMapper mapper = session.getMapper(MemberMapper.class);
	            // 鍮꾨�踰덊샇 �븫�샇�솕 �븘�닔
	            String encodedPw = PasswordEncoder.encode(newPassword);

	            // 鍮꾨�踰덊샇 蹂�寃� �닔�뻾
	            int result = mapper.updatePasswordByUuid(uuid, encodedPw);

	            session.commit(); 
	            return result == 1; 
	        } finally {
	            session.close();
	        }
	    }


	}





