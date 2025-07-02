package service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.EmailCheck;
import mapper.EmailCheckMapper;
import util.MybatisUtil;

//이메일 DB처리서비스
public class EmailCheckService {

				//이메일 인증 요청 저장
    public void save(String email, String uuid, LocalDateTime voiddate) {
        try (SqlSession session = MybatisUtil.getSqlSession()) {
            EmailCheckMapper mapper = session.getMapper(EmailCheckMapper.class);//매퍼객체저장함
            mapper.insertCheck(email, uuid, voiddate);//tbl_email_check테이블에 저장
            session.commit();
        }
    }
    		//인증 링크 클릭시 호출되는 메서드
    public boolean verifyUUID(String uuid) {
        try (SqlSession session = MybatisUtil.getSqlSession()) {
            EmailCheckMapper mapper = session.getMapper(EmailCheckMapper.class);//매퍼객체호출
            EmailCheck check = mapper.selectByUUID(uuid);//uuid로 해당 정보 조회
            if (check != null && check.getVoiddate().isAfter(LocalDateTime.now())) {//존재한유효시간이내라면
                mapper.updateCheck(check.getEmail());// 인증 완료 처리 (check = 1)
                session.commit();
                return true; //인증 성공
            }
        }
        return false;
    }
    					//이메일 인증 됐는지 확인
    public boolean isVerified(String email) {
        try (SqlSession session = MybatisUtil.getSqlSession()) {
            EmailCheckMapper mapper = session.getMapper(EmailCheckMapper.class);
            List<EmailCheck> checks = mapper.selectByEmail(email);//이메일로 인증 정보 조회
            if(checks !=null && !checks.isEmpty()) {
            	EmailCheck check = checks.get(0);
            	return check.getCheck() == 1;  // 인증 완료되었는지 확인
            }
            
        }
        return false;
    }
    
    
}
