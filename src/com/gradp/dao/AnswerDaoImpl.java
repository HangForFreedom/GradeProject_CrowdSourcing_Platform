package com.gradp.dao;

import com.gradp.bean.AgreeBean;
import com.gradp.bean.AnswerBean;
import com.gradp.bean.DisagreeBean;
import com.gradp.util.DBUtil;
import com.gradp.util.EmojiUtil;

import java.io.UnsupportedEncodingException;
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

    /**
     * 根据userid查询该用户的所有回答，以及对应的问题信息
     * */
    public List<AnswerBean> queryAnswerAndQueByUserid(int page, int pageSize, int userid){
        List<AnswerBean> ansbs = new ArrayList<>();
        int start = 0;
        if (page>0){
            start = (page - 1) * pageSize;
        }
        String sql = "SELECT answer.*, question.queid, question.title, question.solveFlag, question.score\n" +
                "FROM answer LEFT JOIN question ON question.queid=answer.queid \n" +
                "WHERE answer.userid=? ORDER BY answer.time DESC LIMIT " + start + "," + pageSize;
        Object[] obj = {userid};
        List<Map<String, String>> lists = db.query(sql, obj);
        for (Map<String, String> m : lists){
            AnswerBean ansb = new AnswerBean();
            ansb.setAnsid(Integer.parseInt(m.get("ansid")));
            if (m.get("content")=="" || m.get("content")==null){
                ansb.setContent("");
            }else {
                try {
                    ansb.setContent(EmojiUtil.stringToEmoji(m.get("content")));
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }
            ansb.setSolveFlag(Integer.parseInt(m.get("solveFlag")));
            ansb.setTime(m.get("time"));
            String username = queryUserNameByUserId(Integer.parseInt(m.get("userid")));
            ansb.setUsername(m.get(username));
            ansb.setImage(m.get("image"));
            String role = queryRoleByUserId(Integer.parseInt(m.get("userid")));
            ansb.setRole(m.get(role));
            ansb.setQueid(Integer.parseInt(m.get("queid")));
            ansb.setQueTitle(m.get("title"));
            ansb.setQueSolveFlag(Integer.parseInt(m.get("solveFlag")));
            ansb.setQueScore(Integer.parseInt(m.get("score")));

            ansbs.add(ansb);
        }
        return ansbs;
    }

    private String getUserToAnsTotal(int userid){
        String sql = "SELECT count(*) AS total FROM answer WHERE userid=?";
        Object[] obj = {userid};
        List<Map<String, String>> lists = db.query(sql, obj);
        if (lists.size()!=0){
            return lists.get(0).get("total");
        }
        return null;
    }

    public int userToAnsTotalPages(int pageSize, int userid){
        int total = Integer.parseInt(getUserToAnsTotal(userid));
        int totalPages = (total % pageSize == 0) ? (total / pageSize) : (total / pageSize) + 1;
        return totalPages;
    }

    /**
     * 根据用户id查询用户名
     * */
    public String queryUserNameByUserId(int userid){
        String sql = "SELECT username FROM user where userid=?";
        Object[] obj = {userid};
        List<Map<String, String>> lists = db.query(sql, obj);

        if (lists.size()!=0){
            return lists.get(0).get("username");
        }
        return "无此用户";
    }

    /**
     * 根据用户id查询用户头像
     * */
    public String queryRoleByUserId(int userid){
        String sql = "SELECT role FROM user where userid=?";
        Object[] obj = {userid};
        List<Map<String, String>> lists = db.query(sql, obj);

        if (lists.size()!=0){
            return lists.get(0).get("role");
        }
        return "img/image.png";
    }

    /**
     * 根据答案id，用户修改自己的回答
     * */
    public int updateAnswerById(Map m, int ansid){
        String sql = "UPDATE answer SET content=? WHERE ansid=?";
        Object[] obj = {m.get("ansContent"), ansid};
        return db.update(sql, obj);
    }

    /**
     * 根据答案id，用户删除自己的回答
     * */
    public int deleteAnswerById(int ansid){
        String sql = "DELETE FROM answer WHERE ansid=?";
        Object[] obj = {ansid};
        return db.update(sql, obj);
    }
}
