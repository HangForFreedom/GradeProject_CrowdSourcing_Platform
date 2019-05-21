package com.gradp.servlet;

import com.gradp.bean.UserBean;
import com.gradp.biz.UserBiz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册
 * @author Hang
 *
 * */
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String pwd1 = req.getParameter("password");
        String pwd2 = req.getParameter("password2");
        String phone = req.getParameter("phone");

        if(!pwd1.equals(pwd2)) {
            req.setAttribute("pswError", "两次输入密码不正确");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        UserBean ub = new UserBean();
        ub.setUsername(username);
        ub.setPassword(pwd1);
        ub.setPhone(phone);

        UserBiz ubz = new UserBiz();
        int request = ubz.register(ub);
        if(request==0) {
            //注册失败
            req.setAttribute("msg", "系统异常");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }else if(request==2){
            req.setAttribute("nameDone", "用户名已存在");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }else if(request==3){
            req.setAttribute("phoDone", "电话号码已存在");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }else {
            //注册成功
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }
}
