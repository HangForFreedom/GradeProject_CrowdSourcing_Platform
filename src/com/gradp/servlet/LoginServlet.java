package com.gradp.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gradp.bean.UserBean;
import com.gradp.biz.UserBiz;
import com.gradp.util.MD5Util;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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

	/**
	 * 登录
	 * */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		//记住密码
		String rempwd = req.getParameter("rempwd");

		UserBean ubean = new UserBean();
		ubean.setPhone(phone);
		ubean.setPassword(MD5Util.MD5Encrypt(password));

		UserBiz ubz = new UserBiz();
		UserBean ub = ubz.checkLogin(ubean);

		List<Map<String, String>> listp = ubz.queryUserByPhone(URLEncoder.encode(phone,"UTF-8"));
		if (listp==null || listp.size()==0){
			req.setAttribute("phoneNull", "手机号未被注册");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}else{
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
				}

				HttpSession session = req.getSession();
				session.setAttribute("ub", ub);

				resp.sendRedirect("main.do");
			}else {
				System.out.println("登录失败");

				req.setAttribute("msg", "密码错误");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
		}
	}

	/**
	 * 登出
	 * */
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

}
