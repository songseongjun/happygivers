package domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("reply")
public class Reply {
	private Long rno; // 리뷰번호
	private Long bno; // 게시글 번호
	private Long mno; // 멤버 번호
	private String content; // 리뷰 내용
	private String regdate; // 생성일
	
	private String nickname; // 닉네임
	private String name; // 이름
}
