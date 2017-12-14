package cn.lsp.appsys.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.ibatis.annotations.Param;

import cn.lsp.appsys.pojo.app_category;
import cn.lsp.appsys.pojo.app_info;
import cn.lsp.appsys.pojo.app_version;
import cn.lsp.appsys.pojo.dataDictionary;
import cn.lsp.appsys.pojo.devUser;

/**
 * 开发者用户接口
 * @author Administrator
 *
 */
public interface DevUserMapper {

	/**
	 * 根据devCode获取用户记录
	 * @param devCode
	 * @return
	 */
	public devUser getLoginUser(@Param("devCode")String devCode);
	
	/**
	 * 获取app状态
	 * @return
	 */
	public List<dataDictionary> getStatusName();
	
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
	 * @return
	 */
	public List<app_category> MainClassification(@Param("parentId")Integer parentId);
	
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
	public List<app_info> getinfoList(@Param("softwareName")String softwareName,@Param("status")Integer status,@Param("flatformId")Integer flatformId,@Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,@Param("categoryLevel3")Integer categoryLevel3,@Param("currentPageNo")int currentPageNo,@Param("pageSize")int pageSize);
	
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
	public int getinfoCount(@Param("softwareName")String softwareName,@Param("status")Integer status,@Param("flatformId")Integer flatformId,@Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,@Param("categoryLevel3")Integer categoryLevel3);
	
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
	public app_info getAppinfoById(@Param("id")Integer id);
	
	/**
	 * 修改APP基础信息
	 * @param info
	 * @return
	 */
	public int appinfomodify(app_info info);
	
	/**
	 * 根据id删除app基础信息
	 * @param id
	 * @return
	 */
	public int appinfodel(@Param("id")Integer id);
	
	/**
	 * 新增版本信息
	 * @return
	 */
	public int addappversion(app_version version);
	/**
	 * 新增版本信息(修改app基础信息)
	 * @return
	 */
	public int modifyappinfo(@Param("versionId")Integer versionId,@Param("id")Integer id);
	/**
	 * 新增版本信息(查询刚刚新增的Id)
	 * @return
	 */
	public int MaxId();
	/**
	 * 新增版本信息(历史记录)
	 * @return
	 */
	public List<app_version> versionsList(@Param("appId")Integer appId);
	
	/**
	 * 修改版本信息(展示信息)
	 * @return
	 */
	public app_version app_version(@Param("vid")Integer id);
	
	/**
	 * 修改版本信息
	 * @param version
	 * @return
	 */
	public int versionmodify(app_version version);
	
	/**
	 * 查看APP信息
	 * @return
	 */
	public app_info infoview(@Param("id")Integer id);
	
	/**
	 * 删除APP
	 * @param id
	 * @return
	 */
	public int delapp(@Param("id")Integer id);
	
	/**
	 * 删除版本信息
	 * @param id
	 * @return
	 */
	public int delversion(@Param("id")Integer id);
	
	/**
	 * 上/下架操作
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
	public int getversionByappId(@Param("appId")Integer appId);
	
	/**
	 * 验证APK
	 * @param aPKName
	 * @return
	 */
	public int getAPKNameCount(@Param("APKName")String aPKName);
	
	/**
	 * 清空图片路径
	 * @param id
	 * @return
	 */
	public int modifyLogo(@Param("id")Integer id);
	
	/**
	 * 修改app信息（修改状态）
	 * @param id
	 * @return
	 */
	public int modifystatus(@Param("status")Integer status,@Param("id")Integer id);
	
	/**
	 * 清空APK路径
	 * @param id
	 * @return
	 */
	public int modifyApk(@Param("id")Integer id);
	
}
