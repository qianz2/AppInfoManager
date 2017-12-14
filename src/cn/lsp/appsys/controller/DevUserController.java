package cn.lsp.appsys.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.regexp.internal.REUtil;

import cn.lsp.appsys.pojo.app_category;
import cn.lsp.appsys.pojo.app_info;
import cn.lsp.appsys.pojo.app_version;
import cn.lsp.appsys.pojo.dataDictionary;
import cn.lsp.appsys.pojo.devUser;
import cn.lsp.appsys.service.DevUserService;
import cn.lsp.appsys.tools.PageSupport;

@Controller
@RequestMapping("/dev")
public class DevUserController {

	private Logger logger = Logger.getLogger(DevUserController.class);
	
	@Resource(name="devuserservice")
	private DevUserService devUserService;
	

	
	
	
	//app维护页面
	@RequestMapping(value="/appinfolist.html")
	public String appinfolist(Model model,
			@RequestParam(value="querySoftwareName",required=false)String querySoftwareName,
			@RequestParam(value="queryStatus",required=false)Integer queryStatus,
			@RequestParam(value="queryFlatformId",required=false)Integer queryFlatformId,
			@RequestParam(value="queryCategoryLevel1",required=false)Integer queryCategoryLevel1,
			@RequestParam(value="queryCategoryLevel2",required=false)Integer queryCategoryLevel2,
			@RequestParam(value="queryCategoryLevel3",required=false)Integer queryCategoryLevel3,
			@RequestParam(value="pageIndex",required=false)String pageIndex){
		logger.debug("==========>软件名称：" + querySoftwareName);
		logger.debug("==========>状态：" + queryStatus);
		logger.debug("==========>所属平台：" + queryFlatformId);
		logger.debug("==========>一级分类：" + queryCategoryLevel1);
		logger.debug("==========>二级分类：" + queryCategoryLevel2);
		logger.debug("==========>三级分类：" + queryCategoryLevel3);
		logger.debug("==========>当前页码：" + pageIndex);
		
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
		
		List<dataDictionary> statusName = devUserService.StatusName();	//app状态
		List<dataDictionary> flatformName = devUserService.FlatformName();	//所属平台
		List<app_category> One = devUserService.OneClassification();	//一级分类
		//Integer _parentId = 0;
		List<app_category> Two = devUserService.MainClassification(queryCategoryLevel1);	//二级分类
		List<app_category> Three = devUserService.MainClassification(queryCategoryLevel2);	//三级分类
		
		//总数量
		int count = devUserService.getinfoCount(querySoftwareName, queryStatus, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3);
		logger.debug("=================> 总数量：" + count);
		
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
    	
    	List<app_info> infoList = devUserService.getinfoList(querySoftwareName, queryStatus, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, (currentPageNo-1)*pageSize, pageSize);
    	int infoListCount = infoList.size();
    	logger.debug("=============>集合数量：" + infoListCount);
    	
    	model.addAttribute("Count", count);
    	model.addAttribute("infoList",infoList);
    	model.addAttribute("page", pages);
    	model.addAttribute("totalPageCount", totalPageCount);
    	model.addAttribute("currentPageNo", currentPageNo);
    	model.addAttribute("queryStatus",queryStatus);
    	model.addAttribute("queryFlatformId",queryFlatformId);
    	model.addAttribute("queryCategoryLevel1",queryCategoryLevel1);
    	model.addAttribute("queryCategoryLevel2",queryCategoryLevel2);
    	model.addAttribute("queryCategoryLevel3",queryCategoryLevel3);
		model.addAttribute("statusName",statusName);
		model.addAttribute("flatformName",flatformName);
		model.addAttribute("One",One);
		model.addAttribute("Two", Two);
		model.addAttribute("Three", Three);
		return "jsp/developer/appinfolist";
	}
	
	//ajax联动分类下拉框
	@RequestMapping(value="categorylevellist.json",method=RequestMethod.GET)
	@ResponseBody
	public Object Classification(@RequestParam String pid){
		logger.debug("===========联动分类 pid="+pid);
		List<app_category> categoriesList = null;
		if(pid != null && pid != ""){
			categoriesList = devUserService.MainClassification(Integer.valueOf(pid));
		}else{
			categoriesList = devUserService.OneClassification();
		}
		return JSON.toJSONString(categoriesList);
	}
	
	//ajax查询所属平台下拉框
	/*@RequestMapping(value="datadictionarylist.json",method=RequestMethod.GET)
	@ResponseBody
	public Object Platform(){
		List<dataDictionary> dictionariesList = null;
		dictionariesList = devUserService.FlatformName();
		return JSON.toJSONString(dictionariesList);
	}*/
	
	//ajax验证apk是否存在
	/*@RequestMapping(value="apkexist.json",method=RequestMethod.GET)
	@ResponseBody
	public Object ApkName(@RequestParam String APKName){
		return "";
	}*/
	
	//新增页面
	@RequestMapping(value="/addappinfo.html",method=RequestMethod.GET)
	public String appinfoadd(){
		return "jsp/developer/appinfoadd";
	}
	
	
	//新增APP基础信息方法
	@RequestMapping(value="/addappinfoSave.html",method=RequestMethod.POST)
	public String addappinfo(app_info info,HttpSession session,HttpServletRequest request,
			@RequestParam(value ="a_logoPicPath", required = false) MultipartFile attach){
		logger.debug("=============>添加实体类：" + info.getSoftwareName());
		logger.debug("=============>文件上传：" + attach);
		
		//文件上传
		String idPicPath = null;
		String idTocPath = null;
		//判断文件是否为空
		if (!attach.isEmpty()) {
			// 定义上传的目标路径
			/*String path = request.getSession().getServletContext()
					.getRealPath("statics" + File.separator + "uploadfiles");*/
			String path = "E:\\workspace_s2\\AppInfoSystem\\WebContent\\statics\\uploadfiles";
			logger.info("uploadFile path ============== > " + path);
			// 获取原文件名
			String oldFileName = attach.getOriginalFilename();
			logger.info("uploadFile oldFileName ============== > " + oldFileName);
			// 获取原文件名的后缀
			String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
			logger.debug("uploadFile后缀 prefix============> " + prefix);
			int filesize = 500000;
			logger.debug("uploadFile size============> " + attach.getSize());
			// 上传大小不得超过 500k
			if (attach.getSize() > filesize) {
				logger.debug("----------------文件过大");
				request.setAttribute("uploadFileError", " * 上传大小不得超过 500k");
				return "jsp/useradd";

			} else if (prefix.equalsIgnoreCase("jpg")
					|| prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg")
					|| prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
				// 新的照片名称，毫秒数加随机数，确保不能重复
				System.out.println("-----------------进入");
				String fileName = System.currentTimeMillis()
						+ RandomUtils.nextInt(1000000) + "_Personal.jpg";
				System.out.println("-----------------ddd");
				logger.debug("new fileName======== " + attach.getName());
				// 创建文件对象，此文件对象用于接收用户上传的文件流
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					// 把MultipartFile中的文件流数据的数据输出至目标文件中
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					logger.debug("----------------上传图片失败");
					request.setAttribute("uploadFileError", " * 上传失败！");
					return "jsp/useradd";
				}
				idTocPath = request.getContextPath()+"/statics/uploadfiles/"+fileName;
				// 获取文件的的名称保存到数据库中
				idPicPath = path + File.separator + fileName;
			} else {
				logger.debug("----------------上传图片格式不正确");
				request.setAttribute("uploadFileError", " * 上传图片格式不正确");
				return "jsp/useradd";
			}
		}
		logger.debug("======地址"+idPicPath);
		
		//开发者id
		info.setDevId(((devUser)session.getAttribute("devUser")).getId());
		//创建者id
		info.setCreatedBy(((devUser)session.getAttribute("devUser")).getCreatedBy());
		//创建者时间
		info.setCreationDate(new Date());
		//上传图片
		info.setLogoPicPath(idTocPath);
		info.setLogoLocPath(idPicPath);
			
		int count = devUserService.addappinfo(info);
		if(count > 0){
			logger.debug("==========>新增APP基础信息成功!");
			return "redirect:/dev/appinfolist.html";
		}
		logger.debug("==========>新增APP基础信息失败!");
		return "redirect:/dev/addappinfo.html";
	}
	
	//app基础信息修改页面(待审核状态)
	@RequestMapping(value="/appinfomodeify.html",method=RequestMethod.GET)
	public String appinfomodeify(@RequestParam Integer id,Model model){
		app_info info = devUserService.getAppinfoById(id);
		model.addAttribute("appinfo", info);
		return "jsp/developer/appinfomodify";
	}
	
	//修改app基础信息方法
	@RequestMapping(value="/appinfomodeifySave.html",method=RequestMethod.POST)
	public String appinfomodeifySave(app_info appinfo,HttpSession session,HttpServletRequest request,
			@RequestParam(value ="attach", required = false) MultipartFile attach,
			@RequestParam("status") Integer status){
		
		//文件上传
		String idPicPath = null;
		String idTocPath = null;
		//判断文件是否为空
		if (!attach.isEmpty()) {
			// 定义上传的目标路径
			/*String path = request.getSession().getServletContext()
					.getRealPath("statics" + File.separator + "uploadfiles");*/
			String path = "E:\\workspace_s2\\AppInfoSystem\\WebContent\\statics\\uploadfiles";
			logger.info("uploadFile path ============== > " + path);
			// 获取原文件名
			String oldFileName = attach.getOriginalFilename();
			logger.info("uploadFile oldFileName ============== > " + oldFileName);
			// 获取原文件名的后缀
			String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
			logger.debug("uploadFile后缀 prefix============> " + prefix);
			int filesize = 500000;
			logger.debug("uploadFile size============> " + attach.getSize());
			// 上传大小不得超过 500k
			if (attach.getSize() > filesize) {
				logger.debug("----------------文件过大");
				request.setAttribute("uploadFileError", " * 上传大小不得超过 500k");
				return "jsp/useradd";

			} else if (prefix.equalsIgnoreCase("jpg")
					|| prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg")
					|| prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
				// 新的照片名称，毫秒数加随机数，确保不能重复
				System.out.println("-----------------进入");
				String fileName = System.currentTimeMillis()
						+ RandomUtils.nextInt(1000000) + "_Personal.jpg";
				System.out.println("-----------------ddd");
				logger.debug("new fileName======== " + attach.getName());
				// 创建文件对象，此文件对象用于接收用户上传的文件流
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					// 把MultipartFile中的文件流数据的数据输出至目标文件中
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					logger.debug("----------------上传图片失败");
					request.setAttribute("uploadFileError", " * 上传失败！");
					return "jsp/useradd";
				}
				idTocPath = request.getContextPath() + "/statics/uploadfiles/"+fileName;
				// 获取文件的的名称保存到数据库中
				idPicPath = path + File.separator + fileName;
			} else {
				logger.debug("----------------上传图片格式不正确");
				request.setAttribute("uploadFileError", " * 上传图片格式不正确");
				return "jsp/useradd";
			}
		}
		
		//创建时间
		appinfo.setModifyBy(((devUser)session.getAttribute("devUser")).getId());
		//更新时间
		appinfo.setModifyDate(new Date());
		//图片路径
		appinfo.setLogoPicPath(idTocPath);
		appinfo.setLogoLocPath(idPicPath);
		
		int count = devUserService.appinfomodify(appinfo);
		if(count > 0){
			logger.debug("==========>修改APP基础信息成功!");
			
			//判断提交是否需要审核
			//logger.debug("=====>提交并审核状态：" + appinfo.getStatus());
			if(status == 1){
				if(devUserService.modifystatus(appinfo.getStatus(), appinfo.getId()) > 0){
					logger.debug("======>提交并审核成功!");
					
				}else {
					logger.debug("======>提交并审核失败!");
					return "redirect:/dev/appinfomodeify.html";
				}
			}
			return "redirect:/dev/appinfolist.html";
			
		}else {
			logger.debug("==========>修改APP基础信息失败!");
			return "redirect:/dev/appinfomodeify.html";
		}
		
		
	}
	
	//根据Id删除APP基础信息
	/*@RequestMapping(value="appinfoDel.html",method=RequestMethod.GET)
	public String appinfoDel(){
		
	}*/
	
	//新增版本信息页面
	@RequestMapping(value="/appversionadd.html",method=RequestMethod.GET)
	public String addappversion(@RequestParam Integer id,Model model){
		logger.debug("===========>新增版本app基础信息表id：" + id);
		
		List<app_version> versions = devUserService.versionsList(id);
		
		model.addAttribute("devInfoId",id);
		model.addAttribute("versions", versions);
		return "jsp/developer/appversionadd";
	}
	
	//新增版本信息方法
	@RequestMapping(value="/addversionsave.html",method=RequestMethod.POST)
	public String addappversionSave(app_version version,HttpServletRequest request,HttpSession session,
			@RequestParam(value ="a_downloadLink", required = false) MultipartFile[] attachs){
		
		logger.debug("=========>app基础信息Id：" + version.getAppId());
		logger.debug("=========>新增版本号：" + version.getVersionNo());
		logger.debug("=========>新增版本大小：" + version.getVersionSize());
		logger.debug("=========>新增版本发布状态：" + version.getPublishStatus());
		logger.debug("=========>新增版本简介：" + version.getVersionInfo());
		
		//文件上传
		String idPicPath = null;
		String fileName = null;
		String workPicPath = null;
		String errorInfo = null;
		boolean flag = true;
		/*String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles"); */
		String path = "E:\\workspace_s2\\AppInfoSystem\\WebContent\\statics\\uploadfiles";
		logger.info("uploadFile path ============== > "+path);
		for(int i = 0;i < attachs.length ;i++){
		MultipartFile attach = attachs[i];
		if(!attach.isEmpty()){
			if(i == 0){
				errorInfo = "uploadFileError";
			}else if(i == 1){
				errorInfo = "uploadWpError";
			}
			
			String oldFileName = attach.getOriginalFilename();//原文件名
			logger.info("uploadFile oldFileName ============== > "+oldFileName);
			String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀     
		   logger.debug("uploadFile prefix============> " + prefix);
			int filesize = 500000000;
			logger.debug("uploadFile size============> " + attach.getSize());
		   if(attach.getSize() >  filesize){//上传大小不得超过 500mb
		   	request.setAttribute(errorInfo, " * 上传大小不得超过 500Mb");
		   	flag = false;
		   }else if(prefix.equalsIgnoreCase("apk")){//上传图片格式不正确
			   fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+".apk";  
		       logger.debug("new fileName======== " + attach.getName());
		       File targetFile = new File(path, fileName);  
		       if(!targetFile.exists()){  
		           targetFile.mkdirs();  
		       }  
		       //保存  
		       try {  
		       	attach.transferTo(targetFile);  
		       } catch (Exception e) {  
		           e.printStackTrace();  
		           request.setAttribute(errorInfo, " * 上传失败！");
		           flag = false;
		       }  
		       if(i == 0){
		       	 idPicPath = request.getContextPath()+"/statics/uploadfiles/"+fileName;
		       	workPicPath = path + File.separator + fileName;}
		       logger.debug("idPicPath: " + idPicPath);
		       
		   }else{
		   	request.setAttribute(errorInfo, " * 上传图片格式不正确");
		   	flag = false;
		   }
		}
		}
		
		//创建者
		version.setCreatedBy(((devUser)session.getAttribute("devUser")).getCreatedBy());
		version.setCreationDate(new Date());	//创建时间
		//文件
		version.setDownloadLink(idPicPath);
		version.setApkFileName(fileName);
		version.setApkLocPath(workPicPath);
		
		logger.debug("=========>新增版本创建者：" + version.getCreatedBy());
		logger.debug("=========>新增版本创创建时间：" + version.getCreationDate());
		logger.debug("=========>新增版本APK文件：" + version.getDownloadLink());
		logger.debug("=========>新增版本APK文件路径：" + version.getApkLocPath());
		logger.debug("=========>新增版本APK文件名称：" + version.getApkFileName());
		
		int appId = version.getAppId();
		
		int versionCount = devUserService.addappversion(version);
		if(versionCount > 0){
			logger.debug("===============>新增版本信息成功!");
			int versionId = devUserService.MaxId();		//刚刚新增的Id
			logger.debug("=========>刚刚新增的版本Id：" + versionId);
			if(devUserService.modifyappinfo(versionId, appId) > 0){
				logger.debug("=============>修改app基础信息成功!");
				return "redirect:/dev/appinfolist.html";
			}else {
				logger.debug("=============>修改app基础信息失败!");
				return "redirect:/dev/appversionadd.html";
			}
		}
		logger.debug("==================>新增版本信息失败!");
		return "redirect:/dev/appversionadd.html";
	}
	
	/**
	 * 修改版本页面
	 * @return
	 */
	@RequestMapping(value="/appversionmodify.html",method=RequestMethod.GET)
	public String appversionmodify(@RequestParam Integer vid,@RequestParam Integer aid,Model model){
		
		List<app_version> versionList = devUserService.versionsList(aid);
		app_version version = devUserService.app_version(vid);
		
		model.addAttribute("appVersion", versionList);
		model.addAttribute("appVersion123", version);
		return "jsp/developer/appversionmodify";
	}
	
	/**
	 * 修改版本信息方法
	 * @param version
	 * @return
	 */
	@RequestMapping(value="/appversionmodifySave.html",method=RequestMethod.POST)
	public String appversionmodifySave(app_version version,HttpSession session,
			@RequestParam(value ="attach", required = false) MultipartFile[] attachs,HttpServletRequest request){
		
		//文件上传
				String idPicPath = null;
				String fileName = null;
				String workPicPath = null;
				String errorInfo = null;
				boolean flag = true;
				/*String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles"); */
				String path = "E:\\workspace_s2\\AppInfoSystem\\WebContent\\statics\\uploadfiles";
				logger.info("uploadFile path ============== > "+path);
				for(int i = 0;i < attachs.length ;i++){
				MultipartFile attach = attachs[i];
				if(!attach.isEmpty()){
					if(i == 0){
						errorInfo = "uploadFileError";
					}else if(i == 1){
						errorInfo = "uploadWpError";
					}
							
					String oldFileName = attach.getOriginalFilename();//原文件名
					logger.info("uploadFile oldFileName ============== > "+oldFileName);
					String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀     
					logger.debug("uploadFile prefix============> " + prefix);
					int filesize = 500000000;
					logger.debug("uploadFile size============> " + attach.getSize());
					if(attach.getSize() >  filesize){//上传大小不得超过 500mb
						  request.setAttribute(errorInfo, " * 上传大小不得超过 500Mb");
						  flag = false;
						 }else if(prefix.equalsIgnoreCase("apk")){//上传图片格式不正确
							 fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+".apk";  
						     logger.debug("new fileName======== " + attach.getName());
						     File targetFile = new File(path, fileName);  
						     if(!targetFile.exists()){  
						         targetFile.mkdirs();  
						     }  
						     //保存  
						     try {  
						      attach.transferTo(targetFile);  
						     } catch (Exception e) {  
						         e.printStackTrace();  
						         request.setAttribute(errorInfo, " * 上传失败！");
						         flag = false;
						     }  
						     if(i == 0){
						       idPicPath = request.getContextPath()+"/statics/uploadfiles/"+fileName;
						      workPicPath = path + File.separator + fileName;}
						     logger.debug("idPicPath: " + idPicPath);
						       
						 }else{
						  request.setAttribute(errorInfo, " * 上传图片格式不正确");
						  flag = false;
						 }
				}
				}
		
		
		version.setModifyBy(((devUser)session.getAttribute("devUser")).getCreatedBy());
		version.setModifyDate(new Date());
		//文件
		version.setDownloadLink(idPicPath);
		version.setApkFileName(fileName);
		version.setApkLocPath(workPicPath);
		
		if(devUserService.versionmodify(version) > 0){
			logger.debug("===========>修改版本信息成功!");
			return "redirect:/dev/appinfolist.html";
		}
		logger.debug("===========>修改版本信息失败!");
		return "redirect:/dev/appversionmodify.html";
	}
	
	//查看APP信息
	@RequestMapping(value="/appview.html/{id}",method=RequestMethod.GET)
	public String appinfoview(@PathVariable Integer id,Model model){
		logger.debug("APP基础信息ID：" + id);
		app_info appinfo = devUserService.infoview(id);
		List<app_version> versionList = devUserService.versionsList(id);
		
		model.addAttribute("appInfo", appinfo);
		model.addAttribute("appInfoList", versionList);
		return "jsp/developer/appinfoview";
	}
	
	//删除APP
	@RequestMapping(value="/delapp.json",method=RequestMethod.GET)
	@ResponseBody
	public Object delapp(@RequestParam("id") Integer id){
		Map<String, String> map = new HashMap<String, String>();
		if(devUserService.delapp(id) > 0 ){
			devUserService.delversion(id);
			logger.debug("==========>删除APP信息成功!");
			map.put("delResult", "true");
		}else {
			logger.debug("==========>删除APP信息失败!");
			map.put("delResult", "false");
		}
		return JSON.toJSONString(map);
	}
	
	//上/下架操作
	@RequestMapping(value="/sale.json",method=RequestMethod.GET)
	@ResponseBody
	public String statusmodify(app_info appinfo,@RequestParam("type")String type,HttpSession session){
		appinfo.setModifyBy(((devUser)session.getAttribute("devUser")).getCreatedBy());
		appinfo.setModifyDate(new Date());
		int Aid = appinfo.getId();
		logger.debug("=========APPID：" + Aid);
		
		app_version version = new app_version();
		version.setModifyBy(((devUser)session.getAttribute("devUser")).getCreatedBy());
		version.setModifyDate(new Date());
		Integer appid = devUserService.getversionByappId(Aid);
		logger.debug("=========最大版本Id：" + appid);
		
		logger.debug("========>更新者：" + appinfo.getModifyBy());
		logger.debug("========>更新时间：" + appinfo.getModifyDate());
		logger.debug("状态Id:" + appinfo.getStatus() + ",APPId:" + appinfo.getId() + ",Type:" + type);
		Map<String, String> map = new HashMap<String, String>();
		if(type.equals("shangjia")){
			logger.debug("=========进入上架判断！");
			if(devUserService.statusmodify(appinfo) > 0){
				logger.debug("============>上架操作成功!");
				
				version.setPublishStatus(2);
				version.setId(appid);
				if(devUserService.versionmodifyBystatus(version) > 0){
					logger.debug("========>修改版本发布状态成功!");
				}else {
					logger.debug("========>修改版本发布状态失败!");
				}
				
				map.put("modifyResult", "true");
			}else {
				logger.debug("============>上架操作失败!");
				map.put("modifyResult", "false");
			}
		}else if(type.equals("xiajia")){
			if(devUserService.statusmodify(appinfo) > 0){
				logger.debug("============>下架操作成功!");
				
				version.setPublishStatus(3);
				if(devUserService.versionmodifyBystatus(version) > 0){
					logger.debug("========>修改版本发布状态成功!");
				}else {
					logger.debug("========>修改版本发布状态失败!");
				}
				
				map.put("modifyResult", "true");
			}else {
				logger.debug("============>下架操作失败!");
				map.put("modifyResult", "false");
			}
		}
		return JSON.toJSONString(map);
	}
	
	//动态加载所属平台列表
	@RequestMapping(value="/datadictionarylist.json",method=RequestMethod.GET)
	@ResponseBody
	public Object modifyList(){
		List<dataDictionary> dictionaryList  = devUserService.FlatformName();
		return JSON.toJSONString(dictionaryList);
	}
	
	//验证APK
	@RequestMapping(value="/apkexist.json",method=RequestMethod.GET)
	@ResponseBody
	public Object Apkexist(@RequestParam String APKName){
		Map<String, String> map = new HashMap<String, String>();
		if(APKName == null){
			logger.debug("========>APK为空!");
			map.put("APKName", "empty");
		}else if(devUserService.getAPKNameCount(APKName) > 0){
			logger.debug("========>APK不能使用!");
			map.put("APKName", "exist");
		}else {
			logger.debug("========>APK可以使用!");
			map.put("APKName", "noexist");
		}
		return JSON.toJSONString(map);
	}
	
	//删除图片
	@RequestMapping(value="/delfile.json",method=RequestMethod.GET)
	@ResponseBody
	public Object delfileLogo(@RequestParam Integer id,@RequestParam String flag){
		logger.debug("======>删除图片-flag：" + flag);
		deleteFile(flag);	//删除一个文件
		Map<String, String> map = new HashMap<String, String>();
		if(devUserService.modifyLogo(id) > 0){
			logger.debug("=========> 清空路径成功!");
			map.put("result", "success");
		}else {
			logger.debug("=========> 清空路径失败!");
			map.put("result", "failed");
		}
		return JSON.toJSONString(map);
	}
	
	/**
	 * 删除单个文件
	 * @param Apk
	 * @return
	 */
	public static boolean deleteFile(String Apk){
		File file = new File(Apk);
		 // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件"+Apk+"成功");
				return true;
			}else {
				System.out.println("删除单个文件"+Apk+"失败");
				return false;
			}
		}else{
			System.out.println("单个文件不存在！");
			return false;
		}
	}
	
	//删除APK文件
	@RequestMapping(value="/delfileApk.json",method=RequestMethod.GET)
	@ResponseBody
	public String delfileApk(@RequestParam Integer id,@RequestParam String flag){
		logger.debug("=======>删除APK路径：" + flag);
		deleteFile(flag);
		Map<String, String> map = new HashMap<String, String>();
		if(devUserService.modifyApk(id) > 0){
			logger.debug("======>清空APK路劲成功!");
			map.put("result", "success");
		}else {
			logger.debug("======>清空APK路劲失败!");
			map.put("result", "failed");
		}
		return JSON.toJSONString(map);
	}
	
}
