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
	 * ��֤��¼
	 * @param devCode
	 * @param devPassword
	 * @return
	 */
	public devUser Login(String devCode, String devPassword);
	
	/**
	 * app״̬
	 * @return
	 */
	public List<dataDictionary> StatusName();
	
	/**
	 * ����ƽ̨
	 * @return
	 */
	public List<dataDictionary> FlatformName();
	
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
	 * ��ҳ��ѯ������
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
	 * ����APP������Ϣ
	 * @param info
	 * @return
	 */
	public int addappinfo(app_info info);
	
	/**
	 * ����id��ѯapp������Ϣ
	 * @param id
	 * @return
	 */
	public app_info getAppinfoById(Integer id);
	
	/**
	 * �޸�app������Ϣ
	 * @param info
	 * @return
	 */
	public int appinfomodify(app_info info);
	
	/**
	 * ����Idɾ��APP������Ϣ
	 * @param id
	 * @return
	 */
	public int appinfodel(Integer id);
	
	/**
	 * �����汾��Ϣ
	 * @param version
	 * @return
	 */
	public int addappversion(app_version version);
	
	/**
	 * �����汾��Ϣ(�޸�app������Ϣ)
	 * @param versionId
	 * @param id
	 * @return
	 */
	public int modifyappinfo(Integer versionId,Integer id);
	
	/**
	 * �����汾��Ϣ(��ѯ�ո�������Id)
	 * @return
	 */
	public int MaxId();
	
	/**
	 * �����汾��Ϣ(��ʷ��¼)
	 * @return
	 */
	public List<app_version> versionsList(Integer appId);
	
	/**
	 * �޸İ汾��Ϣ(չʾ��Ϣ)
	 * @param id
	 * @return
	 */
	public app_version app_version(Integer id);
	
	/**
	 * �޸İ汾��Ϣ
	 * @param version
	 * @return
	 */
	public int versionmodify(app_version version);
	
	/**
	 * ��ѯAPP��Ϣ
	 * @param id
	 * @return
	 */
	public app_info infoview(Integer id);
	
	/**
	 * ɾ��APP
	 * @param id
	 * @return
	 */
	public int delapp(Integer id);
	
	/**
	 * ɾ���汾
	 * @param id
	 * @return
	 */
	public int delversion(Integer id);
	
	/**
	 * ��/�¼ܲ���
	 * @param statusId
	 * @param id
	 * @return
	 */
	public int statusmodify(app_info appinfo);
	
	/**
	 * �޸İ汾����״̬
	 * @param version
	 * @return
	 */
	public int versionmodifyBystatus(app_version version);
	
	/**
	 * ��ѯҪ�޸ķ���״̬��ID
	 * @param appId
	 * @return
	 */
	public int getversionByappId(Integer appId);
	
	/**
	 * ��֤APK
	 * @param aPKName
	 * @return
	 */
	public int getAPKNameCount(String aPKName);
	
	/**
	 * ���ͼƬ·��
	 * @param id
	 * @return
	 */
	public int modifyLogo(Integer id);
	
	/**
	 * �޸�app��Ϣ���޸�״̬��
	 * @param status
	 * @param id
	 * @return
	 */
	public int modifystatus(Integer status,Integer id);
	
	/**
	 * ɾ��APK·��
	 * @param id
	 * @return
	 */
	public int modifyApk(Integer id);
	
}
