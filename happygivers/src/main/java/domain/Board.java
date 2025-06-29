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
	private Long bno;
	private String title;
	private String content;
	private String regdate;
	private String moddate;
	private Long mno;
	private int cno;
	private int cnt;
	private Status status;
}
