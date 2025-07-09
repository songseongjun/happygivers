package mapper;

import org.junit.jupiter.api.Test;

import util.MybatisUtil;

public class AdminMapperTest {
	private AdminMapper mapper = MybatisUtil.getSqlSession().getMapper(AdminMapper.class);
	
	@Test
	public void selectPendingOrgMembers() {
		mapper.selectPendingOrgMembers().forEach(System.out::println);
	}
}
