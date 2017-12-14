package cn.lsp.appsys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.lsp.appsys.pojo.app_category;
import cn.lsp.appsys.pojo.app_info;
import cn.lsp.appsys.pojo.app_version;
import cn.lsp.appsys.pojo.backendUser;
import cn.lsp.appsys.pojo.dataDictionary;
import cn.lsp.appsys.service.BackendUserService;
import cn.lsp.appsys.service.DevUserService;
import cn.lsp.appsys.tools.PageSupport;

@Controller
@RequestMapping("/Backend")
public class BackendUserController {

	private Logger logger = Logger.getLogger(BackendUserController.class);
	
	@Resource(name="backenduserservice")
	private BackendUserService service;
	
	@Resource(name="devuserservice")
	private DevUserService devService;
	
	//后台用户登录页面
	@RequestMapping(value="/Login",method=RequestMethod.GET)
	public String Login(){
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
	public String Logout(HttpSession session){
		session.removeAttribute("userSession");
		return "redirect:/Backend/Login";
	}
	
	//ajax联动分类下拉框
	@RequestMapping(value="categorylevellist.json",method=RequestMethod.GET)
	@ResponseBody
	public Object Classification(@RequestParam String pid){
		logger.debug("===========联动分类 pid="+pid);
		List<app_category> categoriesList = null;
		if(pid != null && pid != ""){
			categoriesList = service.MainClassification(Integer.valueOf(pid));
		}else{
			categoriesList = service.OneClassification();
		}
		return JSON.toJSONString(categoriesList);
	}
	
	//APP审核页面
	@RequestMapping(value="/bkUserList")
	public String bkUserList(Model model,
			@RequestParam(value="querySoftwareName",required=false)String querySoftwareName,
			@RequestParam(value="queryFlatformId",required=false)Integer queryFlatformId,
			@RequestParam(value="queryCategoryLevel1",required=false)Integer queryCategoryLevel1,
			@RequestParam(value="queryCategoryLevel2",required=false)Integer queryCategoryLevel2,
			@RequestParam(value="queryCategoryLevel3",required=false)Integer queryCategoryLevel3,
			@RequestParam(value="pageIndex",required=false)String pageIndex){
		logger.debug("=========<>软件名称：" + querySoftwareName);
		logger.debug("=========<>所属平台：" + queryFlatformId);
		logger.debug("=========<>一级分类：" + queryCategoryLevel1);
		logger.debug("=========<>二级分类：" + queryCategoryLevel2);
		logger.debug("=========<>三级分类：" + queryCategoryLevel3);
		
		List<dataDictionary> FlatformName = service.getFlatformName();		//所属平台
		List<app_category> One = service.OneClassification();	//一级分类
		//Integer _parentId = 0;
		List<app_category> Two = service.MainClassification(queryCategoryLevel1);	//二级分类
		List<app_category> Three = service.MainClassification(queryCategoryLevel2);	//三级分类
		
		
		//设置页面容量
    	int pageSize = 5;
    	//当前页码
    	int currentPageNo = 1;
    	if(pageIndex != null){
    		currentPageNo = Integer.valueOf(pageIndex);
    	}
    	if(querySoftwareName == null){
    		querySoftwareName = "";
    	}
		
    	//总数量
    	int count = service.getAppinfoCount(querySoftwareName, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3);
    	logger.debug("=========总数量：" + count);
    	
    	//总页数
    	PageSupport pages=new PageSupport();
    	//设置当前页
    	pages.setCurrentPageNo(currentPageNo);
    	//每页大小
    	pages.setPageSize(pageSize);
    	//传入总条数，算出总页数
    	pages.setTotalCount(count);
    	//获取总页数
    	int totalPageCount = pages.getTotalPageCount();
    	//控制首页和尾页
    	if(currentPageNo < 1){
    		currentPageNo = 1;
    	}else if(currentPageNo > totalPageCount){
    		currentPageNo = totalPageCount;
    	}
    	
    	List<app_info> infoList = service.getAppinfoList(querySoftwareName, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, (currentPageNo-1)*pageSize, pageSize);
    	
		model.addAttribute("flatFormList", FlatformName);
		model.addAttribute("categoryLevel1List", One);
		model.addAttribute("categoryLevel2List", Two);
		model.addAttribute("categoryLevel3List", Three);
		
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("queryFlatformId", queryFlatformId);
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
		
		model.addAttribute("appInfoList", infoList);
		model.addAttribute("pages", pages);
		return "jsp/backend/applist";
	}
	
	//审核页面
	@RequestMapping(value="/appinfocheck")
	public String AppinfoCheck(@RequestParam Integer aid,@RequestParam Integer vid,
			Model model){
		app_info info = devService.infoview(aid);		//APP基础信息
		app_version version = service.getAppversionByappId(aid);		//最新版本信息
		
		model.addAttribute("appVersion", version);
		model.addAttribute("appInfo", info);
		return "jsp/backend/appcheck";
	}
	
	//审核
	@RequestMapping(value="/appinfochecksave")
	public String AppinfoCheckSave(@RequestParam Integer status,@RequestParam Integer id){
		logger.debug("========>审核状态：" + status);
		if(status == 2){
			if(service.AppinfoModifyStatus(id, status) > 0){
				logger.debug("========>审核通过执行成功！");
				return "redirect:/Backend/bkUserList";
			}else {
				logger.debug("========>审核通过执行失败！");
				return "redirect:/Backend/appinfocheck";
			}
		}else if(status == 3){
			if(service.AppinfoModifyStatus(id, status) > 0){
				logger.debug("========>审核不通过执行成功！");
				return "redirect:/Backend/bkUserList";
			}else {
				logger.debug("========>审核不通过执行失败！");
				return "redirect:/Backend/appinfocheck";
			}
		}
		return "";
	}
	
}
package cn.lsp.appsys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.lsp.appsys.pojo.app_category;
import cn.lsp.appsys.pojo.app_info;
import cn.lsp.appsys.pojo.app_version;
import cn.lsp.appsys.pojo.backendUser;
import cn.lsp.appsys.pojo.dataDictionary;
import cn.lsp.appsys.service.BackendUserService;
import cn.lsp.appsys.service.DevUserService;
import cn.lsp.appsys.tools.PageSupport;

@Controller
@RequestMapping("/Backend")
public class BackendUserController {

	private Logger logger = Logger.getLogger(BackendUserController.class);
	
	@Resource(name="backenduserservice")
	private BackendUserService service;
	
	@Resource(name="devuserservice")
	private DevUserService devService;
	
	//扮ㄦ风诲椤甸
	@RequestMapping(value="/Login",method=RequestMethod.GET)
	public String Login(){
		return "jsp/backendlogin";
	}
	
	//楠璇诲
	@RequestMapping(value="/goLogin",method=RequestMethod.POST)
	public String goLogin(@RequestParam String userCode,@RequestParam String userPassword,HttpSession session,Model model,HttpServletRequest request){
		logger.debug("========>杩ュ扮诲楠璇锛" + userCode + "," + userPassword);
		backendUser bkUser = service.Login(userCode, userPassword);
		if(null != bkUser){
			session.setAttribute("userSession", bkUser);
			logger.debug("==========>扮ㄦ风诲!");
			return "jsp/backend/main";
		}
		logger.debug("==========>扮ㄦ风诲澶辫触!");
		request.setAttribute("error", "ㄦ峰瀵璇!");
		return "jsp/backendlogin";
	}
	
	//娉ㄩ寮椤甸
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String Logout(HttpSession session){
		session.removeAttribute("userSession");
		return "redirect:/Backend/Login";
	}
	
	//ajaxㄥ绫讳妗
	@RequestMapping(value="categorylevellist.json",method=RequestMethod.GET)
	@ResponseBody
	public Object Classification(@RequestParam String pid){
		logger.debug("===========ㄥ绫 pid="+pid);
		List<app_category> categoriesList = null;
		if(pid != null && pid != ""){
			categoriesList = service.MainClassification(Integer.valueOf(pid));
		}else{
			categoriesList = service.OneClassification();
		}
		return JSON.toJSONString(categoriesList);
	}
	
	//APP瀹℃搁〉
	@RequestMapping(value="/bkUserList")
	public String bkUserList(Model model,
			@RequestParam(value="querySoftwareName",required=false)String querySoftwareName,
			@RequestParam(value="queryFlatformId",required=false)Integer queryFlatformId,
			@RequestParam(value="queryCategoryLevel1",required=false)Integer queryCategoryLevel1,
			@RequestParam(value="queryCategoryLevel2",required=false)Integer queryCategoryLevel2,
			@RequestParam(value="queryCategoryLevel3",required=false)Integer queryCategoryLevel3,
			@RequestParam(value="pageIndex",required=false)String pageIndex){
		logger.debug("=========<>杞浠跺绉帮" + querySoftwareName);
		logger.debug("=========<>灞骞冲帮" + queryFlatformId);
		logger.debug("=========<>涓绾у绫伙" + queryCategoryLevel1);
		logger.debug("=========<>浜绾у绫伙" + queryCategoryLevel2);
		logger.debug("=========<>涓绾у绫伙" + queryCategoryLevel3);
		
		List<dataDictionary> FlatformName = service.getFlatformName();		//灞骞冲
		List<app_category> One = service.OneClassification();	//涓绾у绫
		//Integer _parentId = 0;
		List<app_category> Two = service.MainClassification(queryCategoryLevel1);	//浜绾у绫
		List<app_category> Three = service.MainClassification(queryCategoryLevel2);	//涓绾у绫
		
		
		//璁剧疆椤甸㈠归
    	int pageSize = 5;
    	//褰椤电
    	int currentPageNo = 1;
    	if(pageIndex != null){
    		currentPageNo = Integer.valueOf(pageIndex);
    	}
    	if(querySoftwareName == null){
    		querySoftwareName = "";
    	}
		
    	//绘伴
    	int count = service.getAppinfoCount(querySoftwareName, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3);
    	logger.debug("=========绘伴锛" + count);
    	
    	//婚〉
    	PageSupport pages=new PageSupport();
    	//璁剧疆褰椤
    	pages.setCurrentPageNo(currentPageNo);
    	//姣椤靛ぇ灏
    	pages.setPageSize(pageSize);
    	//浼ユ绘℃帮绠烘婚〉
    	pages.setTotalCount(count);
    	//峰婚〉
    	int totalPageCount = pages.getTotalPageCount();
    	//у堕椤靛灏鹃〉
    	if(currentPageNo < 1){
    		currentPageNo = 1;
    	}else if(currentPageNo > totalPageCount){
    		currentPageNo = totalPageCount;
    	}
    	
    	List<app_info> infoList = service.getAppinfoList(querySoftwareName, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, (currentPageNo-1)*pageSize, pageSize);
    	
		model.addAttribute("flatFormList", FlatformName);
		model.addAttribute("categoryLevel1List", One);
		model.addAttribute("categoryLevel2List", Two);
		model.addAttribute("categoryLevel3List", Three);
		
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("queryFlatformId", queryFlatformId);
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
		
		model.addAttribute("appInfoList", infoList);
		model.addAttribute("pages", pages);
		return "jsp/backend/applist";
	}
	
	//瀹℃搁〉
	@RequestMapping(value="/appinfocheck")
	public String AppinfoCheck(@RequestParam Integer aid,@RequestParam Integer vid,
			Model model){
		app_info info = devService.infoview(aid);		//APP虹淇℃
		app_version version = service.getAppversionByappId(aid);		//扮淇℃
		
		model.addAttribute("appVersion", version);
		model.addAttribute("appInfo", info);
		return "jsp/backend/appcheck";
	}
	
	//瀹℃
	@RequestMapping(value="/appinfochecksave")
	public String AppinfoCheckSave(@RequestParam Integer status,@RequestParam Integer id){
		logger.debug("========>瀹℃哥舵锛" + status);
		if(status == 2){
			if(service.AppinfoModifyStatus(id, status) > 0){
				logger.debug("========>瀹℃搁杩ц锛");
				return "redirect:/Backend/bkUserList";
			}else {
				logger.debug("========>瀹℃搁杩ц澶辫触锛");
				return "redirect:/Backend/appinfocheck";
			}
		}else if(status == 3){
			if(service.AppinfoModifyStatus(id, status) > 0){
				logger.debug("========>瀹℃镐杩ц锛");
				return "redirect:/Backend/bkUserList";
			}else {
				logger.debug("========>瀹℃镐杩ц澶辫触锛");
				return "redirect:/Backend/appinfocheck";
			}
		}
		return "";
	}
	
}
package cn.lsp.appsys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.lsp.appsys.pojo.app_category;
import cn.lsp.appsys.pojo.app_info;
import cn.lsp.appsys.pojo.app_version;
import cn.lsp.appsys.pojo.backendUser;
import cn.lsp.appsys.pojo.dataDictionary;
import cn.lsp.appsys.service.BackendUserService;
import cn.lsp.appsys.service.DevUserService;
import cn.lsp.appsys.tools.PageSupport;

@Controller
@RequestMapping("/Backend")
public class BackendUserController {

	private Logger logger = Logger.getLogger(BackendUserController.class);
	
	@Resource(name="backenduserservice")
	private BackendUserService service;
	
	@Resource(name="devuserservice")
	private DevUserService devService;
	
	//椋璇叉い
	@RequestMapping(value="/Login",method=RequestMethod.GET)
	public String Login(){
		return "jsp/backendlogin";
	}
	
	//妤璇
	@RequestMapping(value="/goLogin",method=RequestMethod.POST)
	public String goLogin(@RequestParam String userCode,@RequestParam String userPassword,HttpSession session,Model model,HttpServletRequest request){
		logger.debug("========>┿ユ璇叉" + userCode + "," + userPassword);
		backendUser bkUser = service.Login(userCode, userPassword);
		if(null != bkUser){
			session.setAttribute("userSession", bkUser);
			logger.debug("==========>椋璇!");
			return "jsp/backend/main";
		}
		logger.debug("==========>椋璇叉径杈瑙!");
		request.setAttribute("error", "宄扮电!");
		return "jsp/backendlogin";
	}
	
	//濞╁妞ょ
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String Logout(HttpSession session){
		session.removeAttribute("userSession");
		return "redirect:/Backend/Login";
	}
	
	//ajaxョ猾璁冲
	@RequestMapping(value="categorylevellist.json",method=RequestMethod.GET)
	@ResponseBody
	public Object Classification(@RequestParam String pid){
		logger.debug("===========ョ猾 pid="+pid);
		List<app_category> categoriesList = null;
		if(pid != null && pid != ""){
			categoriesList = service.MainClassification(Integer.valueOf(pid));
		}else{
			categoriesList = service.OneClassification();
		}
		return JSON.toJSONString(categoriesList);
	}
	
	//APP光
	@RequestMapping(value="/bkUserList")
	public String bkUserList(Model model,
			@RequestParam(value="querySoftwareName",required=false)String querySoftwareName,
			@RequestParam(value="queryFlatformId",required=false)Integer queryFlatformId,
			@RequestParam(value="queryCategoryLevel1",required=false)Integer queryCategoryLevel1,
			@RequestParam(value="queryCategoryLevel2",required=false)Integer queryCategoryLevel2,
			@RequestParam(value="queryCategoryLevel3",required=false)Integer queryCategoryLevel3,
			@RequestParam(value="pageIndex",required=false)String pageIndex){
		logger.debug("=========<>娴璺虹甯" + querySoftwareName);
		logger.debug("=========<>楠插府" + queryFlatformId);
		logger.debug("=========<>娑缁狙缁浼" + queryCategoryLevel1);
		logger.debug("=========<>娴缁狙缁浼" + queryCategoryLevel2);
		logger.debug("=========<>娑缁狙缁浼" + queryCategoryLevel3);
		
		List<dataDictionary> FlatformName = service.getFlatformName();		//楠
		List<app_category> One = service.OneClassification();	//娑缁狙缁
		//Integer _parentId = 0;
		List<app_category> Two = service.MainClassification(queryCategoryLevel1);	//娴缁狙缁
		List<app_category> Three = service.MainClassification(queryCategoryLevel2);	//娑缁狙缁
		
		
		//х妞ょ搞褰
    	int pageSize = 5;
    	//瑜版い
    	int currentPageNo = 1;
    	if(pageIndex != null){
    		currentPageNo = Integer.valueOf(pageIndex);
    	}
    	if(querySoftwareName == null){
    		querySoftwareName = "";
    	}
		
    	//缁浼
    	int count = service.getAppinfoCount(querySoftwareName, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3);
    	logger.debug("=========缁浼撮" + count);
    	
    	//濠
    	PageSupport pages=new PageSupport();
    	//х瑜版い
    	pages.setCurrentPageNo(currentPageNo);
    	//濮ｆい
    	pages.setPageSize(pageSize);
    	//娴笺缁甯缁濠
    	pages.setTotalCount(count);
    	//宄板
    	int totalPageCount = pages.getTotalPageCount();
    	//妞ら楣
    	if(currentPageNo < 1){
    		currentPageNo = 1;
    	}else if(currentPageNo > totalPageCount){
    		currentPageNo = totalPageCount;
    	}
    	
    	List<app_info> infoList = service.getAppinfoList(querySoftwareName, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, (currentPageNo-1)*pageSize, pageSize);
    	
		model.addAttribute("flatFormList", FlatformName);
		model.addAttribute("categoryLevel1List", One);
		model.addAttribute("categoryLevel2List", Two);
		model.addAttribute("categoryLevel3List", Three);
		
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("queryFlatformId", queryFlatformId);
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
		
		model.addAttribute("appInfoList", infoList);
		model.addAttribute("pages", pages);
		return "jsp/backend/applist";
	}
	
	//光
	@RequestMapping(value="/appinfocheck")
	public String AppinfoCheck(@RequestParam Integer aid,@RequestParam Integer vid,
			Model model){
		app_info info = devService.infoview(aid);		//APP规
		app_version version = service.getAppversionByappId(aid);		//娣
		
		model.addAttribute("appVersion", version);
		model.addAttribute("appInfo", info);
		return "jsp/backend/appcheck";
	}
	
	//光
	@RequestMapping(value="/appinfochecksave")
	public String AppinfoCheckSave(@RequestParam Integer status,@RequestParam Integer id){
		logger.debug("========>光ヨ甸" + status);
		if(status == 2){
			if(service.AppinfoModifyStatus(id, status) > 0){
				logger.debug("========>光┭");
				return "redirect:/Backend/bkUserList";
			}else {
				logger.debug("========>光┭婢惰精瑙");
				return "redirect:/Backend/appinfocheck";
			}
		}else if(status == 3){
			if(service.AppinfoModifyStatus(id, status) > 0){
				logger.debug("========>光┭");
				return "redirect:/Backend/bkUserList";
			}else {
				logger.debug("========>光┭婢惰精瑙");
				return "redirect:/Backend/appinfocheck";
			}
		}
		return "";
	}
	
}
package cn.lsp.appsys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.lsp.appsys.pojo.app_category;
import cn.lsp.appsys.pojo.app_info;
import cn.lsp.appsys.pojo.app_version;
import cn.lsp.appsys.pojo.backendUser;
import cn.lsp.appsys.pojo.dataDictionary;
import cn.lsp.appsys.service.BackendUserService;
import cn.lsp.appsys.service.DevUserService;
import cn.lsp.appsys.tools.PageSupport;

@Controller
@RequestMapping("/Backend")
public class BackendUserController {

	private Logger logger = Logger.getLogger(BackendUserController.class);
	
	@Resource(name="backenduserservice")
	private BackendUserService service;
	
	@Resource(name="devuserservice")
	private DevUserService devService;
	
	//妞
	@RequestMapping(value="/Login",method=RequestMethod.GET)
	public String Login(){
		return "jsp/backendlogin";
	}
	
	//濡ょ
	@RequestMapping(value="/goLogin",method=RequestMethod.POST)
	public String goLogin(@RequestParam String userCode,@RequestParam String userPassword,HttpSession session,Model model,HttpServletRequest request){
		logger.debug("========>裤" + userCode + "," + userPassword);
		backendUser bkUser = service.Login(userCode, userPassword);
		if(null != bkUser){
			session.setAttribute("userSession", bkUser);
			logger.debug("==========>妞!");
			return "jsp/backend/main";
		}
		logger.debug("==========>妞寰!");
		request.setAttribute("error", "瀹!");
		return "jsp/backendlogin";
	}
	
	//婵濡
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String Logout(HttpSession session){
		session.removeAttribute("userSession");
		return "redirect:/Backend/Login";
	}
	
	//ajaxх剧
	@RequestMapping(value="categorylevellist.json",method=RequestMethod.GET)
	@ResponseBody
	public Object Classification(@RequestParam String pid){
		logger.debug("===========х pid="+pid);
		List<app_category> categoriesList = null;
		if(pid != null && pid != ""){
			categoriesList = service.MainClassification(Integer.valueOf(pid));
		}else{
			categoriesList = service.OneClassification();
		}
		return JSON.toJSONString(categoriesList);
	}
	
	//APP
	@RequestMapping(value="/bkUserList")
	public String bkUserList(Model model,
			@RequestParam(value="querySoftwareName",required=false)String querySoftwareName,
			@RequestParam(value="queryFlatformId",required=false)Integer queryFlatformId,
			@RequestParam(value="queryCategoryLevel1",required=false)Integer queryCategoryLevel1,
			@RequestParam(value="queryCategoryLevel2",required=false)Integer queryCategoryLevel2,
			@RequestParam(value="queryCategoryLevel3",required=false)Integer queryCategoryLevel3,
			@RequestParam(value="pageIndex",required=false)String pageIndex){
		logger.debug("=========<>濞寸鸿圭" + querySoftwareName);
		logger.debug("=========<>妤搴" + queryFlatformId);
		logger.debug("=========<>濞缂缂娴" + queryCategoryLevel1);
		logger.debug("=========<>濞寸缂娴" + queryCategoryLevel2);
		logger.debug("=========<>濞缂缂娴" + queryCategoryLevel3);
		
		List<dataDictionary> FlatformName = service.getFlatformName();		//妤
		List<app_category> One = service.OneClassification();	//濞缂缂
		//Integer _parentId = 0;
		List<app_category> Two = service.MainClassification(queryCategoryLevel1);	//濞寸缂
		List<app_category> Three = service.MainClassification(queryCategoryLevel2);	//濞缂缂
		
		
		//濡瑜
    	int pageSize = 5;
    	//
    	int currentPageNo = 1;
    	if(pageIndex != null){
    		currentPageNo = Integer.valueOf(pageIndex);
    	}
    	if(querySoftwareName == null){
    		querySoftwareName = "";
    	}
		
    	//缂娴
    	int count = service.getAppinfoCount(querySoftwareName, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3);
    	logger.debug("=========缂娴兼" + count);
    	
    	//婵
    	PageSupport pages=new PageSupport();
    	//
    	pages.setCurrentPageNo(currentPageNo);
    	//婵锝
    	pages.setPageSize(pageSize);
    	//濞寸虹缂婵
    	pages.setTotalCount(count);
    	//瀹
    	int totalPageCount = pages.getTotalPageCount();
    	//濡妤
    	if(currentPageNo < 1){
    		currentPageNo = 1;
    	}else if(currentPageNo > totalPageCount){
    		currentPageNo = totalPageCount;
    	}
    	
    	List<app_info> infoList = service.getAppinfoList(querySoftwareName, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, (currentPageNo-1)*pageSize, pageSize);
    	
		model.addAttribute("flatFormList", FlatformName);
		model.addAttribute("categoryLevel1List", One);
		model.addAttribute("categoryLevel2List", Two);
		model.addAttribute("categoryLevel3List", Three);
		
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("queryFlatformId", queryFlatformId);
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
		
		model.addAttribute("appInfoList", infoList);
		model.addAttribute("pages", pages);
		return "jsp/backend/applist";
	}
	
	//
	@RequestMapping(value="/appinfocheck")
	public String AppinfoCheck(@RequestParam Integer aid,@RequestParam Integer vid,
			Model model){
		app_info info = devService.infoview(aid);		//APP瑙
		app_version version = service.getAppversionByappId(aid);		//濞
		
		model.addAttribute("appVersion", version);
		model.addAttribute("appInfo", info);
		return "jsp/backend/appcheck";
	}
	
	//
	@RequestMapping(value="/appinfochecksave")
	public String AppinfoCheckSave(@RequestParam Integer status,@RequestParam Integer id){
		logger.debug("========>ㄧ" + status);
		if(status == 2){
			if(service.AppinfoModifyStatus(id, status) > 0){
				logger.debug("========>");
				return "redirect:/Backend/bkUserList";
			}else {
				logger.debug("========>濠㈡扮簿");
				return "redirect:/Backend/appinfocheck";
			}
		}else if(status == 3){
			if(service.AppinfoModifyStatus(id, status) > 0){
				logger.debug("========>");
				return "redirect:/Backend/bkUserList";
			}else {
				logger.debug("========>濠㈡扮簿");
				return "redirect:/Backend/appinfocheck";
			}
		}
		return "";
	}
	
}
package cn.lsp.appsys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.lsp.appsys.pojo.app_category;
import cn.lsp.appsys.pojo.app_info;
import cn.lsp.appsys.pojo.app_version;
import cn.lsp.appsys.pojo.backendUser;
import cn.lsp.appsys.pojo.dataDictionary;
import cn.lsp.appsys.service.BackendUserService;
import cn.lsp.appsys.service.DevUserService;
import cn.lsp.appsys.tools.PageSupport;

@Controller
@RequestMapping("/Backend")
public class BackendUserController {

	private Logger logger = Logger.getLogger(BackendUserController.class);
	
	@Resource(name="backenduserservice")
	private BackendUserService service;
	
	@Resource(name="devuserservice")
	private DevUserService devService;
	
	//濡
	@RequestMapping(value="/Login",method=RequestMethod.GET)
	public String Login(){
		return "jsp/backendlogin";
	}
	
	//婵°
	@RequestMapping(value="/goLogin",method=RequestMethod.POST)
	public String goLogin(@RequestParam String userCode,@RequestParam String userPassword,HttpSession session,Model model,HttpServletRequest request){
		logger.debug("========>瑁" + userCode + "," + userPassword);
		backendUser bkUser = service.Login(userCode, userPassword);
		if(null != bkUser){
			session.setAttribute("userSession", bkUser);
			logge