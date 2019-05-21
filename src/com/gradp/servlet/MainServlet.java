package com.gradp.servlet;

import com.gradp.bean.AnswerBean;
import com.gradp.bean.QuestionBean;
import com.gradp.bean.UserBean;
import com.gradp.biz.QuestionBiz;
import com.gradp.biz.UserBiz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        UserBiz ubz = new UserBiz();
        //登录这信息
        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");

        session.setAttribute("ub", ub);

        //提出问题数量
        int quesum = ubz.queryQuestionById(ub.getUserid());
        //回答数量
        int anssum = ubz.queryAnswerById(ub.getUserid());

        //查询所有问题内容
        QuestionBiz quebz = new QuestionBiz();
        List<QuestionBean> quebs = quebz.queryAllQuestion();

        for (QuestionBean q : quebs){
            int queid = q.getQueid();

            //查询每条问题回答数量
            int anssum2que = quebz.queryAnswerSumById(queid);
            q.setAnssum2que(anssum2que);

        }

        req.setAttribute("quesum", quesum);
        req.setAttribute("anssum", anssum);
        req.setAttribute("quebs", quebs);

        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }
}
