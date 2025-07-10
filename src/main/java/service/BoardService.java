package service;


import java.util.ArrayList;
import java.util.List;


import domain.en.Ctype;
import org.apache.ibatis.session.SqlSession;

import domain.Attach;
import domain.Board;
import domain.Category;
import domain.Donate;
import domain.DonateRound;
import domain.Member;
import domain.dto.Criteria;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import mapper.AttachMapper;
import mapper.BoardMapper;
import mapper.CategoryMapper;
import mapper.DonateMapper;
import mapper.MemberMapper;
import mapper.ReplyMapper;
import util.MybatisUtil;
import util.S3Util;

@Slf4j
public class BoardService {
	// 게시글 생성
	public void write(Board board) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class); 
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			mapper.insert(board);
			if(board.getAttach() != null) {
				Attach attach = board.getAttach();
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			}
			if (board.getImages() != null){
				for(Attach i : board.getImages()){
					i.setViewbno(board.getBno());
					attachMapper.insert(i);
				}
			}
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
	
	// 기부 게시글
	public void write(Board board, Donate donate, DonateRound round) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try {
			DonateMapper donateMapper = session.getMapper(DonateMapper.class);
			BoardMapper boardMapper = session.getMapper(BoardMapper.class);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			if(donate.getDno() == null) {
				// 1. 모금함 생성 - 첫회차일때
				donateMapper.insert(donate);
			}
			else { // 첫회차가 아닐 때
				round.setRound(donateMapper.findByMaxRound(donate.getDno()) + 1);
			}
			
			// 2-1. 모금함 번호를 회차에 넣고, 회차 생성
			round.setDno(donate.getDno());
			donateMapper.insertRound(round);
			
			
			// 3. 생성된 회차번호를 게시글에 넣고 게시글 생성
			board.setDrno(round.getDrno());
			boardMapper.insert(board);
			
			
			// 4. 첨부파일에 bno 넣고 첨부파일 생성
			if(board.getAttach() != null) {
				Attach attach = board.getAttach();
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			}
			
			// 게시글 내부 이미지에 bno 붙여주기
			if(board.getImages() != null) {
				for(Attach i : board.getImages()) {
					i.setViewbno(board.getBno());
					i.setImage(true);
					attachMapper.insert(i);
				}
			}
			
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
	
	
	
	// 수정하는데 기부회차 정보가 있으면, 회차정보도 상태 동일하게 수정, 썸네일도 수정
	public void modify(Board board) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try{
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			if(board.getDrno() != null) {
				DonateMapper donateMapper = session.getMapper(DonateMapper.class);
				DonateRound round = findRound(board.getDrno());
				round.setStatus(board.getStatus());
				donateMapper.updateRound(round);
				
			}
			if(board.getAttach() != null) {
				if(attachMapper.selectOne(board.getBno()) != null) {
					String removeImg = attachMapper.selectOne(board.getBno()).getS3Key();
					S3Util.remove(removeImg);
					attachMapper.update(board.getAttach());
				}
				else {
					Attach img = board.getAttach();
					img.setBno(board.getBno());
					attachMapper.insert(img);
				}				
			}
			
			if(board.getImages() != null) {
				List<Attach> modifyImgs = modifyViewImgList(board.getImages(), attachMapper.findByViewBno(board.getBno()));
				log.info("{}", modifyImgs);
				for(Attach i : modifyImgs) {
					i.setViewbno(board.getBno());
					attachMapper.insert(i);
				}
			}
				
			mapper.update(board);
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
	
	
	
	// 게시글, 회차정보 Status값 delete로 변경
	public void remove(Long bno) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			DonateMapper donateMapper = session.getMapper(DonateMapper.class);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			Board board = findByBno(bno);
			if(board.getDrno() != null) {
				DonateRound round = donateMapper.selectOneRound(board.getDrno());
				round.setStatus(Status.DELETE);
				donateMapper.updateRound(round);
			}
			List<Attach> viewImgs = new ArrayList<>();

			if(findAttach(bno) != null) {
				viewImgs.add(findAttach(bno));
			}
			if(attachMapper.findByViewBno(bno) != null) {
				viewImgs.addAll(attachMapper.findByViewBno(bno));
			}

			if(!viewImgs.isEmpty()) {
				List<String> keys = viewImgs.stream()
						.map(Attach::getS3Key)
						.toList();

				S3Util.removeAll(keys);
				attachMapper.deleteByBno(bno);
			}



			board.setStatus(Status.DELETE);
			mapper.update(board);
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
	
	// 전체 리스트
	public List<Board> list(Criteria cri) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class); 
			CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);

			List<Board> list = mapper.list(cri);
			for(Board b : list) {
				b.setCtype(categoryMapper.selectOne(b.getCno()).getCtype());
				b.setCname(findCname(b.getCno()));
				b.setNickname(findNickname(b.getMno()));
				b.setName(findName(b.getMno()));
				b.setThumbnail(findThumbnail(b.getBno()));
				b.setAttach(findAttach(b.getBno()));
				if(b.getDrno() != null) {
					b.setRound(findRound(b.getDrno()));
				}
			}
			
			return list;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	

	
	
	// 게시글 개수 가져오기
	public long getCount(Criteria cri) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.getCount(cri);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	// bno로 게시글 가져오기
	public Board findByBno(Long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);

			Board board = mapper.selectOne(bno);
			board.setThumbnail(findThumbnail(bno));
			board.setAttach(findAttach(bno));
			board.setNickname(findNickname(board.getMno()));
			board.setName(findName(board.getMno()));
			if(board.getDrno() != null) {
				board.setRound(findRound(board.getDrno()));
			}
			
			return board;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	// 썸네일 가져오기
	public String findThumbnail(Long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()){
			if (bno == null) return null;
		    
			AttachMapper mapper = session.getMapper(AttachMapper.class);
		    String thumbnail = mapper.getBoardThumbnail(bno);
			return thumbnail;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	// 첨부파일 가져오기
	public Attach findAttach(Long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()){
			if (bno == null) return null;
			
			AttachMapper mapper = session.getMapper(AttachMapper.class);
			
			Attach attach = null; 
			if(mapper.selectOne(bno) != null) {
				attach = mapper.selectOne(bno); 
			}
					
			return attach;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	// 회차정보 가져오기
		public DonateRound findRound(Long drno) {
			try(SqlSession session = MybatisUtil.getSqlSession()){
				if (drno == null) return null;
			    
				DonateMapper mapper = session.getMapper(DonateMapper.class);
			    DonateRound round = mapper.selectOneRound(drno);
				DonateService donateService = new DonateService();
				round.setTop3(donateService.findTop3(drno));

				return round;
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
	// 카테고리명 가져오기
		public String findCname(Integer cno) {
			try(SqlSession session = MybatisUtil.getSqlSession()){
				if (cno == null) return null;
			    
				CategoryMapper mapper = session.getMapper(CategoryMapper.class);
			    Category cate = mapper.selectOne(cno);
				return cate.getCname();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
	// 작성자명 가져오기
		public String findName(Long mno) {
			try(SqlSession session = MybatisUtil.getSqlSession()){
				if (mno == null) return null;
			    
				MemberMapper mapper = session.getMapper(MemberMapper.class);
			    Member member = mapper.findByMno(mno);

			    return member.getName();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
	
		// 닉네임 가져오기
		public String findNickname(Long mno) {
			try(SqlSession session = MybatisUtil.getSqlSession()){
				if (mno == null) return null;
			    
				MemberMapper mapper = session.getMapper(MemberMapper.class);
			    Member member = mapper.findByMno(mno);
			    if(member.getNickname() == null) {
			    	return null;
			    }
			    return member.getNickname();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
		
	// 댓글 개수 가져오기
	public int getReplyCount(Long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			int count = mapper.getReplyCount(bno);
			
			return count;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}	
	
	// 마감임박 게시글 가져오기
		public Board findByDeadline() {
			try(SqlSession session = MybatisUtil.getSqlSession()) {
				BoardMapper mapper = session.getMapper(BoardMapper.class); 

				Board board = mapper.selectOneDeadline();
				board.setRound(findRound(board.getDrno()));
				board.setThumbnail(findThumbnail(board.getBno()));
				board.setName(findName(board.getMno()));
				
				return board;
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}

	// 마감임박 게시글 가져오기
		public List<Board> findNewList() {
			try(SqlSession session = MybatisUtil.getSqlSession()) {
				BoardMapper mapper = session.getMapper(BoardMapper.class); 
				List<Board> newBoards = mapper.listNew();
				for(Board b : newBoards) {
					b.setRound(findRound(b.getDrno()));
					b.setName(findName(b.getMno()));
					b.setThumbnail(findThumbnail(b.getBno()));
				}
				
				return newBoards;
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
	// 이미지 제거한 컨텐츠 가져오기
		public String removeImgContent(String content) {
			if (content == null) return "";
		    return content.replaceAll("!\\[.*?\\]\\(.*?\\)", "");
		}

		
		
	// 원본 게시글과 수정 게시글 비교하여 변경사항 삭제, 기존에 있던것은 리스트 유지
		public List<Attach> modifyViewImgList(List<Attach> newList, List<Attach> originList){
			try (SqlSession session = MybatisUtil.getSqlSession()){
				AttachMapper attachMapper = session.getMapper(AttachMapper.class);
				log.info("{}", newList);
				List<Attach> modifyImgList = new ArrayList<Attach>();
				List<Attach> uselist = new ArrayList<>();
				for(Attach i : newList) {
					if(!originList.contains(i)) {
						modifyImgList.add(i);
					}else {
						uselist.add(i);
					}	
				}
				
				originList.removeAll(uselist);
				
				for(Attach a : originList) {
					S3Util.remove(a.getS3Key());
					attachMapper.delete(a.getUuid());
				}
				log.info("{}", modifyImgList);
				
				return modifyImgList;
		
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
	
	// 메인 - 공지사항 세개 불러오기
	public List<Board> findNoticeList() {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			List<Board> notices = mapper.listNotice();

			return notices;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	// 메인 - Q&A 세개 불러오기
	public List<Board> findQnaList() {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			List<Board> qnas = mapper.listQna();

			return qnas;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	// mno로 게시글 세개 가져오기
	public List<Board> findMnoDonateList(Long mno) {
		try(SqlSession session = MybatisUtil.getSqlSession()) {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			List<Board> myDonates = mapper.listByMnoDonate(mno);
			for(Board b : myDonates) {
				b.setThumbnail(findThumbnail(b.getBno()));
				b.setName(findName(b.getMno()));
			}

			return myDonates;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
