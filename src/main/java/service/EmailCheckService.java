package service;

import org.apache.ibatis.session.SqlSession;

import domain.Member;
import mapper.MemberMapper;
import util.MybatisUtil;
import util.RedisUtil;

// 이메일 Redis 처리 + DB 인증 처리 서비스
public class EmailCheckService {

    // 인증 토큰 저장
    public void saveToken(String uuid, String email) {
        RedisUtil.set(uuid, email, 600); // 10분 TTL
    }

    // 인증 토큰 존재 여부 확인
    public boolean isVerified(String uuid) {
        return RedisUtil.exists(uuid);
    }

    // 토큰으로 이메일 꺼내기
    public String getEmail(String uuid) {
        return RedisUtil.get(uuid);
    }

    // 토큰 삭제 (1회용)
    public void removeToken(String uuid) {
        RedisUtil.remove(uuid);
    }

    //  인증 성공 처리: emailcheck = 1 + email_check.check = 1
    public boolean verifyEmail(String uuid) {
        SqlSession session = MybatisUtil.getSqlSession();
        try {
            MemberMapper mapper = session.getMapper(MemberMapper.class);

            // 1. uuid로 이메일 꺼냄
            String email = RedisUtil.get(uuid);
            if (email == null) return false;

            // 2. tbl_member.emailcheck = 1
            mapper.updateEmailCheckByEmail(email);

            // 3. tbl_email_check.check = 1
            mapper.verifyEmail(uuid);

            session.commit();

            // 4. Redis 인증 토큰 삭제
            RedisUtil.remove(uuid);
            System.out.println("인증 시작 - uuid: " + uuid);
            System.out.println("이메일 인증 처리 - email: " + email);

            return true;
            
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    // 인증된 사용자 조회 (세션 등록용)
    public Member getMemberByEmail(String email) {
        SqlSession session = MybatisUtil.getSqlSession();
        try {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            return mapper.selectByEmail(email);
        } finally {
            session.close();
        }
    }
}
