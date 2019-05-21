package com.gradp.bean;

import java.util.Date;

/**
 * <p>摘要：</p>
 * <p>备注：</p>
 */
public class AnswerBean {
    private int ansid;

    private int queid;

    private int userid;

    private String content;

    private String image;

    public int getAgnum() {
        return agnum;
    }

    public void setAgnum(int agnum) {
        this.agnum = agnum;
    }

    public int getDisagnum() {
        return disagnum;
    }

    public void setDisagnum(int disagnum) {
        this.disagnum = disagnum;
    }

    private int agnum;

    private int disagnum;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    public int getAnsid() {
        return ansid;
    }

    public void setAnsid(int ansid) {
        this.ansid = ansid;
    }

    public int getQueid() {
        return queid;
    }

    public void setQueid(int queid) {
        this.queid = queid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getSolveFlag() {
        return solveFlag;
    }

    public void setSolveFlag(int solveFlag) {
        this.solveFlag = solveFlag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private int solveFlag;

    private String username;

    private String role;
}
