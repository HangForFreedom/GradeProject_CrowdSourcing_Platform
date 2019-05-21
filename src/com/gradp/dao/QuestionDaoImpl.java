package com.gradp.dao;

import com.gradp.bean.AnswerBean;
import com.gradp.bean.QuestionBean;
import com.gradp.util.DBUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>摘要：</p>
 * <p>备注：</p>
 */
public class QuestionDaoImpl {
    DBUtil db = new DBUtil();

    /**
     * 查询所有问题及其信息
     * */
    public List<QuestionBean> queryAllQuestion(){
        String sql = "select question.*, u.username, u.role from question left join user u on u.userid=question.userid order by question.queid desc";
        //lists代表查询出来的所有微博信息
        List<Map<String, String>> lists = db.query(sql, null);
        //创建一个List<QuestionBean>用于装在所有微博信息
        List<QuestionBean> quebs = new ArrayList<>();
        for (Map<String, String> q : lists){
            QuestionBean queb = new QuestionBean();
            //引号里的对应表中的列名
            queb.setQueid(Integer.parseInt(q.get("queid")));
            if (q.get("content")=="" || q.get("content")==null){
                queb.setContent("");
            }

            queb.setTitle(q.get("title"));
            queb.setContent(q.get("content"));

            queb.setTime(q.get("time"));
            queb.setImage(q.get("image"));
            queb.setUserid(Integer.parseInt(q.get("userid")));
            queb.setUsername(q.get("username"));
            queb.setRole(q.get("role"));
            queb.setScore(Integer.parseInt(q.get("score")));
            queb.setSolveFlag(Integer.parseInt(q.get("solveFlag")));

            quebs.add(queb);
        }

        return quebs;
    }

    /**
     * 根据queid查询单个问题
     * */
    public List<QuestionBean> querySingleQuestion(int queid){
        String sql = "SELECT * FROM question WHERE queid=?";
        Object[] obj = {queid};
        List<Map<String, String>> lists = db.query(sql, obj);
        List<QuestionBean> quebs = new ArrayList<>();
        for (Map<String, String> q : lists){
            QuestionBean queb = new QuestionBean();
            //引号里的对应表中的列名
            queb.setQueid(Integer.parseInt(q.get("queid")));
            if (q.get("content")=="" || q.get("content")==null){
                queb.setContent("");
            }

            queb.setTitle(q.get("title"));
            queb.setContent(q.get("content"));

            queb.setTime(q.get("time"));
            queb.setImage(q.get("image"));
            queb.setUserid(Integer.parseInt(q.get("userid")));
            queb.setUsername(q.get("username"));
            queb.setRole(q.get("role"));
            queb.setScore(Integer.parseInt(q.get("score")));
            queb.setSolveFlag(Integer.parseInt(q.get("solveFlag")));

            quebs.add(queb);
        }

        return quebs;
    }

    /**
     * 根据用户名查询问题表中内容
     * */
    public List<QuestionBean> queryQustionByUsername(String username){
        String sql = "SELECT question.*, u.username, u.role from question LEFT JOIN user u ON u.userid=question.userid WHERE u.username=? order by question.queid desc";
        Object[] obj = {username};
        List<Map<String, String>> lists = db.query(sql, obj);
        List<QuestionBean> quebs = new ArrayList<>();
        for (Map<String, String> q : lists){
            QuestionBean queb = new QuestionBean();
            queb.setQueid(Integer.parseInt(q.get("queid")));
            if (q.get("content")=="" || q.get("content")==null){
                queb.setContent("");
            }

            queb.setTitle(q.get("title"));
            queb.setContent(q.get("content"));

            queb.setTime(q.get("time"));
            queb.setImage(q.get("image"));
            queb.setUserid(Integer.parseInt(q.get("userid")));
            queb.setUsername(q.get("username"));
            queb.setRole(q.get("role"));
            queb.setScore(Integer.parseInt(q.get("score")));
            queb.setSolveFlag(Integer.parseInt(q.get("solveFlag")));

            quebs.add(queb);
        }

        return quebs;
    }

    /**
     * 查询问题的点赞数
     * */
    public int queryAgreeById(int ansid){
        String sql = "SELECT count(agid) FROM agree WHERE ansid=?";
        Object[] obj = {ansid};
        List<Map<String, String>> lists = db.query(sql, obj);

        if (lists.size() == 0){
            return 0;
        }
        return Integer.parseInt(lists.get(0).get("count(agid)"));
    }

    /**
     * 查询问题的不赞同数
     * */
    public int queryDisagreeById(int ansid){
        String sql = "SELECT count(disagid) FROM disagree WHERE ansid=?";
        Object[] obj = {ansid};
        List<Map<String, String>> lists = db.query(sql, obj);

        if (lists.size() == 0){
            return 0;
        }
        return Integer.parseInt(lists.get(0).get("count(disagid)"));
    }

    /**
     * 查询问题回答数
     * */
    public int queryAnswerSumById(int queid){
        String sql = "SELECT count(ansid) FROM answer WHERE queid=?";
        Object[] obj = {queid};
        List<Map<String, String>> lists = db.query(sql, obj);

        if (lists.size() == 0){
            return 0;
        }
        return Integer.parseInt(lists.get(0).get("count(ansid)"));
    }

    /**
     * 查询回答内容
     * */
    public List<AnswerBean> queryAnswerById(int queid){
        String sql = "SELECT * From answer WHERE queid=? ORDER BY solveFlag desc";
        Object[] obj = {queid};

        List<Map<String, String>> lists = db.query(sql, obj);
        List<AnswerBean> ansbs = new ArrayList<>();
        for (Map<String, String> m : lists){
            AnswerBean ansb = new AnswerBean();
            ansb.setAnsid(Integer.parseInt(m.get("ansid")));
            ansb.setQueid(Integer.parseInt(m.get("queid")));
            ansb.setContent(m.get("content"));
            ansb.setImage(m.get("image"));
            ansb.setTime(m.get("time"));
            String username = queryUserNameByUserId(Integer.parseInt(m.get("userid")));
            ansb.setUsername(username);
            String role = queryRoleByUserId(Integer.parseInt(m.get("userid")));
            ansb.setRole(role);
            ansb.setSolveFlag(Integer.parseInt(m.get("solveFlag")));

            ansbs.add(ansb);
        }
        return ansbs;
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
        return "/img/image.png";
    }

    /**
     * 发布问题
     * */
    public int raiseQuestion(Map m){
        // queid, userid, title, content, image, time, score, solveFlag
        String sql = "INSERT INTO question values(null,?,?,?,?,now(),?,0)";
        Object [] obj = {m.get("userid"),m.get("queTitle"),m.get("queContent"),m.get("queImg"),m.get("queScore")};
        return db.update(sql, obj);
    }

    /**
     * 根据queid查询该问题是否解决
     * */
    public String queryQueSolvedById(int queid){
        String sql = "SELECT solveFlag FROM question WHERE queid=?";
        Object[] obj = {queid};
        List<Map<String, String>> lists = db.query(sql, obj);
        return lists.get(0).get("solveFlag");
    }

    /**
     * 根据ansid查询该答案是否采取
     * */
    public String queryAnsSolveById(int ansid){
        String sql = "SELECT solveFlag FROM answer WHERE ansid=?";
        Object[] obj = {ansid};
        List<Map<String, String>> lists = db.query(sql, obj);
        return lists.get(0).get("solveFlag");
    }

    /**
     * 根据userid 查询question中queid的一列
     * */
    public List<Integer> queryQueidListByUserid(int userid){
        String sql = "SELECT queid FROM question WHERE userid=?";
        Object[] obj = {userid};
        List<Map<String, String>> lists = db.query(sql, obj);
        List<Integer> result = new ArrayList<>();
        if (lists == null || lists.size() == 0){
            return result;
        }
        for (Map<String, String> m : lists){
            for (String value : m.values()){
                result.add(Integer.parseInt(value));
            }
        }
        return result;
    }

    /**
     * 根据queid查询该问题是否解决
     * */
    public String queryQueSolveById(int queid){
        String sql = "SELECT solveFlag FROM question WHERE queid=?";
        Object[] obj = {queid};
        List<Map<String, String>> lists = db.query(sql, obj);
        return lists.get(0).get("solveFlag");
    }
}
