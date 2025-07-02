package util;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


//public class MailUtil {
//	public static void main(String[] args) {
//		sendEmail("thdtjdwns0902@gmail.com", "송상준이 발송하고 송상준이 받음", "내용없음");
//	}
//	
//	
//    // 메일 전송용 메서드
//    public static void sendEmail(String to, String subject, String text) {
//        // 보내는 사람 이메일 계정
//        final String username = "thdtjdwns0902@gmail.com"; // 예: test@gmail.com
//        final String password = "dbswnhlfquazwlnm"; // 구글 2차 비밀번호 아님, 앱 비밀번호 따로 발급받아야 함
//
//        // 메일 전송에 필요한 SMTP 정보 설정
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", true);  // 인증 필요
//        props.put("mail.smtp.starttls.enable", true); // TLS 보안 연결
//        props.put("mail.smtp.host", "smtp.gmail.com"); // Gmail SMTP 서버
//        props.put("mail.smtp.port", "587"); // 포트 번호
//
//        // 메일 세션 생성
//        Session session = Session.getInstance(props,
//            new Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(username, password);
//                }
//            });
//
//        try {
//            // 이메일 메시지 생성
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username)); // 보내는 사람
//            message.setRecipients(
//                Message.RecipientType.TO,
//                InternetAddress.parse(to) // 받는 사람
//            );
//            message.setSubject(subject); // 제목
//            message.setText(text); // 내용
//
//            // 메일 전송
//            Transport.send(message);
//
//            System.out.println("메일 전송 완료");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//}

public class MailUtil {
    public static void sendEmail(String to, String subject, String content) {
        String host = "smtp.gmail.com";
        final String user = "thdtjdwns0902@gmail.com";  // Gmail 계정
        final String password = "jaxbyqyodienwksj";         // 앱 비밀번호

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        System.out.println("메일 전송 시도 중: " + to);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);

            // html형식
            String html = "<h3>아래 링크를 클릭하세요</h3>" +
                          "<a href='" + content + "' target='_blank'>" + content + "</a>";

            message.setContent(html, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("메일 전송 완료");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    } 

    public class MailTest {
        public static void main(String[] args) {
            MailUtil.sendEmail("thdtjdwns0902@gmail.com", "테스트 제목", "테스트 본문입니다.");
        }
    }

}
