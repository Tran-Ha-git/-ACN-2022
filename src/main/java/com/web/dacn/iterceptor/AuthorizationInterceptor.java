package com.web.dacn.iterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.web.dacn.entity.user.User;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor{
	@Autowired
	private HttpSession session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		User user = (User) session.getAttribute("user");
		Boolean isAdmin = (Boolean) session.getAttribute("ad");
				
		if(user!=null && isAdmin != null && isAdmin.booleanValue()) {
			return true;
		}
		
		String url = request.getRequestURI();
		
		if(url.startsWith("/admin")){
			response.sendRedirect("/home") ;
		}
		
		return true;
	}
	
	
}
