package mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import domain.EmailCheck;

public interface EmailCheckMapper {
	  List<EmailCheck> selectByEmail(String email);
//인증정보저장
	void insertCheck(@Param("email") String email,
            @Param("uuid") String uuid,
            @Param("voiddate") LocalDateTime voiddate);
//uuid로인증정보저장
EmailCheck selectByUUID(String uuid);

void updateCheck(String email);

}
