package service;

import org.apache.ibatis.session.SqlSession;


import domain.DonateRound;
import mapper.DonateMapper;
import util.MybatisUtil;

public class DonateService {
	
	// drno로 round개체 찾아오기
	public DonateRound findByDrno(Long drno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			DonateMapper mapper = session.getMapper(DonateMapper.class); 
			DonateRound round = mapper.selectOneRound(drno);
			return round;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateRound(DonateRound round) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			DonateMapper mapper = session.getMapper(DonateMapper.class); 
			mapper.updateRound(round);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
