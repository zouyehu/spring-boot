package com.fulan.springboot.filter;

import com.alibaba.fastjson.JSONObject;
import com.fulan.springboot.utils.FileUtils;
import com.fulan.springboot.utils.StrUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

	private static Log log = LogFactory.getLog(LoginFilter.class);

//	@Autowired
//	private RedisService redisService;

	@Autowired
	private FileUtils fileUtils;

	@Override
	public void destroy() {
		log.info("过滤器销毁...");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String requestURI = request.getRequestURI();
		String sessionId = StrUtils.pattern + request.getSession().getId();
//		String sessionIdValue = (String) redisService.get(sessionId);
		String[] sessionV = fileUtils.getUserByFile().split("###");
		String sessionIdValue = null;
		if (sessionV.length>1){
			sessionIdValue = sessionV[1];
		}

		log.info("requestURI:" + requestURI + ",sessionId:" + sessionId + ",sessionIdValue:" + sessionIdValue);
		String args[] = requestURI.split("\\.");

		if (requestURI.equals("/failindex/login.html") || requestURI.equals("/failindex/api/authuser") || requestURI.equals("/failindex/api/getuser")) {
			fc.doFilter(req, res);
		} else {
			if (args.length > 1 && !args[args.length - 1].equals("html")) {
				fc.doFilter(req, res);
			} else {
				if (StrUtils.isEmpty(sessionIdValue)) {
					if (args.length == 1 && !requestURI.equals("/failindex") && !requestURI.equals("/failindex/")) {
						PrintWriter writer = res.getWriter();
						JSONObject json = new JSONObject();
						json.put("code", "99");
						json.put("message", "auth expired");
						writer.write(json.toJSONString());
						writer.flush();
					} else {
						response.sendRedirect("/failindex/login.html");
					}
				} else {
					fc.doFilter(req, res);
				}
			}

		}

	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		log.info("过滤器初始化...");
		fileUtils.setFileUser();
	}

}
