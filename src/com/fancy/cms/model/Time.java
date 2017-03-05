package com.fancy.cms.model;

import java.util.Date;

public class Time {
    private Integer stunumber;
    private Date stustarttime;
    private Date stuendtime;
    private Date teastarttime;
    private Date teaendtime;
    private Date thesarttime;
    private Date theendtime;
    public Integer getStunumber() {
        return stunumber;
    }
    public void setStunumber(Integer stunumber) {
        this.stunumber = stunumber;
    }

    public Date getStustarttime() {
        return stustarttime;
    }

    public void setStustarttime(Date stustarttime) {
        this.stustarttime = stustarttime;
    }
    public Date getStuendtime() {
        return stuendtime;
    }
    public void setStuendtime(Date stuendtime) {
        this.stuendtime = stuendtime;
    }
    public Date getTeastarttime() {
        return teastarttime;
    }

    public void setTeastarttime(Date teastarttime) {
        this.teastarttime = teastarttime;
    }
    public Date getTeaendtime() {
        return teaendtime;
    }

    public void setTeaendtime(Date teaendtime) {
        this.teaendtime = teaendtime;
    }

    public Date getThesarttime() {
        return thesarttime;
    }
    public void setThesarttime(Date thesarttime) {
        this.thesarttime = thesarttime;
    }
    public Date getTheendtime() {
        return theendtime;
    }

    public void setTheendtime(Date theendtime) {
        this.theendtime = theendtime;
    }
}