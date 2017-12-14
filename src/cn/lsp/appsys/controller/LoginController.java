package cn.lsp.appsys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.lsp.appsys.pojo.backendUser;
import cn.lsp.appsys.pojo.devUser;
import cn.lsp.appsys.service.BackendUserService;
import cn.lsp.appsys.service.DevUserService;

@Controller
@RequestMapping("/gol")
public class LoginController {

	private Logger logger = Logger.getLogger(LoginController.class);
	
	@Resource(name="devuserservice")
	private DevUserService devUserService;
	
	
	@Resource(name="backenduserservice")
	private BackendUserService service;
	
	//主页面
		@RequestMapping(value="/index.html",method=RequestMethod.GET)
		public String index(){
			return "index";
		}
		
		//403页面
		@RequestMapping(value="/403",method=RequestMethod.GET)
		public String _403(){
			return "403";
		}
		
		//开发者登录页面
		@RequestMapping(value="/login.html",method=RequestMethod.GET)
		public String Login(){
			return "jsp/devlogin";
		}
		
		//验证登录
		@RequestMapping(value="/dologin.html",method=RequestMethod.POST)
		public String doLogin(@RequestParam String devCode,@RequestParam String devPassword,HttpSession session,HttpServletRequest request){
			devUser devUser = devUserService.Login(devCode, devPassword);
			if(null != devUser){
				session.setAttribute("devUser", devUser);
				logger.debug("==========> 开发者登录成功!");
				return "jsp/developer/main";
			}
			logger.debug("==========> 开发者登录失败!");
			request.setAttribute("error", "用户名或密码错误!");
			return "jsp/devlogin";
		}
		
		//退出登录
		@RequestMapping(value="/logoff.html",method=RequestMethod.GET)
		public String Logoff(HttpSession session){
			session.removeAttribute("devUser");
			return "redirect:/gol/index.html";
		}
		
		//注销开发者页面
		@RequestMapping(value="/logout.html",method=RequestMethod.GET)
		public String Logout(HttpSession session){
			session.removeAttribute("devUser");
			return "jsp/devlogin";
		}
	
		
		//分界线
		//分界线
		//分界线
		
		
		//后台用户登录页面
		@RequestMapping(value="/Login",method=RequestMethod.GET)
		public String LoginH(){
			return "jsp/backendlogin";
		}
		
		//验证登录
		@RequestMapping(value="/goLogin",method=RequestMethod.POST)
		public String goLogin(@RequestParam String userCode,@RequestParam String userPassword,HttpSession session,Model model,HttpServletRequest request){
			logger.debug("========>进入后台登录验证：" + userCode + "," + userPassword);
			backendUser bkUser = service.Login(userCode, userPassword);
			if(null != bkUser){
				session.setAttribute("userSession", bkUser);
				logger.debug("==========>后台用户登录成功!");
				return "jsp/backend/main";
			}
			logger.debug("==========>后台用户登录失败!");
			request.setAttribute("error", "用户名或密码错误!");
			return "jsp/backendlogin";
		}
		
		//注销开发者页面
		@RequestMapping(value="/logout",method=RequestMethod.GET)
		public String LogoutH(HttpSession session){
			session.removeAttribute("userSession");
			return "redirect:/Backend/Login";
		}
		
}
