package mapper;

import domain.Donate;


public interface DonateMapper {
	void insert(Donate donate);
	void delete(Long dno);
	void update(Donate donate);
	Donate selectOne(Long dno);
}
