package com.gradp.servlet;

import com.gradp.bean.AnswerBean;
import com.gradp.bean.QuestionBean;
import com.gradp.bean.UserBean;
import com.gradp.biz.AnswerBiz;
import com.gradp.biz.QuestionBiz;
import com.gradp.biz.UserBiz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * <p>摘要：</p>
 * <p>备注：</p>
 */
public class OtherPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");
        UserBiz ubz = new UserBiz();
        AnswerBiz ansbz = new AnswerBiz();
        QuestionBiz quebz = new QuestionBiz();
        String otherUsername = req.getParameter("username");
        //根据username查询他人id
        UserBean otherUb = ubz.queryUserByUsername(otherUsername);

        //提出问题数量
        int quesum = ubz.queryQuestionById(otherUb.getUserid());
        //回答数量
        int anssum = ubz.queryAnswerById(otherUb.getUserid());

        req.setAttribute("otherUb", otherUb);
        req.setAttribute("otherUsername", otherUsername);
        req.setAttribute("quesum",quesum);
        req.setAttribute("anssum", anssum);


        /* 分页功能 */
        // 每页6条记录
        int pageSize = 6;
        //todo 修改后面的参数
        String anPage = req.getParameter("anPage");
        if (anPage == null || anPage == ""){
            anPage = "1";
        }
        int ansPage = Integer.parseInt(anPage);

        req.setAttribute("ansPage", ansPage);

        int ansTotalPage = ansbz.userToAnsTotalPages(pageSize, otherUb.getUserid());

        req.setAttribute("ansTotalPage", ansTotalPage);

        List<AnswerBean> ansbs = ansbz.queryAnswerAndQueByUserid(ansPage, pageSize, otherUb.getUserid());
        int ansSum = ansbs.size();

        req.setAttribute("ansSum",ansSum);

        List<Integer> agreedAnsList = ubz.queryAgreedAnsList(otherUb.getUserid());
        List<Integer> disagreedAnsList = ubz.queryDisagreedAnsList(otherUb.getUserid());
        String agids = "";
        for (Integer integer : agreedAnsList) {
            agids += integer + ",";
        }
        String disagIds = "";
        for (Integer integer : disagreedAnsList) {
            disagIds += integer + ",";
        }

        req.setAttribute("agreedAnsList", agids);
        req.setAttribute("disagreedAnsList", disagIds);
        req.setAttribute("solve", "采纳失败");

        for (AnswerBean a : ansbs){
            int ansid = a.getAnsid();
            int agnum = quebz.queryAgreeById(ansid);
            a.setAgnum(agnum);
            int disagnum = quebz.queryDisagreeById(ansid);
            a.setDisagnum(disagnum);
            String solveFlag = quebz.queryAnsSolveById(ansid);
            if (Integer.parseInt(solveFlag) != 0){
                req.setAttribute("solve", "已采纳");
                req.setAttribute("solveansid", ansid);
            }
        }

        req.setAttribute("ansbs", ansbs);

        /*  重复的我的问题  **/
        String quePage = req.getParameter("quePage");
        if (quePage == null || quePage == ""){
            quePage = "1";
        }
        int quesPage = Integer.parseInt(quePage);
        int queTotalPage = quebz.queTotalPages(pageSize);

        req.setAttribute("quesPage", quesPage);
        req.setAttribute("queTotalPage", queTotalPage);

        List<QuestionBean> quebs = quebz.queryQustionByUsername(quesPage, pageSize, otherUsername);
        int queSum = quebs.size();
        for (QuestionBean q : quebs){
            int queid = q.getQueid();
            int anssum2que = quebz.queryAnswerSumById(queid);
            q.setAnssum2que(anssum2que);
        }

        req.setAttribute("quebs", quebs);
        req.setAttribute("queSum",queSum);

        if (otherUsername.equals(ub.getUsername())){
            req.getRequestDispatcher("myPage.jsp").forward(req, resp);
        }else {
            req.getRequestDispatcher("otherPage.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }
}
