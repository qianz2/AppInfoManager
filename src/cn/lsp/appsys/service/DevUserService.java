package cn.lsp.appsys.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lsp.appsys.pojo.app_category;
import cn.lsp.appsys.pojo.app_info;
import cn.lsp.appsys.pojo.app_version;
import cn.lsp.appsys.pojo.dataDictionary;
import cn.lsp.appsys.pojo.devUser;


public interface DevUserService {

	/**
	 * 验证登录
	 * @param devCode
	 * @param devPassword
	 * @return
	 */
	public devUser Login(String devCode, String devPassword);
	
	/**
	 * app状态
	 * @return
	 */
	public List<dataDictionary> StatusName();
	
	/**
	 * 所属平台
	 * @return
	 */
	public List<dataDictionary> FlatformName();
	
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
	 * @param softwareName
	 * @param status
	 * @param flatformId
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	public List<app_info> getinfoList(String softwareName,Integer status,Integer flatformId,Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3,int currentPageNo,int pageSize);
	
	/**
	 * 分页查询总数量
	 * @param softwareName
	 * @param status
	 * @param flatformId
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @return
	 */
	public int getinfoCount(String softwareName,Integer status,Integer flatformId,Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3);
	
	/**
	 * 新增APP基础信息
	 * @param info
	 * @return
	 */
	public int addappinfo(app_info info);
	
	/**
	 * 根据id查询app基础信息
	 * @param id
	 * @return
	 */
	public app_info getAppinfoById(Integer id);
	
	/**
	 * 修改app基础信息
	 * @param info
	 * @return
	 */
	public int appinfomodify(app_info info);
	
	/**
	 * 根据Id删除APP基础信息
	 * @param id
	 * @return
	 */
	public int appinfodel(Integer id);
	
	/**
	 * 新增版本信息
	 * @param version
	 * @return
	 */
	public int addappversion(app_version version);
	
	/**
	 * 新增版本信息(修改app基础信息)
	 * @param versionId
	 * @param id
	 * @return
	 */
	public int modifyappinfo(Integer versionId,Integer id);
	
	/**
	 * 新增版本信息(查询刚刚新增的Id)
	 * @return
	 */
	public int MaxId();
	
	/**
	 * 新增版本信息(历史记录)
	 * @return
	 */
	public List<app_version> versionsList(Integer appId);
	
	/**
	 * 修改版本信息(展示信息)
	 * @param id
	 * @return
	 */
	public app_version app_version(Integer id);
	
	/**
	 * 修改版本信息
	 * @param version
	 * @return
	 */
	public int versionmodify(app_version version);
	
	/**
	 * 查询APP信息
	 * @param id
	 * @return
	 */
	public app_info infoview(Integer id);
	
	/**
	 * 删除APP
	 * @param id
	 * @return
	 */
	public int delapp(Integer id);
	
	/**
	 * 删除版本
	 * @param id
	 * @return
	 */
	public int delversion(Integer id);
	
	/**
	 * 上/下架操作
	 * @param statusId
	 * @param id
	 * @return
	 */
	public int statusmodify(app_info appinfo);
	
	/**
	 * 修改版本发布状态
	 * @param version
	 * @return
	 */
	public int versionmodifyBystatus(app_version version);
	
	/**
	 * 查询要修改发布状态的ID
	 * @param appId
	 * @return
	 */
	public int getversionByappId(Integer appId);
	
	/**
	 * 验证APK
	 * @param aPKName
	 * @return
	 */
	public int getAPKNameCount(String aPKName);
	
	/**
	 * 清空图片路径
	 * @param id
	 * @return
	 */
	public int modifyLogo(Integer id);
	
	/**
	 * 修改app信息（修改状态）
	 * @param status
	 * @param id
	 * @return
	 */
	public int modifystatus(Integer status,Integer id);
	
	/**
	 * 删除APK路径
	 * @param id
	 * @return
	 */
	public int modifyApk(Integer id);
	
}
