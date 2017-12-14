package cn.lsp.appsys.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.lsp.appsys.dao.DevUserMapper;
import cn.lsp.appsys.pojo.app_category;
import cn.lsp.appsys.pojo.app_info;
import cn.lsp.appsys.pojo.app_version;
import cn.lsp.appsys.pojo.dataDictionary;
import cn.lsp.appsys.pojo.devUser;
import cn.lsp.appsys.service.DevUserService;

/**
 * 实现开发者用户业务接口
 * @author Administrator
 *
 */
@Service("devuserservice")
public class DevUserServiceImpl implements DevUserService {

	@Resource
	private DevUserMapper mapper;
	
	
	/**
	 * 验证登录
	 * @param devCode
	 * @param devPassword
	 * @return
	 */
	public devUser Login(String devCode, String devPassword){
		devUser user = null;
		user = mapper.getLoginUser(devCode);
		if(null != user){
			if(!user.getDevPassword().equals(devPassword)){
				user = null;
			}
		}
		return user;
	}


	/**
	 * app状态
	 */
	public List<dataDictionary> StatusName() {
		// TODO Auto-generated method stub
		return mapper.getStatusName();
	}


	/**
	 * 所属平台
	 */
	public List<dataDictionary> FlatformName() {
		// TODO Auto-generated method stub
		return mapper.getFlatformName();
	}


	/**
	 * 一级分类
	 */
	public List<app_category> OneClassification() {
		// TODO Auto-generated method stub
		return mapper.OneClassification();
	}


	/**
	 * 二,三级分类
	 */
	public List<app_category> MainClassification(Integer parentId) {
		// TODO Auto-generated method stub
		return mapper.MainClassification(parentId);
	}


	/**
	 * 分页查询
	 */
	public List<app_info> getinfoList(String softwareName, Integer status,
			Integer flatformId, Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3, int currentPageNo, int pageSize) {
		// TODO Auto-generated method stub
		return mapper.getinfoList(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, currentPageNo, pageSize);
	}


	/**
	 * 分页查询总数量
	 */
	public int getinfoCount(String softwareName, Integer status,
			Integer flatformId, Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3) {
		// TODO Auto-generated method stub
		return mapper.getinfoCount(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3);
	}


	/**
	 * 新增APP基础信息
	 */
	public int addappinfo(app_info info) {
		// TODO Auto-generated method stub
		return mapper.addappinfo(info);
	}


	/**
	 * 根据id查询app基础信息
	 */
	public app_info getAppinfoById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.getAppinfoById(id);
	}


	/**
	 * 修改app基础信息
	 */
	public int appinfomodify(app_info info) {
		// TODO Auto-generated method stub
		return mapper.appinfomodify(info);
	}


	/**
	 * 根据Id删除APP基础信息
	 */
	public int appinfodel(Integer id) {
		// TODO Auto-generated method stub
		return mapper.appinfodel(id);
	}


	/**
	 * 新增版本信息
	 */
	public int addappversion(app_version version) {
		// TODO Auto-generated method stub
		return mapper.addappversion(version);
	}


	/**
	 * 新增版本信息(修改app基础信息)
	 */
	public int modifyappinfo(Integer versionId, Integer id) {
		// TODO Auto-generated method stub
		return mapper.modifyappinfo(versionId, id);
	}


	/**
	 * 新增版本信息(查询刚刚新增的Id)
	 */
	public int MaxId() {
		// TODO Auto-generated method stub
		return mapper.MaxId();
	}


	/**
	 * 新增版本信息(历史记录)
	 */
	public List<app_version> versionsList(Integer id) {
		// TODO Auto-generated method stub
		return mapper.versionsList(id);
	}


	/**
	 * 修改版本信息(展示信息)
	 */
	public app_version app_version(Integer appId) {
		// TODO Auto-generated method stub
		return mapper.app_version(appId);
	}


	/**
	 * 修改版本信息
	 */
	public int versionmodify(app_version version) {
		// TODO Auto-generated method stub
		return mapper.versionmodify(version);
	}


	/**
	 * 查询APP信息
	 */
	public app_info infoview(Integer id) {
		// TODO Auto-generated method stub
		return mapper.infoview(id);
	}


	/**
	 * 删除APP
	 */
	public int delapp(Integer id) {
		// TODO Auto-generated method stub
		return mapper.delapp(id);
	}


	/**
	 * 删除版本
	 */
	public int delversion(Integer id) {
		// TODO Auto-generated method stub
		return mapper.delversion(id);
	}



	/**
	 * 上/下架操作
	 */
	public int statusmodify(app_info appinfo){
		// TODO Auto-generated method stub
		return mapper.statusmodify(appinfo);
	}


	/**
	 * 修改版本发布状态
	 */
	public int versionmodifyBystatus(app_version version) {
		// TODO Auto-generated method stub
		return mapper.versionmodifyBystatus(version);
	}


	/**
	 * 查询要修改发布状态的ID
	 */
	public int getversionByappId(Integer appId) {
		// TODO Auto-generated method stub
		return mapper.getversionByappId(appId);
	}


	/**
	 * 验证APK
	 */
	public int getAPKNameCount(String APKName) {
		// TODO Auto-generated method stub
		return mapper.getAPKNameCount(APKName);
	}


	/**
	 * 清空图片路径
	 */
	public int modifyLogo(Integer id) {
		// TODO Auto-generated method stub
		return mapper.modifyLogo(id);
	}


	/**
	 * 修改app信息（修改状态）
	 */
	public int modifystatus(Integer status, Integer id) {
		// TODO Auto-generated method stub
		return mapper.modifystatus(status, id);
	}



	/**
	 * 清空APK路径
	 */
	public int modifyApk(Integer id) {
		// TODO Auto-generated method stub
		return mapper.modifyApk(id);
	}


	
	
	
}
