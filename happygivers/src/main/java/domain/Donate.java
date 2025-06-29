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
@Alias("donate")
public class Donate {
	private Long dno;
	private Long bno;
	private Long mno;
	private int goalamount;
	private String regdate;
	private String voiddate;
}
