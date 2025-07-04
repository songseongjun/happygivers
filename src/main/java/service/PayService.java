package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.Board;
import domain.Donate;
import domain.DonateAction;
import domain.DonateRound;
import domain.Pay;
import domain.PayLog;
import mapper.BoardMapper;
import mapper.DonateMapper;
import mapper.PayMapper;

import util.MybatisUtil;

public class PayService {
	public Pay findBy(Long pno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			PayMapper mapper = session.getMapper(PayMapper.class);
			return mapper.selectOne(pno);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Pay> list(Long drno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			PayMapper mapper = session.getMapper(PayMapper.class);
			List<Pay> list = mapper.list(drno);
			
			return list;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	// 결제 성공시 등록
	public void register(DonateAction action, Pay pay, PayLog log) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try {
			DonateMapper donateMapper = session.getMapper(DonateMapper.class);
			PayMapper payMapper = session.getMapper(PayMapper.class);
			donateMapper.insertAction(action);
			pay.setDano(action.getDano());
			payMapper.insert(pay);
			log.setPno(pay.getPno());
			payMapper.insertLog(log);
			
			session.commit();
		}
		catch (Exception e){
			session.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	
	
	
	
	
	public void modify(Pay pay) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			PayMapper mapper = session.getMapper(PayMapper.class);
			mapper.update(pay);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void remove(Long pno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			PayMapper mapper = session.getMapper(PayMapper.class);
			mapper.delete(pno);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
