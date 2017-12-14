package cn.lsp.appsys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lsp.appsys.pojo.app_category;
import cn.lsp.appsys.pojo.app_info;
import cn.lsp.appsys.pojo.app_version;
import cn.lsp.appsys.pojo.backendUser;
import cn.lsp.appsys.pojo.dataDictionary;

public interface BackendUserService {

	/**
	 * 验证登录
	 * @param userCode
	 * @return
	 */
	public backendUser Login(String userCode,String userPassword);
	
	/**
	 * 获取所属平台
	 * @return
	 */
	public List<dataDictionary> getFlatformName();
	
	/**
	 * 一级分类
	 * @return
	 */
	public List<app_category> OneClassification();
	
	/**
	 * 二,三级分类
	 * @param parentId
	 * @return
	 */
	public List<app_category> MainClassification(Integer parentId);
	
	/**
	 * 分页查询
	 * @param appInfo
	 * @return
	 */
	public List<app_info> getAppinfoList(String softwareName,Integer flatformId,Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3,int currentPageNo,int pageSize);
	
	/**
	 * 分页查询总数
	 * @param softwareName
	 * @param flatformId
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @return
	 */
	public Integer getAppinfoCount(String softwareName,Integer flatformId,Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3);
	
	/**
	 * 最新版本信息
	 * @param id
	 * @return
	 */
	public app_version getAppversionByappId(Integer id);
	
	/**
	 * 审核
	 * @param id
	 * @param status
	 * @return
	 */
	public Integer AppinfoModifyStatus(Integer id,Integer status);
	
}
