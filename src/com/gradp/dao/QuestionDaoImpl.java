package com.gradp.dao;

import com.gradp.bean.AnswerBean;
import com.gradp.bean.ClassesBean;
import com.gradp.bean.QuestionBean;
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
public class QuestionDaoImpl {
    DBUtil db = new DBUtil();

    /**
     * 查询所有问题
     * */
    public List<QuestionBean> queryAllQuestion(){
        List<QuestionBean> quebs = new ArrayList<>();
        String sql = "select question.*, u.username, u.role from question left join user u on u.userid=question.userid order by time desc";
        //lists代表查询出来的所有微博信息
        List<Map<String, String>> lists = db.query(sql, null);
        //创建一个List<QuestionBean>用于装在所有微博信息
        for (Map<String, String> q : lists){
            QuestionBean queb = new QuestionBean();
            //引号里的对应表中的列名
            queb.setQueid(Integer.parseInt(q.get("queid")));
            if (q.get("content")=="" || q.get("content")==null){
                queb.setContent("");
            }else{
                try {
                    queb.setContent(EmojiUtil.stringToEmoji(q.get("content")));
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
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
            queb.setClassid(Integer.parseInt(q.get("classid")));
            queb.setClassName(queryClassNameById(Integer.parseInt(q.get("classid"))));

            quebs.add(queb);
        }
        return quebs;
    }

    /**
     * 查询所有问题（积分排序）
     * */
    public List<QuestionBean> queryAllQuestionOrderScore(int page, int pageSize){
        List<QuestionBean> quebs = new ArrayList<>();
        int start = 0;
        if (page>0){
            start = (page - 1) * pageSize;
        }
        String sql = "select question.*, u.username, u.role from question left join user u on u.userid=question.userid order by score desc LIMIT " + start + "," + pageSize;
        //lists代表查询出来的所有微博信息
        List<Map<String, String>> lists = db.query(sql, null);
        //创建一个List<QuestionBean>用于装在所有微博信息
        for (Map<String, String> q : lists){
            QuestionBean queb = new QuestionBean();
            //引号里的对应表中的列名
            queb.setQueid(Integer.parseInt(q.get("queid")));
            if (q.get("content")=="" || q.get("content")==null){
                queb.setContent("");
            }else{
                try {
                    queb.setContent(EmojiUtil.stringToEmoji(q.get("content")));
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }
            queb.setTitle(q.get("title"));
            queb.setTime(q.get("time"));
            queb.setImage(q.get("image"));
            queb.setUserid(Integer.parseInt(q.get("userid")));
            queb.setUsername(q.get("username"));
            queb.setRole(q.get("role"));
            queb.setScore(Integer.parseInt(q.get("score")));
            queb.setSolveFlag(Integer.parseInt(q.get("solveFlag")));
            queb.setClassid(Integer.parseInt(q.get("classid")));
            queb.setClassName(queryClassNameById(Integer.parseInt(q.get("classid"))));

            quebs.add(queb);
        }
        return quebs;
    }

    /**
     * 根据queid查询单个问题
     * */
    public List<QuestionBean> querySingleQuestion(int queid){
        String sql = "SELECT question.*, u.username, u.role FROM question LEFT JOIN user u ON u.userid=question.userid WHERE queid=?";
        Object[] obj = {queid};
        List<Map<String, String>> lists = db.query(sql, obj);
        List<QuestionBean> quebs = new ArrayList<>();
        for (Map<String, String> q : lists){
            QuestionBean queb = new QuestionBean();
            //引号里的对应表中的列名
            queb.setQueid(Integer.parseInt(q.get("queid")));
            if (q.get("content")=="" || q.get("content")==null){
                queb.setContent("");
            }else{
                try {
                    queb.setContent(EmojiUtil.stringToEmoji(q.get("content")));
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
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
            queb.setClassid(Integer.parseInt(q.get("classid")));
            queb.setClassName(queryClassNameById(Integer.parseInt(q.get("classid"))));

            quebs.add(queb);
        }

        return quebs;
    }

    /**
     * 根据用户名查询问题表中内容
     * */
    public List<QuestionBean> queryQustionByUsername(int page, int pageSize, String username){
        List<QuestionBean> quebs = new ArrayList<>();
        int start = 0;
        if (page>0){
            start = (page - 1) * pageSize;
        }
        String sql = "SELECT question.*, u.username, u.role from question LEFT JOIN user u ON u.userid=question.userid WHERE u.username=? ORDER BY time desc LIMIT " + start + "," + pageSize;
        Object[] obj = {username};
        List<Map<String, String>> lists = db.query(sql, obj);
        for (Map<String, String> q : lists){
            QuestionBean queb = new QuestionBean();
            queb.setQueid(Integer.parseInt(q.get("queid")));
            if (q.get("content")=="" || q.get("content")==null){
                queb.setContent("");
            }else{
                try {
                    queb.setContent(EmojiUtil.stringToEmoji(q.get("content")));
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
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
            queb.setClassid(Integer.parseInt(q.get("classid")));
            queb.setClassName(queryClassNameById(Integer.parseInt(q.get("classid"))));

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
     * 查询回答内容(添加分页)
     * */
    public List<AnswerBean> queryAnswerById(int queid){
        List<AnswerBean> ansbs = new ArrayList<>();
        String sql = "SELECT * From answer WHERE queid=? ORDER BY solveFlag desc";
        Object[] obj = {queid};
        List<Map<String, String>> lists = db.query(sql, obj);
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
            // ansb.setWilsonScore(Double.parseDouble(m.get("wilsonScore")));

            ansbs.add(ansb);
        }
        return ansbs;
    }

    /* 表中记录总数 **/
    private String getAnsTotal(){
        String sql = "SELECT count(*) AS total FROM answer";
        List<Map<String, String>> lists = db.query(sql, null);
        if (lists.size()!=0){
            return lists.get(0).get("total");
        }
        return null;
    }

    /* 总页数 */
    public int ansTotalPages(int pageSize){
        int total = Integer.parseInt(getAnsTotal());
        int totalPages = (total % pageSize == 0) ? (total / pageSize) : (total / pageSize) + 1;
        return totalPages;
    }

    /**
     * 根据用户id查询用户名
     * */
    private String queryUserNameByUserId(int userid){
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
    private String queryRoleByUserId(int userid){
        String sql = "SELECT role FROM user where userid=?";
        Object[] obj = {userid};
        List<Map<String, String>> lists = db.query(sql, obj);

        if (lists.size()!=0){
            return lists.get(0).get("role");
        }
        return "img/image.png";
    }

    /**
     * 发布问题
     * */
    public int raiseQuestion(Map m){
        // queid, userid, title, content, image, time, score, solveFlag
        String sql = "INSERT INTO question values(null,?,?,?,?,now(),?,0,?)";
        Object [] obj = {m.get("userid"),m.get("queTitle"),m.get("queContent"),m.get("queImg"),m.get("queScore"),m.get("queClasses")};
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


    /***** 实现分页功能 *****/

    public List<QuestionBean> getRecords(int page, int pageSize){
        //创建一个List<QuestionBean>用于装在所有微博信息
        List<QuestionBean> quebs = new ArrayList<>();
        int start = 0;
        if (page>0){
            start = (page - 1) * pageSize;
        }
        String sql = "select question.*, user.username, user.role from question left join user on user.userid=question.userid ORDER BY time DESC LIMIT " + start + ","
                + pageSize;
        //lists代表查询出来的所有微博信息
        List<Map<String, String>> lists = db.query(sql, null);
        for (Map<String, String> q : lists){
            QuestionBean queb = new QuestionBean();
            //引号里的对应表中的列名
            queb.setQueid(Integer.parseInt(q.get("queid")));
            if (q.get("content")=="" || q.get("content")==null){
                queb.setContent("");
            }else{
                try {
                    queb.setContent(EmojiUtil.stringToEmoji(q.get("content")));
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }
            queb.setTitle(q.get("title"));
            queb.setTime(q.get("time"));
            queb.setImage(q.get("image"));
            queb.setUserid(Integer.parseInt(q.get("userid")));
            queb.setUsername(q.get("username"));
            queb.setRole(q.get("role"));
            queb.setScore(Integer.parseInt(q.get("score")));
            queb.setSolveFlag(Integer.parseInt(q.get("solveFlag")));
            queb.setClassid(Integer.parseInt(q.get("classid")));
            queb.setClassName(queryClassNameById(Integer.parseInt(q.get("classid"))));

            quebs.add(queb);
        }
        return quebs;
    }

    /* 表中记录总数 **/
    public String getQueTotal(){
        String sql = "SELECT count(*) AS total FROM question";
        List<Map<String, String>> lists = db.query(sql, null);
        if (lists.size()!=0){
            return lists.get(0).get("total");
        }
        return null;
    }

    /* 总页数 */
    public int queTotalPages(int pageSize){
        int total = Integer.parseInt(getQueTotal());
        int totalPages = (total % pageSize == 0) ? (total / pageSize) : (total / pageSize) + 1;
        return totalPages;
    }


    /***** 实现分类功能 *****/

    /**
     * 查询类别列表
     * */
    public List<ClassesBean> queryAllClasses(){
        List<ClassesBean> classbs = new ArrayList<>();
        String sql = "SELECT * FROM classes";
        List<Map<String, String>> lists = db.query(sql, null);
        for (Map<String, String> m : lists){
            ClassesBean classb = new ClassesBean();
            classb.setClassid(Integer.parseInt(m.get("classid")));
            classb.setClassName(m.get("className"));
            classb.setDeleteFlag(Integer.parseInt(m.get("deleteFlag")));

            classbs.add(classb);
        }
        return classbs;
    }

    /**
     * 根据分类查询问题
     * */
    public List<QuestionBean> queryQueByClassId(int page, int pageSize, int classid){
        List<QuestionBean> quebs = new ArrayList<>();
        int start = 0;
        if (page>0){
            start = (page - 1) * pageSize;
        }
        String sql = "SELECT * FROM question WHERE classid=? ORDER BY time DESC LIMIT " + start + "," + pageSize;
        Object[] obj = {classid};
        List<Map<String, String>> lists = db.query(sql, obj);
        for (Map<String, String> q : lists){
            QuestionBean queb = new QuestionBean();
            //引号里的对应表中的列名
            queb.setQueid(Integer.parseInt(q.get("queid")));
            if (q.get("content")=="" || q.get("content")==null){
                queb.setContent("");
            }else{
                try {
                    queb.setContent(EmojiUtil.stringToEmoji(q.get("content")));
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }
            queb.setTitle(q.get("title"));
            queb.setTime(q.get("time"));
            queb.setImage(q.get("image"));
            queb.setUserid(Integer.parseInt(q.get("userid")));
            queb.setUsername(queryUserNameByUserId(Integer.parseInt(q.get("userid"))));
            queb.setRole(q.get("role"));
            queb.setScore(Integer.parseInt(q.get("score")));
            queb.setSolveFlag(Integer.parseInt(q.get("solveFlag")));
            queb.setClassid(Integer.parseInt(q.get("classid")));
            queb.setClassName(queryClassNameById(Integer.parseInt(q.get("classid"))));

            quebs.add(queb);
        }
        return quebs;
    }

    /**
     * 根据分类id查询分类名，在显示问题出显示类名
     * */
    private String queryClassNameById(int classid){
        String sql = "SELECT className FROM classes WHERE classid=?";
        Object[] obj = {classid};
        List<Map<String, String>> lists = db.query(sql, obj);
        if (lists.size()!=0){
            return lists.get(0).get("className");
        }
        return null;
    }

    /**
     * 模糊查询，搜索功能
     * */
    public List<QuestionBean> searchQuetionList(String key){
        List<QuestionBean> quebs = new ArrayList<>();
        // StringBuilder sql = new StringBuilder(" SELECT * FROM question WHERE 1=1 ");
        // List<String> paramList = new ArrayList<>();
        // if (title != null && !"".equals(title.trim())){
        //     sql.append(" and title like ? ORDER BY time DESC ");
        //     paramList.add(title);
        // }
        String sql = "SELECT * FROM question WHERE title LIKE ? OR content LIKE ?";
        Object [] obj = {key,key};
        List<Map<String, String>> lists = db.query(sql, obj);
        for (Map<String, String> q : lists){
            QuestionBean queb = new QuestionBean();
            queb.setQueid(Integer.parseInt(q.get("queid")));
            queb.setContent(q.get("content"));
            queb.setTitle(q.get("title"));
            queb.setTime(q.get("time"));
            queb.setImage(q.get("image"));
            queb.setUserid(Integer.parseInt(q.get("userid")));
            queb.setUsername(queryUserNameByUserId(Integer.parseInt(q.get("userid"))));
            queb.setRole(q.get("role"));
            queb.setScore(Integer.parseInt(q.get("score")));
            queb.setSolveFlag(Integer.parseInt(q.get("solveFlag")));
            queb.setClassid(Integer.parseInt(q.get("classid")));
            queb.setClassName(queryClassNameById(Integer.parseInt(q.get("classid"))));

            quebs.add(queb);
        }
        return quebs;
    }

    /**
     * 根据问题id，用户修改自己的问题
     * */
    public int updateQuestionById(Map m, int queid){
        String sql = "UPDATE question SET title=?, content=?, classid=? WHERE queid=?";
        Object[] obj = {m.get("queTitle"), m.get("queContent"), m.get("queClasses"), queid};
        return db.update(sql, obj);
    }


}
