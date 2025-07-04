package controller.pay;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;
import util.JsonRespUtil;
@Slf4j
@WebServlet("/api/payment/complete")
public class PaymentCompleteServlet extends HttpServlet{
	private static final String API_SECRET;
	static {
	Properties props = new Properties();
	
	try(InputStream is = Thread.currentThread()
			.getContextClassLoader()
			.getResourceAsStream("secret/portone.properties")){
		if(is == null) {
			throw new FileNotFoundException("Cannot find portone.properties in classpath");
			}
			props.load(is);
			} catch (IOException e) {
			e.printStackTrace();
			}

	 API_SECRET = props.getProperty("portone.secret");
	}
    
	
    static class PaymentRequest {
        public String paymentId;
    }
    
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	PaymentRequest data = JsonRespUtil.readJson(req, PaymentRequest.class);
    	String paymentId = data.paymentId;
    	
    	try {
            // 2. 토큰 발급
            String token = getAccessToken();
            

            // 3. PortOne 결제 조회 API 호출
            URL url = new URL("https://api.portone.io/payments/" + paymentId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                JsonObject result = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonObject();
                log.info("{}",result);
                String id = result.get("status").getAsString();
                String status = result.get("status").getAsString(); // 예: PAID, FAILED
                
                // Object o = map;
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> fullMap = gson.fromJson(result, type);
                // 4. 응답 JSON 전송

                JsonRespUtil.writeJson(resp, fullMap);
            } else {
                JsonRespUtil.writeJson(resp, Map.of("msg", "결제 조회 실패"), 500);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JsonRespUtil.writeJson(resp, Map.of("msg", "서버 오류", "error", e.getMessage()), 500);
        }
	}
    
    private String getAccessToken() throws IOException {
        // 1. 요청 URL 설정
        URL url = new URL("https://api.portone.io/login/api-secret");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // 2. HTTP 요청 설정
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        // 3. 요청 바디 구성
        JsonObject json = new JsonObject();
        json.addProperty("apiSecret", API_SECRET);

        // 4. 요청 전송
        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.toString().getBytes(StandardCharsets.UTF_8));
        }

        // 5. 응답 코드 확인 후 InputStream 선택
        int code = conn.getResponseCode();
        InputStream input = (code >= 200 && code < 300) ? conn.getInputStream() : conn.getErrorStream();

        // 6. 응답 JSON 파싱 및 access_token 추출
        try (InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8)) {
            JsonObject response = JsonParser.parseReader(isr).getAsJsonObject();

            // 🔍 디버깅용 로그 출력
            System.out.println("🟢 PortOne 응답: " + response);

            // 7. access_token 존재 여부 확인
            if (response.has("accessToken")) {
                return response.get("accessToken").getAsString();
            } else {
                throw new IOException("❌ accessToken 누락 - PortOne 응답 오류: " + response.toString());
            }
        }
    }

}
