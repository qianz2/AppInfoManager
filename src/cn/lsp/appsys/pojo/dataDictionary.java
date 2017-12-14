package cn.lsp.appsys.pojo;

import java.util.Date;

/**
 * 字典实体类
 * @author Administrator
 *
 */
public class dataDictionary {
	private Integer id;			//主键ID
	private String typeCode;	//类型编码
	private String typeName;	//类型名称
	private Integer valueId;	//类型值ID
	private String valueName;	//类型值名称
	private Integer createdBy;	//创建者（来源于backend_user用户表的用户id）
	private Date creationDate;	//创建时间
	private Integer modifyBy;	//更新者（来源于backend_user用户表的用户id）
	private Date modifyDate;	//最新更新时间
	
	/**
	 * 读取主键ID
	 * @return
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 读取类型编码
	 * @return
	 */
	public String getTypeCode() {
		return typeCode;
	}
	/**
	 * 读取类型名称
	 * @return
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * 读取类型值ID
	 * @return
	 */
	public Integer getValueId() {
		return valueId;
	}
	/**
	 * 读取类型值名称
	 * @return
	 */
	public String getValueName() {
		return valueName;
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
	 * 写入类型编码
	 * @param typeCode
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	/**
	 * 写入类型名称
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * 写入类型值ID
	 * @param valueId
	 */
	public void setValueId(Integer valueId) {
		this.valueId = valueId;
	}
	/**
	 * 写入类型值名称
	 * @param valueName
	 */
	public void setValueName(String valueName) {
		this.valueName = valueName;
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
}
