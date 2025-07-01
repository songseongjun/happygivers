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
@Alias("category")
public class Category {
	private Integer cno;
	private String cname;
	private String regdate;
	private Integer odr;
	private Status status;
}
