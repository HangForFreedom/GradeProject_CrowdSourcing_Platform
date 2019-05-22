package com.gradp.biz;

import com.gradp.bean.AgreeBean;
import com.gradp.bean.AnswerBean;
import com.gradp.bean.DisagreeBean;
import com.gradp.dao.AnswerDaoImpl;

import java.util.List;
import java.util.Map;

/**
 * <p>摘要：</p>
 * <p>备注：</p>
 */
public class AnswerBiz {
    AnswerDaoImpl ansDao = new AnswerDaoImpl();
    /**
     * 回答问题
     * */
    public int answerToQuestion(Map m){
        return ansDao.answerToQuestion(m);
    }

    /**
     * 采纳问题修改flag
     * */
    public int updateFlagByAnswerId(int ansid){
        return ansDao.updateFlagByAnswerId(ansid);
    }

    /**
     * 采纳问题后，增加用户积分
     * */
    public int updateUserScoreByAnswerId(int ansid){
        return ansDao.updateUserScoreByAnswerId(ansid);
    }

    /**
     * 点击插入点赞数据 ansid userid
     * */
    public int agreeToAnswer(int ansid, int userid){
        return ansDao.agreeToAnswer(ansid, userid);
    }

    /**
     * 点击删除点赞数据
     * */
    public int cancelAgreeToAnswer(int ansid, int userid){
        return ansDao.cancelAgreeToAnswer(ansid, userid);
    }

    /**
     * 点击插入不赞同数据 ansid userid
     * */
    public int disagreeToAnswer(int ansid, int userid){
        return ansDao.disagreeToAnswer(ansid, userid);
    }

    /**
     * 点击删除踩数据
     * */
    public int cancelDisagreeToAnswer(int ansid, int userid){
        return ansDao.cancelDisagreeToAnswer(ansid, userid);
    }

    /**
     * 根据userid ansid 查询agree表是否有数据
     * */
    public List<AgreeBean> queryAgreeIsNullById(int ansid, int userid){
        return ansDao.queryAgreeIsNullById(ansid, userid);
    }

    /**
     * 根据userid ansid 查询agree表是否有数据
     * */
    public List<DisagreeBean> queryDisagreeIsNullById(int ansid, int userid){
        return ansDao.queryDisagreeIsNullById(ansid, userid);
    }

    /**
     * 根据userid查询该用户的所有回答，以及对应的问题信息
     * */
    public List<AnswerBean> queryAnswerAndQueByUserid(int page, int pageSize, int userid){
        return ansDao.queryAnswerAndQueByUserid(page, pageSize, userid);
    }

    public int userToAnsTotalPages(int pageSize, int userid){
        return ansDao.userToAnsTotalPages(pageSize, userid);
    }
}
