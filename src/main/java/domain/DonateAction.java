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
@Alias("donateAction")
public class DonateAction {
	private Long dano; // 후원 번호
	private Long drno; // 후원한 모금회차 번호
	private Long mno; //후원한 회원 번호
	private int amount; // 후원 금액
	private String regdate; // 후원 일시
}
