package com.gradp.dao;

import com.gradp.bean.AgreeBean;
import com.gradp.bean.DisagreeBean;
import com.gradp.util.DBUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>摘要：</p>
 * <p>备注：</p>
 */
public class AnswerDaoImpl {
    DBUtil db = new DBUtil();

    /**
     * 回答问题
     * */
    public int answerToQuestion(Map m){
        //ansid queid userid content image time solveFlag
        String sql = "INSERT INTO answer values(null,?,?,?,?,now(),0)";
        Object [] obj = {m.get("queid"),m.get("userid"),m.get("ansContent"),m.get("ansImg")};
        return db.update(sql, obj);
    }

    /**
     * 采纳问题修改flag
     * */
    public int updateFlagByAnswerId(int ansid){
        String sql = "UPDATE answer \n" +
                "INNER JOIN question \n" +
                "ON answer.queid=question.queid \n" +
                "SET answer.solveFlag=1,question.solveFlag=1 \n" +
                "WHERE ansid=?";
        Object[] obj = {ansid};
        return db.update(sql, obj);
    }

    /**
     * 采纳问题后，增加用户积分
     * */
    public int updateUserScoreByAnswerId(int ansid){
        String sql = "UPDATE user\n" +
                "INNER JOIN answer\n" +
                "ON `user`.userid=answer.userid\n" +
                "set score=score+( SELECT score FROM question LEFT JOIN answer ON question.queid=answer.queid WHERE answer.ansid=? )\n";
        Object[] obj = {ansid};
        return db.update(sql, obj);
    }

    /**
     * 点击插入点赞数据 ansid userid
     * */
    public int agreeToAnswer(int ansid, int userid){
        String sql = "INSERT INTO agree VALUES(null,?,?)";
        Object[] obj = {ansid, userid};
        return db.update(sql, obj);
    }

    /**
     * 点击删除点赞数据
     * */
    public int cancelAgreeToAnswer(int ansid, int userid){
        String sql = "DELETE FROM agree WHERE ansid=? and userid=?";
        Object[] obj = {ansid, userid};
        return db.update(sql, obj);
    }

    /**
     * 点击不赞修改数量
     * */
    public int disagreeToAnswer(int ansid, int userid){
        String sql = "INSERT INTO disagree VALUES(null,?,?)";
        Object[] obj = {ansid, userid};
        return db.update(sql, obj);
    }

    /**
     * 点击删除踩数据
     * */
    public int cancelDisagreeToAnswer(int ansid, int userid){
        String sql = "DELETE FROM disagree WHERE ansid=? and userid=?";
        Object[] obj = {ansid, userid};
        return db.update(sql, obj);
    }

    /**
     * 根据userid ansid 查询agree表是否有数据
     * */
    public List<AgreeBean> queryAgreeIsNullById(int ansid, int userid){
        String sql = "SELECT * FROM agree WHERE ansid=? and userid=?";
        Object[] obj = {ansid, userid};
        List<Map<String, String>> lists = db.query(sql, obj);
        List<AgreeBean> agbs = new ArrayList<>();
        for (Map<String, String> m : lists){
            AgreeBean agb = new AgreeBean();
            agb.setAgid(Integer.parseInt(m.get("agid")));
            agb.setAnsid(Integer.parseInt(m.get("ansid")));
            agb.setUserid(Integer.parseInt(m.get("userid")));

            agbs.add(agb);
        }
        return agbs;
    }

    /**
     * 根据userid ansid 查询agree表是否有数据
     * */
    public List<DisagreeBean> queryDisagreeIsNullById(int ansid, int userid){
        String sql = "SELECT * FROM disagree WHERE ansid=? and userid=?";
        Object[] obj = {ansid, userid};
        List<Map<String, String>> lists = db.query(sql, obj);
        List<DisagreeBean> disagbs = new ArrayList<>();
        for (Map<String, String> m : lists){
            DisagreeBean disagb = new DisagreeBean();
            disagb.setDisagid(Integer.parseInt(m.get("disagid")));
            disagb.setAnsid(Integer.parseInt(m.get("ansid")));
            disagb.setUserid(Integer.parseInt(m.get("userid")));

            disagbs.add(disagb);
        }
        return disagbs;
    }
}
