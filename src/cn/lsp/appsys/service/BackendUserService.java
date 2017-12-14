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
	 * ��֤��¼
	 * @param userCode
	 * @return
	 */
	public backendUser Login(String userCode,String userPassword);
	
	/**
	 * ��ȡ����ƽ̨
	 * @return
	 */
	public List<dataDictionary> getFlatformName();
	
	/**
	 * һ������
	 * @return
	 */
	public List<app_category> OneClassification();
	
	/**
	 * ��,��������
	 * @param parentId
	 * @return
	 */
	public List<app_category> MainClassification(Integer parentId);
	
	/**
	 * ��ҳ��ѯ
	 * @param appInfo
	 * @return
	 */
	public List<app_info> getAppinfoList(String softwareName,Integer flatformId,Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3,int currentPageNo,int pageSize);
	
	/**
	 * ��ҳ��ѯ����
	 * @param softwareName
	 * @param flatformId
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @return
	 */
	public Integer getAppinfoCount(String softwareName,Integer flatformId,Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3);
	
	/**
	 * ���°汾��Ϣ
	 * @param id
	 * @return
	 */
	public app_version getAppversionByappId(Integer id);
	
	/**
	 * ���
	 * @param id
	 * @param status
	 * @return
	 */
	public Integer AppinfoModifyStatus(Integer id,Integer status);
	
}
