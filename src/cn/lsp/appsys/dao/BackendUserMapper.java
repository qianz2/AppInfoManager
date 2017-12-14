package cn.lsp.appsys.dao;

import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.ibatis.annotations.Param;

import cn.lsp.appsys.pojo.app_category;
import cn.lsp.appsys.pojo.app_info;
import cn.lsp.appsys.pojo.app_version;
import cn.lsp.appsys.pojo.backendUser;
import cn.lsp.appsys.pojo.dataDictionary;

/**
 * 后天用户接口
 * @author Administrator
 *
 */
public interface BackendUserMapper {

	/**
	 * 根据userCode查询后台用户信息
	 * @return
	 */
	public backendUser getBackendUserByCode(@Param("userCode")String userCode);
	
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
	public List<app_category> MainClassification(Integer parentId);
	
	/**
	 * 分页查询
	 * @param appInfo
	 * @return
	 */
	public List<app_info> getAppinfoList(@Param("softwareName")String softwareName,@Param("flatformId")Integer flatformId,@Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,@Param("categoryLevel3")Integer categoryLevel3,@Param("currentPageNo")int currentPageNo,@Param("pageSize")int pageSize);
	
	/**
	 * 分页查询总数
	 * @param softwareName
	 * @param flatformId
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @return
	 */
	public Integer getAppinfoCount(@Param("softwareName")String softwareName,@Param("flatformId")Integer flatformId,@Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,@Param("categoryLevel3")Integer categoryLevel3);
	
	/**
	 * 最新版本信息
	 * @param id
	 * @return
	 */
	public app_version getAppversionByappId(@Param("appId")Integer id);
	
	/**
	 * 审核
	 * @param id
	 * @param status
	 * @return
	 */
	public Integer AppinfoModifyStatus(@Param("id")Integer id,@Param("status")Integer status);
	
}
