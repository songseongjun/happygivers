package mapper;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Donate;
import domain.dto.Criteria;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;

@Slf4j
public class DonateMapperTest {
	private DonateMapper donateMapper = MybatisUtil.getSqlSession().getMapper(DonateMapper.class);
	
	@Test
	@DisplayName("모금함 생성 테스트")
	public void testInsert() {
		// given
		Long bno = 7L;
		Long mno = 1L;
		int goalamount = 5555555;
		String voiddate = "2025-07-24";
		
		// when
		Donate donate = Donate.builder().mno(mno).bno(bno).goalamount(goalamount).voiddate(voiddate).build();
		
		// then ~ actual, expect
		donateMapper.insert(donate);
		log.info("{}", donate);
	}
	
	
	
	@Test
	@DisplayName("모금함 삭제 테스트")
	public void testDelete() {
		Long dno = 1L;
		
		donateMapper.delete(dno);
	}
	
}
