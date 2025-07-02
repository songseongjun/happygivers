package service;

import util.RedisUtil;

//이메일 Redis처리서비스  uuid=인증토큰
public class EmailCheckService {
	
	 // 인증 토큰 저장: Redis에 (uuid, email) 쌍으로 저장
    public void saveToken(String uuid, String email) {
        // Redis에 uuid라는 키로 email을 저장하고 10분(600초) 뒤에 자동 삭제되도록 설정함
        RedisUtil.set(uuid, email, 600);
    }

    // 인증 토큰 확인: uuid가 유효한지 확인 (Redis에 존재하면 인증됨)
    public boolean isVerified(String uuid) {
        // Redis에서 해당 uuid가 존재하면 인증 완료된 것으로 간주
        return RedisUtil.exists(uuid);
    }

    // 이메일 가져오기 필요한 경우, uuid로 이메일 꺼내오기
    public String getEmail(String uuid) {
        return RedisUtil.get(uuid);
    }

    // 인증 완료되면 토큰 삭제 (재사용 방지용)
    public void removeToken(String uuid) {
        RedisUtil.remove(uuid);
    }
}