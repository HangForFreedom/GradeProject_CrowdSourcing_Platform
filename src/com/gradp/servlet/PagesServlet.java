package com.gradp.servlet;

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
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>摘要：</p>
 * <p>备注：</p>
 */
public class PagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //1.获取ServletPath
        String servletPath = req.getServletPath();

        //2.去除 / 和 .do 得到answer
        String methodName = servletPath.substring(1);
        methodName = methodName.substring(0, methodName.length()-3);
        try {
            //3.利用反射获取methodName对应的方法
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //4.利用反射调用对应的方法
            method.invoke(this, req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }

    private void myQue(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        QuestionBiz quebz = new QuestionBiz();
        UserBiz ubz = new UserBiz();
        PrintWriter out = resp.getWriter();

        //登录这信息
        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");
        session.setAttribute("ub", ub);

        //提出问题数量
        int quesum = ubz.queryQuestionById(ub.getUserid());
        //回答数量
        int anssum = ubz.queryAnswerById(ub.getUserid());


        // 每页6条记录
        int pageSize = 9;
        String spage = req.getParameter("page");
        if (spage == null || spage == ""){
            spage = "1";
        }
        int page = Integer.parseInt(spage);
        int totalPage = quebz.queTotalPages(pageSize);

        List<QuestionBean> quebs = quebz.queryQustionByUsername(page, pageSize, ub.getUsername());
        int queSum = quebs.size();

        for (QuestionBean q : quebs){
            int queid = q.getQueid();

            //查询每条问题回答数量
            int anssum2que = quebz.queryAnswerSumById(queid);
            q.setAnssum2que(anssum2que);
        }

        req.setAttribute("quesum", quesum);
        req.setAttribute("anssum", anssum);
        req.setAttribute("page", page);
        req.setAttribute("prePage", page-1);
        req.setAttribute("nextPage", page+1);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("quebs", quebs);
        req.setAttribute("queSum", queSum);

        req.getRequestDispatcher("myQue.jsp").forward(req, resp);
    }

    private void scoreQue(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        QuestionBiz quebz = new QuestionBiz();
        UserBiz ubz = new UserBiz();
        PrintWriter out = resp.getWriter();

        //登录这信息
        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");
        session.setAttribute("ub", ub);

        //提出问题数量
        int quesum = ubz.queryQuestionById(ub.getUserid());
        //回答数量
        int anssum = ubz.queryAnswerById(ub.getUserid());


        // 每页6条记录
        int pageSize = 9;
        String spage = req.getParameter("page");
        if (spage == null || spage == ""){
            spage = "1";
        }
        int page = Integer.parseInt(spage);
        int totalPage = quebz.queTotalPages(pageSize);

        List<QuestionBean> quebs = quebz.queryAllQuestionOrderScore(page, pageSize);
        int queSum = quebs.size();

        for (QuestionBean q : quebs){
            int queid = q.getQueid();

            //查询每条问题回答数量
            int anssum2que = quebz.queryAnswerSumById(queid);
            q.setAnssum2que(anssum2que);
        }

        req.setAttribute("quesum", quesum);
        req.setAttribute("anssum", anssum);
        req.setAttribute("page", page);
        req.setAttribute("prePage", page-1);
        req.setAttribute("nextPage", page+1);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("quebs", quebs);
        req.setAttribute("queSum", queSum);

        req.getRequestDispatcher("scoreQue.jsp").forward(req, resp);
    }
}
