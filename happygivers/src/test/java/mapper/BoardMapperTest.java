package mapper;



import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Board;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;

@Slf4j
public class BoardMapperTest {
	private BoardMapper boardMapper = MybatisUtil.getSqlSession().getMapper(BoardMapper.class);
	
	@Test
	@DisplayName("게시판 작성 테스트")
	public void testInsert() {
		// given
		String title = "테스트 입니다.";
		String content = "테스트 내용입니다.";
		Long mno = 1L;
		int cno = 1;
		Status status = Status.ACTIVE;
		
		// when
		Board board = Board.builder().title(title).content(content).mno(mno).cno(cno).status(status).build();
		
		// then ~ actual, expect
		boardMapper.insert(board);
		log.info("{}", board);
	}
	
	@Test
	@DisplayName("bno로 선택 테스트")
	public void testSelectOne() {
		// given
		Long bno = 1L;
		
		// when
		Board board = boardMapper.selectOne(bno);
		
		// then ~ actual, expect
		log.info("{}", board);		
	}

	@Test
	@DisplayName("게시글 전체 리스트")
	public void testList() {
		// when
		List<Board> boards = boardMapper.list();
		
		// then ~ actual, expect
		log.info("{}", boards);		
	}

	@Test
	@DisplayName("게시글 상태변경")
	public void testModifyStatus() {
		// when
		Long Bno = 1L;
		Board board = boardMapper.selectOne(Bno);
		Status status = Status.ACTIVE;
		board.setStatus(status);
		
		boardMapper.update(board);
		// then ~ actual, expect
		log.info("{}", board);		
	}

	@Test
	@DisplayName("게시글 삭제")
	public void testRemove() {
		// when
		Long bno = 1L;
		
		boardMapper.delete(bno);		
	}
	
	
}
