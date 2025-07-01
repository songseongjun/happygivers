package mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Param;

import domain.EmailCheck;

public interface EmailCheckMapper {
//인증정보저장
	void insertCheck(@Param("email") String email,
            @Param("uuid") String uuid,
            @Param("voiddate") LocalDateTime voiddate);
//uuid로인증정보저장
EmailCheck selectByUUID(String uuid);

EmailCheck selectByEmail(String email);

void updateCheck(String email);
}
