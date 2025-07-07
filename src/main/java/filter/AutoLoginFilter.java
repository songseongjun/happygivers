package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import domain.dao.AutoLoginDAO;
import service.MemberService;

@WebFilter("/*")
public class AutoLoginFilter implements Filter {
	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {

	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse resp = (HttpServletResponse) response;

	        HttpSession session = req.getSession(false);
	        if (session != null && session.getAttribute("member") != null) {
	            chain.doFilter(request, response);
	            return;
	        }

	        Cookie[] cookies = req.getCookies();
	        if (cookies != null) {
	            for (Cookie c : cookies) {
	                if ("remember-me".equals(c.getName())) {
	                    String token = c.getValue();
	                    Long mno = new AutoLoginDAO().findMnoByToken(token);
	                    if (mno != null) {
	                        Member member = new MemberService().findByMno(mno);
	                        HttpSession newSession = req.getSession();
	                        newSession.setAttribute("member", member);
	                        newSession.setMaxInactiveInterval(60 * 60);
	                    }
	                    break;
	                }
	            }
	        }
	        chain.doFilter(request, response);
	    }
	}

