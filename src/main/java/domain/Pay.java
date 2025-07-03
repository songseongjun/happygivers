package domain;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import domain.en.PayStatus;
import domain.en.PayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("pay")
public class Pay {
	private Long pno;
	private Long dano;
	private Long mno;
	private BigDecimal payamount;
	private PayType paytype;
	private PayStatus paystatus;
	private String confirm;
	private String receipt;
	private String uuid;
	private String regdate;
	private String voiddate;
	private String moddate;
	
	
}
