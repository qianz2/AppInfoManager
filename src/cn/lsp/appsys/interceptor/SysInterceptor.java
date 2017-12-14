package cn.lsp.appsys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.lsp.appsys.pojo.app_info;
import cn.lsp.appsys.pojo.backendUser;
import cn.lsp.appsys.pojo.devUser;


public class SysInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(SysInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		HttpSession session = request.getSession();
		
		devUser devuser = (devUser)session.getAttribute("devUser");
		//backendUser userSession = (backendUser)session.getAttribute("userSession");
		
		if(null == devuser){
			response.sendRedirect(request.getContextPath()+"/gol/403");
			return false;
		}
		return true;
	}
}
