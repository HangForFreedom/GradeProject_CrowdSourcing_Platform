package com.gradp.bean;

/**
 * <p>摘要：</p>
 * <p>备注：</p>
 */
public class ClassesBean {
    private int classid;

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    private String className;

    private int deleteFlag;
}
