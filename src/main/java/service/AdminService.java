package service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import domain.Member;
import domain.ApprLog;
import mapper.AdminMapper;
import util.MybatisUtil;

public class AdminService {

    public List<Member> getPendingOrgMembers() {
        try (SqlSession session = MybatisUtil.getSqlSession()) {
            return session.getMapper(AdminMapper.class).selectPendingOrgMembers();
        }
    }
    				//기관회원승인여부
    public boolean approveOrReject(long mno, int appr, String reason, long admno) {
        try (SqlSession session = MybatisUtil.getSqlSession()) {
            AdminMapper mapper = session.getMapper(AdminMapper.class);

            String status = (appr == 1) ? "ACTIVE" : "DISABLED";  // 상태값은 String
            int result1 = mapper.updateMemberStatus(mno, status); // 결과는 int


            ApprLog log = new ApprLog();
            log.setMno(mno);
            log.setAppr(appr);
            log.setReason(reason);
            log.setAdmno(admno);
            int result2 = mapper.insertApprLog(log);

            session.commit();
            return result1 > 0 && result2 > 0;
        }
    }
}