package service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Board;
import domain.Donate;
import domain.en.Status;



public class BoardServiceTest {
	BoardService service;
	
	@BeforeEach
	void setUp() {
		service = new BoardService();
	}
	
	@Test
	@DisplayName("모금함 없는 글 생성")
	public void testWrite() {
		String title = "junit 테스트 - 모금함 없음";
		String content = "junit 테스트";
		Long mno = 1L;
		int cno = 6;
		Status status = Status.ACTIVE;
		
		Board board = Board.builder().title(title).content(content).mno(mno).cno(cno).status(status).build();
		
		service.write(board);
	}

	@Test
	@DisplayName("모금함 있는 글 생성")
	public void testWriteDonate() {
		String title = "junit 테스트 - 모금함 있음";
		String content = "junit 테스트";
		Long mno = 1L;
		int cno = 7;
		Status status = Status.ACTIVE;
		int goalamount = 242456;
		String voiddate = "2025-08-04";
		
		
		Board board = Board.builder().title(title).content(content).mno(mno).cno(cno).status(status).build();
		Donate donate = Donate.builder().mno(mno).goalamount(goalamount).voiddate(voiddate).build();
		
		service.write(board, donate);
	}
}
