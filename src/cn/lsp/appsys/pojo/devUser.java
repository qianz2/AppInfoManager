package cn.lsp.appsys.pojo;

import java.util.Date;
/**
 * 开发者用户实体类
 * @author Administrator
 *
 */
public class devUser {
	private Integer id;				//主键ID
	private String devCode;			//开发者账号
	private String devName;			//开发者名称
	private String devPassword;		//开发者密码
	private String devEmail;		//开发者电子邮箱
	private String devInfo;			//开发者简介
	private Integer createdBy;		//创建者（来源于backend_user用户表的用户id）
	private Date creationDate;		//创建时间
	private Integer modifyBy;		//更新时间（来源于backend_user用户表的用户id）
	private Date modifyDate;		//最新更新时间
	
	/**
	 * 读取主键ID
	 * @return
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 读取开发者账号
	 * @return
	 */
	public String getDevCode() {
		return devCode;
	}
	/**
	 * 读取开发者名称
	 * @return
	 */
	public String getDevName() {
		return devName;
	}
	/**
	 * 读取开发者密码
	 * @return
	 */
	public String getDevPassword() {
		return devPassword;
	}
	/**
	 * 读取开发者电子邮箱
	 * @return
	 */
	public String getDevEmail() {
		return devEmail;
	}
	/**
	 * 读取开发者简介
	 * @return
	 */
	public String getDevInfo() {
		return devInfo;
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
	 * 读取更新时间（来源于backend_user用户表的用户id）
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
	 * 写入开发者账号
	 * @param devCode
	 */
	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}
	/**
	 * 写入开发者名称
	 * @param devName
	 */
	public void setDevName(String devName) {
		this.devName = devName;
	}
	/**
	 * 写入开发者密码
	 * @param devPassword
	 */
	public void setDevPassword(String devPassword) {
		this.devPassword = devPassword;
	}
	/**
	 * 写入开发者电子邮箱
	 * @param devEmail
	 */
	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}
	/**
	 * 写入开发者简介
	 * @param devInfo
	 */
	public void setDevInfo(String devInfo) {
		this.devInfo = devInfo;
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
	 * 写入更新时间（来源于backend_user用户表的用户id）
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
	
}
