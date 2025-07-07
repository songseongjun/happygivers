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
public class MailUtil {
    	   // 메일 전송용 메서드
        public static void sendEmail(String to, String subject, String content) {
            // Gmail SMTP 서버 정보
            final String host = "smtp.gmail.com";
            final String user = "thdtjdwns0902@gmail.com";      // 보내는 사람 이메일
            final String password = "jaxbyqyodienwksj";         // 앱 비밀번호

            // SMTP 속성 설정
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true"); // TLS 보안

            // 세션 생성
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

            
            try {
                // 이메일 메시지 생성
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(user));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject(subject);

                // HTML 형식으로 메일 내용 설정
                message.setContent(content, "text/html; charset=utf-8");

                // 메일 전송
                Transport.send(message);
                System.out.println("메일 전송 완료: " + to);

            } catch (MessagingException e) {
                e.printStackTrace(); // 에러 출력
                throw new RuntimeException("메일 전송 실패: " + e.getMessage());
            }
        }
        public static void main(String[] args) {
            MailUtil.sendEmail("thdtjdwns@gmail.com", "테스트 제목", "테스트 본문<br>이건 HTML입니다.");
        }

        
    }