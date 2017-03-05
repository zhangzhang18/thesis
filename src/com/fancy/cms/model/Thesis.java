package com.fancy.cms.model;

import java.util.Date;

public class Thesis {
	private Integer cid;
	private String cproperty;
	private String stuname;
	private String ctitle;
	private String content;
	private String message;
	private String ctype;
	private Date submitDate;
	private Integer cflag;
	private Integer stuid;
	private Integer tid;
	private Integer research;
	private Teachers teacher;
	private Students student;

	public Teachers getTeacher() {
		return teacher;
	}

	public void setTeacher(Teachers teacher) {
		this.teacher = teacher;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	
	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCproperty() {
		return cproperty;
	}
	public void setCproperty(String cproperty) {
		this.cproperty = cproperty == null ? null : cproperty.trim();
	}
	public String getCtitle() {
		return ctitle;
	}
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle == null ? null : ctitle.trim();
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype == null ? null : ctype.trim();
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public Integer getCflag() {
		return cflag;
	}
	public void setCflag(Integer cflag) {
		this.cflag = cflag;
	}
	public Integer getStuid() {
		return stuid;
	}
	public void setStuid(Integer stuid) {
		this.stuid = stuid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getResearch() {
		return research;
	}
	public void setResearch(Integer research) {
		this.research = research;
	}
}