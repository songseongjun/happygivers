package controller.pay;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import domain.Pay;
import lombok.extern.slf4j.Slf4j;
import service.PayService;


import util.JsonRespUtil;

@Slf4j
@WebServlet ("/payment/*")
public class PayServlet extends HttpServlet{
	public static final String ID = "/pay/";
	
	private String getURI(HttpServletRequest req) {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.indexOf(ID) + ID.length());
		return uri;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = getURI(req);
		PayService service = new PayService();
		Gson gson = new Gson(); 
		Object o = null;
		if(uri.startsWith("complete")) { // 목록조회
			log.info("{}", uri);
			String tmp = "complete/";
			if(uri.contains(tmp)) {
				String[] tmps = uri.split("/");
				Long drno = null;
				if(tmps.length > 1) {
					drno = Long.valueOf((tmps[1]));
					
					o = service.list(drno);
					log.info("{}", o);
				}
			}
		}
		else { // 단일조회
			o = service.findBy(Long.parseLong(uri));
		}
		JsonRespUtil.writeJson(resp, o);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = getURI(req);
		Long pno = Long.valueOf(uri);
		
		new PayService().remove(pno);
		
		JsonRespUtil.writeJson(resp,Map.of("result", true));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Pay pay = JsonRespUtil.readJson(req, Pay.class);
		
		new PayService().register(pay);
		log.info("{}", pay);
		JsonRespUtil.writeJson(resp, Map.of("result", true, "pay", pay));
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Pay pay = JsonRespUtil.readJson(req, Pay.class);
		new PayService().modify(pay); 
		log.info("{}", pay);
		JsonRespUtil.writeJson(resp, Map.of("result", true));
	}
	
	
}
