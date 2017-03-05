package com.fancy.cms.model;

@SuppressWarnings("serial")
public class Students implements java.io.Serializable {
	private Integer stuid;
	private RcAdmin rcAdmin;
	private String stuname;
	private String stusex;
	private String stupwd;
	private Integer stuclass;
	private Integer tid;

	public Integer getStuid() {
		return stuid;
	}

	public void setStuid(Integer stuid) {
		this.stuid = stuid;
	}

	public RcAdmin getRcAdmin() {
		return rcAdmin;
	}

	public void setRcAdmin(RcAdmin rcAdmin) {
		this.rcAdmin = rcAdmin;
	}

	public void setStuclass(Integer stuclass) {
		this.stuclass = stuclass;
	}

	public Integer getStuclass() {
		return stuclass;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname == null ? null : stuname.trim();
	}

	public String getStusex() {
		return stusex;
	}

	public void setStusex(String stusex) {
		this.stusex = stusex == null ? null : stusex.trim();
	}

	public String getStupwd() {
		return stupwd;
	}

	public void setStupwd(String stupwd) {
		this.stupwd = stupwd == null ? null : stupwd.trim();
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}
}