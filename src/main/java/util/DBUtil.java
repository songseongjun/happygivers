package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getConnection() throws Exception {
    	String url = "jdbc:mariadb://np.gitbaby.com:3306/happy"; // DB 주소
        String user = "happy";      // DB 사용자 ID
        String password = "1234";  // DB 비밀번호

        Class.forName("org.mariadb.jdbc.Driver"); // JDBC 드라이버 로드
        return DriverManager.getConnection(url, user, password); // 연결 반환
    }
}
