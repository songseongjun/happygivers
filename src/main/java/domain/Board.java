package domain;




import org.apache.ibatis.type.Alias;

import domain.en.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("board")
public class Board {
	private Long bno; //게시글 번호
	private String title; // 제목
	private String content; // 내용
	private String regdate; // 생성일
	private String moddate; // 수정일
	private Long mno; // 작성자
	private int cno; // 카테고리 번호
	private int cnt; // 조회수
	private Status status; // 게시글 상태
	private Long drno; // 연결된 모금회차 고유번호

	
	private String thumbnail; // 썸네일
	private DonateRound round; // 라운드 개체
	private String cname; // 카테고리명
	private String name; // 작성자d 이름
	private String nickname; // 작성자 닉네임
}
