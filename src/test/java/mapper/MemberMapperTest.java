package mapper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Board;
import domain.Member;
import domain.dto.Criteria;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;

@Slf4j
public class MemberMapperTest {
	private MemberMapper memberMapper = MybatisUtil.getSqlSession().getMapper(MemberMapper.class);

	@Test
	@DisplayName("아이디로 맴버찾아오기")
	public void testFindById() {
		String id = "admin";
		Member member = memberMapper.findById(id);
		log.info("{}",member);
	}

	
}
