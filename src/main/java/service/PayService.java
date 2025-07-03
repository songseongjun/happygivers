package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.Pay;

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
	
	
	
	
	public void register(Pay pay) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			PayMapper mapper = session.getMapper(PayMapper.class);
			mapper.insert(pay);
		}
		catch (Exception e){
			e.printStackTrace();
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
