package com.gradp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gradp.bean.UserBean;
import com.gradp.biz.UserBiz;

public class UpdatePwServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String phone = req.getParameter("phone");
        String newpwd1 = req.getParameter("newpassword");
        String newpwd2 = req.getParameter("newpassword2");
        String security = req.getParameter("security");

        if(!newpwd1.equals(newpwd2)) {
            req.setAttribute("pswError", "两次输入密码不正确");
            req.getRequestDispatcher("updatePw.jsp").forward(req, resp);
            return;
        }

        UserBean ub = new UserBean();
        ub.setPhone(phone);
        ub.setPassword(newpwd1);

        UserBiz ubz = new UserBiz();
        int request = ubz.updatePwdByPhone(newpwd1, phone);

        if(request==4) {
            //注册失败
            req.setAttribute("phoNull", "手机号未注册");
            req.getRequestDispatcher("updatePw.jsp").forward(req, resp);
            return;
        }else {
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }
}
