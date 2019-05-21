package com.gradp.servlet;

import com.gradp.bean.AgreeBean;
import com.gradp.bean.DisagreeBean;
import com.gradp.bean.UserBean;
import com.gradp.biz.AnswerBiz;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>摘要：</p>
 * <p>备注：</p>
 */
public class OperateAsnwerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        // doGet(req, resp);
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
    private void answer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");

        String ansid = req.getParameter("ansid");
        AnswerBiz ansbz = new AnswerBiz();

        if (ansid != null){
            //修改solveFlag状态
            ansbz.updateFlagByAnswerId(Integer.parseInt(ansid));
            //修改用户积分
            ansbz.updateUserScoreByAnswerId(Integer.parseInt(ansid));

            resp.sendRedirect("visitOther.do");
        }
    }

    private void agree(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");

        String ansid = req.getParameter("ansid");
        String userid = req.getParameter("userid");
        String action = req.getParameter("action");

        AnswerBiz ansbz = new AnswerBiz();

        if(action.equals("agree"))
        {
            // list<> xx = select * from agree where userid=? and ansid=?
            List<AgreeBean> agbs = ansbz.queryAgreeIsNullById(Integer.parseInt(ansid), Integer.parseInt(userid));
            if(agbs == null || agbs.isEmpty())
            {
                //insert
                ansbz.agreeToAnswer(Integer.parseInt(ansid), Integer.parseInt(userid));
            }
        }
        else
        {
            //delete from agree where userid=? and ansid=?
            ansbz.cancelAgreeToAnswer(Integer.parseInt(ansid), Integer.parseInt(userid));
        }

        resp.sendRedirect("visitOther.do");
    }

    private void disagree(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");

        String ansid = req.getParameter("ansid");
        String userid = req.getParameter("userid");
        String action = req.getParameter("action");

        AnswerBiz ansbz = new AnswerBiz();

        if(action.equals("deny"))
        {
            List<DisagreeBean> disagbs = ansbz.queryDisagreeIsNullById(Integer.parseInt(ansid), Integer.parseInt(userid));
            if (disagbs == null || disagbs.isEmpty()){
                //insert
                ansbz.disagreeToAnswer(Integer.parseInt(ansid), Integer.parseInt(userid));
            }
        }
        else
        {
            //delete from agree where userid=? and ansid=?
            ansbz.cancelDisagreeToAnswer(Integer.parseInt(ansid), Integer.parseInt(userid));
        }

        resp.sendRedirect("visitOther.do");
    }
}
