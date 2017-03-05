package com.fancy.cms.model;

import java.util.Date;

public class RcMenu implements java.io.Serializable {

	private Integer bInternal;
	private String labelText;
	private Integer level;
	private String link;
	private Integer menuId;
	private Integer parentId;
	private String parentName;
	private Integer status;
	private Date updateTime;
	public Integer getbInternal() {
		return this.bInternal;
	}

	public String getLabelText() {
		return this.labelText;
	}
	public Integer getLevel() {
		return this.level;
	}
	public String getLink() {
		return this.link;
	}
	public Integer getMenuId() {
		return this.menuId;
	}
	public Integer getParentId() {
		return this.parentId;
	}

	public String getParentName() {
		return this.parentName;
	}

	public Integer getStatus() {
		return this.status;
	}
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setbInternal(Integer bInternal) {
		this.bInternal = bInternal;
	}
	public void setLabelText(String labelText) {
		this.labelText = labelText == null ? null : labelText.trim();
	}
	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setLink(String link) {
		this.link = link == null ? null : link.trim();
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName == null ? null : parentName.trim();
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}