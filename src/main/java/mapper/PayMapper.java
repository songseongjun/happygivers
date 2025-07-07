package mapper;


import java.util.List;


import domain.Pay;
import domain.PayLog;

public interface PayMapper {
	void insert(Pay pay);
	void delete(Long pno);
	void update(Pay pay);
	
	// 모금회차 하나의 결제정보
	List<Pay> list(Long drno);
	
	// 전체 결제 정보
	List<Pay> adminPayList();
	
	Pay selectOne(Long pno);
	Pay findByUuid(String uuid);

	void insertLog(PayLog log);
	void deleteLog(Long plno);
	void updateLog(PayLog log);
	
	
	List<PayLog> listLog();
	
	PayLog selectOneLog(Long plno);
}
