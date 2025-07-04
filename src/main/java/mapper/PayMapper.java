package mapper;


import java.util.List;


import domain.Pay;
import domain.PayLog;

public interface PayMapper {
	void insert(Pay pay);
	void delete(Long pno);
	void update(Pay pay);
	
	
	List<Pay> list(Long drno);

	Pay selectOne(Long pno);

	void insertLog(PayLog log);
	void deleteLog(Long plno);
	void updateLog(PayLog log);
	
	
	List<PayLog> listLog(Long plno);
	
	PayLog selectOneLog(Long plno);
}
