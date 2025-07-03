package mapper;


import java.util.List;


import domain.Pay;

public interface PayMapper {
	void insert(Pay pay);
	void delete(Long pno);
	void update(Pay pay);
	
	
	List<Pay> list(Long drno);

	Pay selectOne(Long pno);
}
