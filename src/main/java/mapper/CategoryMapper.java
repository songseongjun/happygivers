package mapper;



import domain.Category;


public interface CategoryMapper {
	void insert(Category cate);
	void delete(Long cno);
	void update(Category cate);
	
	Category selectOne(Long cno);
}
