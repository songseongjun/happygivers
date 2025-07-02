package service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MailUtil {
	

	@Test
	@DisplayName("모금함 없는 글 생성")
	public void testSendEmail() {
		String target = "thdtjdwns0902@gmail.com";
		String title = "테스트 제목";
		String content = "테스트 본문입니다.";
		util.MailUtil.sendEmail(target, title, content);
	}
	
}	
	

