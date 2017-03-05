package com.fancy.cms.model;

public class Teachers {
    private Integer tid;
    private String pinyin;
    private String teaName;
    private String back1;
    private String back2;
    private Integer ssum;
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getPinyin() {
        return pinyin;
    }
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName == null ? null : teaName.trim();
    }

    public String getBack1() {
        return back1;
    }

    public void setBack1(String back1) {
        this.back1 = back1 == null ? null : back1.trim();
    }

    public String getBack2() {
        return back2;
    }
    public void setBack2(String back2) {
        this.back2 = back2 == null ? null : back2.trim();
    }

    public Integer getSsum() {
        return ssum;
    }

	public void setSsum(Integer ssum) {
		this.ssum = ssum;
	}

}