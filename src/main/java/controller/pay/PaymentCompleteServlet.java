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
import java.util.ArrayList;
import java.util.List;
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

import domain.DonateAction;
import domain.Pay;
import domain.PayLog;
import domain.en.PayStatus;
import domain.en.PayType;
import lombok.extern.slf4j.Slf4j;
import service.PayService;
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
            
            JsonObject result = null;
            if (responseCode == 200) {
                result = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonObject();
                log.info("{}",result);
                
                String status = result.get("status").getAsString(); // 예: PAID, FAILED
                log.info("{}",status);
                	
            	// 커스텀 데이터 파싱
            	String customDataRaw = result.get("customData").getAsString();
            	
            	JsonObject customData = JsonParser.parseString(customDataRaw).getAsJsonObject();
            	
            	// donateaction 인스턴스 생성
            	long drno = customData.get("drno").getAsLong();
            	long mno = customData.get("mno").getAsLong();
            	
            	int realAmount = 0;
            	if (result.has("amount") && result.get("amount").isJsonObject()) {
            	    JsonObject amountObj = result.getAsJsonObject("amount");
            	    if (amountObj.has("total") && !amountObj.get("total").isJsonNull()) {
            	        String totalStr = amountObj.get("total").getAsString().split("\\.")[0];
            	        realAmount = Integer.parseInt(totalStr);
            	    }
            	}
            	int amount = realAmount * 1000;
            	DonateAction action = DonateAction.builder().drno(drno).mno(mno).amount(amount).build();
            	
            	
            	// pay 인스턴스 생성
            	String paytype = "ETC";
            	if(result.get("method") != null) {
            		if(result.get("method").getAsJsonObject().get("provider") != null) {
            			paytype = result.get("method").getAsJsonObject().get("provider").getAsString();
            		}
            	}
            			
            		
            		
            	List<String> useTypes = new ArrayList<String>(List.of("CARD", "TOSSPAY", "KAKAOPAY", "TRANSFER")); 
            	if(!useTypes.contains(paytype)) {
            		paytype = "ETC";
            	}
            	// 영수증 없으면 널값
            	String receipt = ""; 
            	if(result.get("receiptUrl") != null) {
            		receipt = result.get("receiptUrl").getAsString();
            	}
            	
            	String uuid = result.get("id").getAsString();
            	Pay pay = Pay.builder().mno(mno).payamount(amount).paytype(PayType.valueOf(paytype)).paystatus(PayStatus.valueOf(status)).receipt(receipt).uuid(uuid).build();
            	log.info("{}", pay);
            	// paylog 인스턴스 생성
            	String pgResponseRaw = "";
            	String resultMsg = "";
            	if(result.get("pgResponse") != null) {
            		pgResponseRaw = result.get("pgResponse").getAsString();
            		JsonObject pgResponse = JsonParser.parseString(pgResponseRaw).getAsJsonObject();
            		resultMsg = pgResponse.get("ResultMsg").getAsString();
            	}
            		
            	PayLog paylog = PayLog.builder().paystatus(PayStatus.valueOf(status)).result(resultMsg).build();
            	
            	
            	log.info("{} :: {} :: {}", action, pay, paylog);
            	
            	PayService payService = new PayService();
            	payService.register(action, pay, paylog);
            }
            
            
            // Object o = map;
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> fullMap = gson.fromJson(result, type);
            // 4. 응답 JSON 전송

            JsonRespUtil.writeJson(resp, fullMap);
           

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
        log.info("3 :: {}", json);
        // 4. 요청 전송
        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.toString().getBytes(StandardCharsets.UTF_8));
        }
        log.info("4");
        // 5. 응답 코드 확인 후 InputStream 선택
        int code = conn.getResponseCode();
        InputStream input = (code >= 200 && code < 300) ? conn.getInputStream() : conn.getErrorStream();
        log.info("{}", code);
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
