package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Board;
import domain.Donate;
import domain.Member;
import domain.en.Status;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberServiceTest {
	MemberService service;

	@BeforeEach
	void setUp() {
		service = new MemberService();
	}

	@Test
	@DisplayName("로그인")
	public void testLogin() {
		String id = "admin";
		String pw = "1234";
		service.login(id, pw);
	}

}
