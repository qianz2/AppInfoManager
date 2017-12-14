package cn.lsp.appsys.pojo;

import java.util.Date;

/**
 * 后台用户实体类
 * @author Administrator
 *
 */
public class backendUser {
	private Integer id;				//主键ID
	private String userCode;		//用户编码
	private String userName;		//用户名称
	private String userPassword;	//用户密码
	private Integer userType;		//用户角色类型（来源于数据字典表，分为：超管、财务、市场、运营、销售）;
	private Integer createdBy;		//创建者（来源于backend_user用户表的用户id）
	private Date creationDate;		//创建时间
	private Integer modifyBy;		//更新者（来源于backend_user用户表的用户id）
	private Date modifyDate;		//最新更新时间
	
	private String userTypeName;	//用户角色类型名称
	
	/**
	 * 读取主键ID
	 * @return
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 读取用户编码
	 * @return
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * 读取用户名称
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 读取用户密码
	 * @return
	 */
	public String getUserPassword() {
		return userPassword;
	}
	/**
	 * 读取用户角色类型（来源于数据字典表，分为：超管、财务、市场、运营、销售）;
	 * @return
	 */
	public Integer getUserType() {
		return userType;
	}
	/**
	 * 读取创建者（来源于backend_user用户表的用户id）
	 * @return
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}
	/**
	 * 读取创建时间
	 * @return
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * 读取更新者（来源于backend_user用户表的用户id）
	 * @return
	 */
	public Integer getModifyBy() {
		return modifyBy;
	}
	/**
	 * 读取最新更新时间
	 * @return
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	
	
	/**
	 * 写入主键ID
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 写入用户编码
	 * @param userCode
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 * 写入用户名称
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 写入用户密码
	 * @param userPassword
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	/**
	 * 写入用户角色类型（来源于数据字典表，分为：超管、财务、市场、运营、销售）;
	 * @param userType
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	/**
	 * 写入创建者（来源于backend_user用户表的用户id）
	 * @param createdBy
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * 写入创建时间
	 * @param creationDate
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * 写入更新者（来源于backend_user用户表的用户id）
	 * @param modifyBy
	 */
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	/**
	 * 写入最新更新时间
	 * @param modifyDate
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	
}
