package cn.lsp.appsys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.lsp.appsys.dao.BackendUserMapper;
import cn.lsp.appsys.pojo.app_category;
import cn.lsp.appsys.pojo.app_info;
import cn.lsp.appsys.pojo.app_version;
import cn.lsp.appsys.pojo.backendUser;
import cn.lsp.appsys.pojo.dataDictionary;
import cn.lsp.appsys.service.BackendUserService;

@Service("backenduserservice")
public class BackendUserServiceImpl implements BackendUserService {

	@Resource
	private BackendUserMapper mapper;
	
	/**
	 * ��֤��¼
	 */
	public backendUser Login(String userCode, String userPassword) {
		backendUser bkUser = mapper.getBackendUserByCode(userCode);
		if(null != bkUser){
			if(!bkUser.getUserPassword().equals(userPassword)){
				bkUser = null;
			}
		}
		return bkUser;
	}

	/**
	 * ��ȡ����ƽ̨
	 */
	public List<dataDictionary> getFlatformName() {
		// TODO Auto-generated method stub
		return mapper.getFlatformName();
	}

	/**
	 * һ������
	 */
	public List<app_category> OneClassification() {
		// TODO Auto-generated method stub
		return mapper.OneClassification();
	}

	/**
	 * ��������
	 */
	public List<app_category> MainClassification(Integer parentId) {
		// TODO Auto-generated method stub
		return mapper.MainClassification(parentId);
	}

	
	/**
	 * ��ҳ��ѯ
	 */
	public List<app_info> getAppinfoList(String softwareName,
			Integer flatformId, Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3, int currentPageNo, int pageSize) {
		// TODO Auto-generated method stub
		return mapper.getAppinfoList(softwareName, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, currentPageNo, pageSize);
	}


	/**
	 * ��ҳ��ѯ����
	 */
	public Integer getAppinfoCount(String softwareName, Integer flatformId,
			Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3) {
		// TODO Auto-generated method stub
		return mapper.getAppinfoCount(softwareName, flatformId, categoryLevel1, categoryLevel2, categoryLevel3);
	}

	
	/**
	 * ���°汾��Ϣ
	 */
	public app_version getAppversionByappId(Integer id) {
		// TODO Auto-generated method stub
		return mapper.getAppversionByappId(id);
	}

	/**
	 * ���
	 */
	public Integer AppinfoModifyStatus(Integer id, Integer status) {
		// TODO Auto-generated method stub
		return mapper.AppinfoModifyStatus(id, status);
	}

	
	

}
