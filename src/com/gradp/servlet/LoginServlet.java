package com.gradp.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gradp.bean.UserBean;
import com.gradp.biz.UserBiz;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		//记住密码
		String rempwd = req.getParameter("rempwd");
		
		UserBean ubean = new UserBean();
		ubean.setPhone(phone);
		ubean.setPassword(password);
		
		UserBiz ubz = new UserBiz();
		UserBean ub = ubz.checkLogin(ubean);
		
		if (ub != null) {
			System.out.println("登录成功");
			
			if ("1".equals(rempwd)) {
				
				String str = URLEncoder.encode(phone,"UTF-8");
				
				Cookie c1 = new Cookie("u", str);
				Cookie c2 = new Cookie("p", password);
				
				//设置cookie的时长
				c1.setMaxAge(1*60*60*24);
				c2.setMaxAge(1*60*60*24);
				
				resp.addCookie(c1);
				resp.addCookie(c2);
				
				HttpSession session = req.getSession();
				session.setAttribute("ub", ub);
				
				resp.sendRedirect("init.do");
			}
		}else {
			System.out.println("登录失败");
			
			req.setAttribute("msg", "手机号或密码输入错误，请重新输入！");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
