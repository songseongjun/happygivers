package controller.reply;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import domain.Member;
import domain.Reply;
import lombok.extern.slf4j.Slf4j;
import service.ReplyService;

import util.JsonRespUtil;

@Slf4j
@WebServlet ("/reply/*")
public class ReplyServlet extends HttpServlet{
	public static final String ID = "/reply/";
	
	private String getURI(HttpServletRequest req) {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.indexOf(ID) + ID.length());
		return uri;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = getURI(req);
		HttpSession session = req.getSession(false);
		Member member = (Member) session.getAttribute("member");

		ReplyService service = new ReplyService();
		Gson gson = new Gson(); 
		Object o = null;
		if(uri.startsWith("list") || uri.equals("*")) { // 목록조회
			String tmp = "list/";
			if(uri.contains(tmp)) {
				String[] tmps = uri.split("/");
				Long bno = null;
				Long lastRno = null;
				if(tmps.length > 1) {
					bno = Long.valueOf((tmps[1]));
					if(tmps.length > 2) {
						lastRno = Long.valueOf(tmps[2]);
					}
					o = service.list(bno, member.getMno(),lastRno);
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
		Long rno = Long.valueOf(uri);
		
		new ReplyService().remove(rno);
		
		JsonRespUtil.writeJson(resp,Map.of("result", true));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		Reply reply = JsonRespUtil.readJson(req, Reply.class);
		ReplyService service = new ReplyService();
		service.register(reply);
		Reply resultReply = service.findBy(reply.getRno()); 
		
		JsonRespUtil.writeJson(resp, Map.of("result", true, "reply", resultReply));
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Reply reply = JsonRespUtil.readJson(req, Reply.class);
		new ReplyService().modify(reply); 
		JsonRespUtil.writeJson(resp, Map.of("result", true));
	}
	
	
}
