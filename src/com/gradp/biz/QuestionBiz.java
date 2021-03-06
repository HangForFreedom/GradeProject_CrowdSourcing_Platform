package com.gradp.biz;

import com.gradp.bean.AnswerBean;
import com.gradp.bean.ClassesBean;
import com.gradp.bean.QuestionBean;
import com.gradp.dao.QuestionDaoImpl;

import java.util.List;
import java.util.Map;

/**
 * <p>摘要：</p>
 * <p>备注：</p>
 */
public class QuestionBiz {
    QuestionDaoImpl queDao = new QuestionDaoImpl();

    /**
     * 查询所有问题
     * */
    public List<QuestionBean> queryAllQuestion(){
        return queDao.queryAllQuestion();
    }

    /**
     * 查询所有问题（积分排序）
     * */
    public List<QuestionBean> queryAllQuestionOrderScore(int page, int pageSize){
        return queDao.queryAllQuestionOrderScore(page, pageSize);
    }

    public List<QuestionBean> querySingleQuestion(int queid){
        return queDao.querySingleQuestion(queid);
    }

    /**
     * 根据用户名查询问题表中内容
     * */
    public List<QuestionBean> queryQustionByUsername(int page, int pageSize, String username){
        return queDao.queryQustionByUsername(page, pageSize, username);
    }

    /* 回答总页数 */
    public int ansTotalPages(int pageSize){
        return queDao.ansTotalPages(pageSize);
    }

    /**
     * 查询问题的点赞数
     * */
    public int queryAgreeById(int ansid){
        return queDao.queryAgreeById(ansid);
    }

    /**
     * 查询问题的不赞同数
     * */
    public int queryDisagreeById(int ansid){
        return queDao.queryDisagreeById(ansid);
    }

    /**
     * 查询问题回答数
     * */
    public int queryAnswerSumById(int queid){
        return queDao.queryAnswerSumById(queid);
    }

    /**
     * 查询回答内容
     * */
    public List<AnswerBean> queryAnswerById(int queid){
        return queDao.queryAnswerById(queid);
    }

    /**
     * 发布问题
     * */
    public int raiseQuestion(Map m){
        return queDao.raiseQuestion(m);
    }

    /**
     * 根据queid查询该问题是否解决
     * */
    public String queryQueSolvedById(int queid){
        return queDao.queryQueSolvedById(queid);
    }

    /**
     * 根据ansid查询该答案是否采取
     * */
    public String queryAnsSolveById(int ansid){
        return queDao.queryAnsSolveById(ansid);
    }

    /**
     * 根据userid 查询question中queid的一列
     * */
    public List<Integer> queryQueidListByUserid(int userid){
        return queDao.queryQueidListByUserid(userid);
    }

    /**
     * 根据queid查询该问题是否解决
     * */
    public String queryQueSolveById(int queid){
        return queDao.queryQueSolveById(queid);
    }

    /******** 分页功能 *********/
    public List<QuestionBean> getRecords(int page, int pageSize){
        return queDao.getRecords(page, pageSize);
    }

    /* 总页数 */
    public int queTotalPages(int pageSize){
        return queDao.queTotalPages(pageSize);
    }

    /* 根据用户Id查询总页数 */
    public int queTotalPagesById(int pageSize, int userid){
        return queDao.queTotalPagesById(pageSize, userid);
    }

    /**
     * 查询类别列表
     * */
    public List<ClassesBean> queryAllClasses(){
        return queDao.queryAllClasses();
    }

    /**
     * 根据分类查询问题
     * */
    public List<QuestionBean> queryQueByClassId(int page, int pageSize, int classid){
        return queDao.queryQueByClassId(page, pageSize, classid);
    }

    /**
     * 模糊查询，搜索功能
     * */
    public List<QuestionBean> searchQuetionList(String title){
        return queDao.searchQuetionList(title);
    }

    /**
     * 根据问题id，用户修改自己的问题
     * */
    public int updateQuestionById(Map m, int queid){
        return queDao.updateQuestionById(m, queid);
    }

}
