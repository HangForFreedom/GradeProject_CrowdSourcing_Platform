package com.gradp.bean;

import java.util.BitSet;
import java.util.Date;

/**
 * <p>摘要：</p>
 * <p>备注：</p>
 */
public class QuestionBean {
    private int queid;

    private int userid;

    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSolveFlag() {
        return solveFlag;
    }

    public void setSolveFlag(int solveFlag) {
        this.solveFlag = solveFlag;
    }

    private String content;

    private String image;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    private int score;

    private int solveFlag;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    private int anssum2que;

    public int getAnssum2que() {
        return anssum2que;
    }

    public void setAnssum2que(int anssum2que) {
        this.anssum2que = anssum2que;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    private int classid;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private String className;

}
