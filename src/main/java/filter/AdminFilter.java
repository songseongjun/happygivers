package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import domain.en.Mtype;

@WebFilter("/admin/*")
public class AdminFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;


		Object o = req.getSession().getAttribute("member");
//		if(o == null || !(o instanceof Member)) {
//			res.setStatus(401);
//			res.sendRedirect("index");
//			return;
//		}
//		Member member = (Member) o;
//		if(member.getMtype() != Mtype.ADMIN || member.getMtype() != Mtype.MANAGER) {
//			res.setStatus(403);
//			res.sendRedirect("index");
//			return;
//		}
				
		
		chain.doFilter(request, response);
	}
}