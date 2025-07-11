package service;

import java.util.ArrayList;
import java.util.List;

import domain.Member;
import org.apache.ibatis.session.SqlSession;

import domain.DonateAction;
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

	// round 수정
	public void updateRound(DonateRound round) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			DonateMapper mapper = session.getMapper(DonateMapper.class); 
			mapper.updateRound(round);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// 위치한 기부함에 기부한 금액 가져오기
	public long findMyAmount(Long drno, Long mno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			DonateMapper mapper = session.getMapper(DonateMapper.class); 
			return mapper.findMyAmount(drno, mno);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	// 플랫폼에 기부한 전체 금액 가져오기
	public long findMyTotalAmount(Long mno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			DonateMapper mapper = session.getMapper(DonateMapper.class); 
			return mapper.findMyTotalAmount(mno);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	// 플랫폼에 기부된 전체 금액 가져오기
	public long findTotalAmount() {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			DonateMapper mapper = session.getMapper(DonateMapper.class); 
			return mapper.findTotalAmount();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	// 기부 상태가 paid인 모든 기부내역 가져오기
	public List<DonateAction> actionList() {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			DonateMapper mapper = session.getMapper(DonateMapper.class); 
			return mapper.adminActionList();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	// 기부 게시글에 있는 회차번호로 top 3의 번호를 가져옴
	public List<Member> findTop3(Long drno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			DonateMapper mapper = session.getMapper(DonateMapper.class);
			List<Member> top3 = new ArrayList<>();
			List<Long> list = mapper.findTop3(drno);
			MemberService memberService = new MemberService();
			for(Long mno : list){
				top3.add(memberService.findByMno(mno));
			}
			return top3;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	// 기부 상태가 paid인 유저한명의 기부내역 가져오기
	public List<DonateAction> myActionList(Long mno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			DonateMapper mapper = session.getMapper(DonateMapper.class);
			return mapper.myActionList(mno);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
