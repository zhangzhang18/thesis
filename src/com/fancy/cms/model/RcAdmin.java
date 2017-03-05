package com.fancy.cms.model;

import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class RcAdmin implements java.io.Serializable {
	private String identityName;
	private String username;
	private Date starttime;
	private Date endtime;
	private String text;
	private Integer roleId;
	private Integer role_id;
	private String extension;
	private String title;
	private String command;
	private String wiringGroup;
	private Integer WorkNumber;
	private String headImg;
	private String contact;
	private String name;
	private Integer userState;
	private Integer deptLevel;
	private String confirmPassword;
	private List<PortDept> deptprofessions;
	private List<TProfession> tprofessions;
	private String papers;
	private String professions;
	private Date createTime;
	private PortDept dept;
	private RcUserRole role;
	private Integer deptId;
	private Integer id;
	private Date lastTime;
	private String passWord;
	private PortStation station;
	private Integer stationId;
	private Integer status;
	private PortUnit unit;
	private Integer unitId;
	private Integer permissionsLevel;
	private String deptInfo;
	private String userName;
	private String identity;

	public String getIdentityName() {
		return identityName;
	}

	public Date getStarttime() {
		return starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public void setIdentityName(String identityName) {
		this.identityName = identityName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}


	public Integer getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(Integer deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getPermissionsLevel() {
		return permissionsLevel;
	}

	public void setPermissionsLevel(Integer permissionsLevel) {
		this.permissionsLevel = permissionsLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RcUserRole getRole() {
		return role;
	}

	public void setRole(RcUserRole role) {
		this.role = role;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public PortDept getDept() {
		return this.dept;
	}

	public Integer getDeptId() {
		return this.deptId;
	}

	public Integer getId() {
		return this.id;
	}

	public Date getLastTime() {
		return this.lastTime;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public PortStation getStation() {
		return this.station;
	}

	public Integer getStationId() {
		return this.stationId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public PortUnit getUnit() {
		return this.unit;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setDept(PortDept dept) {
		this.dept = dept;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord == null ? null : passWord.trim();
	}

	public void setStation(PortStation station) {
		this.station = station;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setUnit(PortUnit unit) {
		this.unit = unit;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getDeptInfo() {
		return deptInfo;
	}

	public void setDeptInfo(String deptInfo) {
		this.deptInfo = deptInfo;
	}

	public String getProfessions() {
		return professions;
	}

	public void setProfessions(String professions) {
		this.professions = professions;
	}

	public String getPapers() {
		return papers;
	}

	public void setPapers(String papers) {
		this.papers = papers;
	}

	public List<TProfession> getTprofessions() {
		return tprofessions;
	}

	public void setTprofessions(List<TProfession> tprofessions) {
		this.tprofessions = tprofessions;
	}

	public List<PortDept> getDeptprofessions() {
		return deptprofessions;
	}

	public void setDeptprofessions(List<PortDept> deptprofessions) {
		this.deptprofessions = deptprofessions;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Integer getWorkNumber() {
		return WorkNumber;
	}

	public void setWorkNumber(Integer workNumber) {
		WorkNumber = workNumber;
	}

	public String getWiringGroup() {
		return wiringGroup;
	}

	public void setWiringGroup(String wiringGroup) {
		this.wiringGroup = wiringGroup;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}