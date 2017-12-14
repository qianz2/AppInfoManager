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
 * ʵ�ֿ������û�ҵ��ӿ�
 * @author Administrator
 *
 */
@Service("devuserservice")
public class DevUserServiceImpl implements DevUserService {

	@Resource
	private DevUserMapper mapper;
	
	
	/**
	 * ��֤��¼
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
	 * app״̬
	 */
	public List<dataDictionary> StatusName() {
		// TODO Auto-generated method stub
		return mapper.getStatusName();
	}


	/**
	 * ����ƽ̨
	 */
	public List<dataDictionary> FlatformName() {
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
	 * ��,��������
	 */
	public List<app_category> MainClassification(Integer parentId) {
		// TODO Auto-generated method stub
		return mapper.MainClassification(parentId);
	}


	/**
	 * ��ҳ��ѯ
	 */
	public List<app_info> getinfoList(String softwareName, Integer status,
			Integer flatformId, Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3, int currentPageNo, int pageSize) {
		// TODO Auto-generated method stub
		return mapper.getinfoList(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, currentPageNo, pageSize);
	}


	/**
	 * ��ҳ��ѯ������
	 */
	public int getinfoCount(String softwareName, Integer status,
			Integer flatformId, Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3) {
		// TODO Auto-generated method stub
		return mapper.getinfoCount(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3);
	}


	/**
	 * ����APP������Ϣ
	 */
	public int addappinfo(app_info info) {
		// TODO Auto-generated method stub
		return mapper.addappinfo(info);
	}


	/**
	 * ����id��ѯapp������Ϣ
	 */
	public app_info getAppinfoById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.getAppinfoById(id);
	}


	/**
	 * �޸�app������Ϣ
	 */
	public int appinfomodify(app_info info) {
		// TODO Auto-generated method stub
		return mapper.appinfomodify(info);
	}


	/**
	 * ����Idɾ��APP������Ϣ
	 */
	public int appinfodel(Integer id) {
		// TODO Auto-generated method stub
		return mapper.appinfodel(id);
	}


	/**
	 * �����汾��Ϣ
	 */
	public int addappversion(app_version version) {
		// TODO Auto-generated method stub
		return mapper.addappversion(version);
	}


	/**
	 * �����汾��Ϣ(�޸�app������Ϣ)
	 */
	public int modifyappinfo(Integer versionId, Integer id) {
		// TODO Auto-generated method stub
		return mapper.modifyappinfo(versionId, id);
	}


	/**
	 * �����汾��Ϣ(��ѯ�ո�������Id)
	 */
	public int MaxId() {
		// TODO Auto-generated method stub
		return mapper.MaxId();
	}


	/**
	 * �����汾��Ϣ(��ʷ��¼)
	 */
	public List<app_version> versionsList(Integer id) {
		// TODO Auto-generated method stub
		return mapper.versionsList(id);
	}


	/**
	 * �޸İ汾��Ϣ(չʾ��Ϣ)
	 */
	public app_version app_version(Integer appId) {
		// TODO Auto-generated method stub
		return mapper.app_version(appId);
	}


	/**
	 * �޸İ汾��Ϣ
	 */
	public int versionmodify(app_version version) {
		// TODO Auto-generated method stub
		return mapper.versionmodify(version);
	}


	/**
	 * ��ѯAPP��Ϣ
	 */
	public app_info infoview(Integer id) {
		// TODO Auto-generated method stub
		return mapper.infoview(id);
	}


	/**
	 * ɾ��APP
	 */
	public int delapp(Integer id) {
		// TODO Auto-generated method stub
		return mapper.delapp(id);
	}


	/**
	 * ɾ���汾
	 */
	public int delversion(Integer id) {
		// TODO Auto-generated method stub
		return mapper.delversion(id);
	}



	/**
	 * ��/�¼ܲ���
	 */
	public int statusmodify(app_info appinfo){
		// TODO Auto-generated method stub
		return mapper.statusmodify(appinfo);
	}


	/**
	 * �޸İ汾����״̬
	 */
	public int versionmodifyBystatus(app_version version) {
		// TODO Auto-generated method stub
		return mapper.versionmodifyBystatus(version);
	}


	/**
	 * ��ѯҪ�޸ķ���״̬��ID
	 */
	public int getversionByappId(Integer appId) {
		// TODO Auto-generated method stub
		return mapper.getversionByappId(appId);
	}


	/**
	 * ��֤APK
	 */
	public int getAPKNameCount(String APKName) {
		// TODO Auto-generated method stub
		return mapper.getAPKNameCount(APKName);
	}


	/**
	 * ���ͼƬ·��
	 */
	public int modifyLogo(Integer id) {
		// TODO Auto-generated method stub
		return mapper.modifyLogo(id);
	}


	/**
	 * �޸�app��Ϣ���޸�״̬��
	 */
	public int modifystatus(Integer status, Integer id) {
		// TODO Auto-generated method stub
		return mapper.modifystatus(status, id);
	}



	/**
	 * ���APK·��
	 */
	public int modifyApk(Integer id) {
		// TODO Auto-generated method stub
		return mapper.modifyApk(id);
	}


	
	
	
}
