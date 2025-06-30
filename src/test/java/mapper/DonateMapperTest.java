package mapper;







import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;

@Slf4j
public class DonateMapperTest {
	private DonateMapper donateMapper = MybatisUtil.getSqlSession().getMapper(DonateMapper.class);
	
	@Test
	@DisplayName("모금함 생성 테스트")
	public void testInsert() {
		
	}
	
	
	
	@Test
	@DisplayName("모금함 삭제 테스트")
	public void testDelete() {
		Long dno = 1L;
		
		donateMapper.delete(dno);
	}
	
}
