package service;

import org.apache.ibatis.session.SqlSession;

import domain.Member;
import mapper.MemberMapper;
import util.MybatisUtil;
import util.PasswordEncoder;

public class MemberService {
			//회원가입
	public int register(Member member) {
		try(SqlSession session = MybatisUtil.getSqlSession()){
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			member.setPw(PasswordEncoder.encode(member.getPw()));
			return mapper.insert(member);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
//	public boolean login(String id, String pw) {
//		Member member = findById(id);
//		if(member == null) { return fales;
//			return PasswordEncoder.matches(pw, member.getPw());
//		}
//	}
}
