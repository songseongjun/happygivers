package service;

import org.apache.ibatis.session.SqlSession;

import domain.Member;
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
			return mapper.insert(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
<<<<<<< HEAD
	public boolean login(String id, String pw) {
		Member member = findById(id);
		log.info("{}",member);
		if(member == null) {
			return false;
		}
//			return PasswordEncoder.matches(pw, member.getPw()); 이쪽부분 고쳐야댐
		return pw.equals(member.getPw());
		}
	
	public Member findById(String id) {
		try(SqlSession session =MybatisUtil.getSqlSession()){
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.findById(id);
		}catch(Exception e){
=======

	public boolean login(String id, String pw) {
		Member member = findById(id);
		if (member == null) {
			return false;
		}

		return PasswordEncoder.matches(pw, member.getPw());
	}
			//로그인 구현 기능
	public Member findById(String id) {
		try (SqlSession session = MybatisUtil.getSqlSession()) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.findById(id);
		} catch (Exception e) {
>>>>>>> branch 'ssj' of https://github.com/manlubo/happygivers.git
			e.printStackTrace();
		}
		return null;
	}
}
