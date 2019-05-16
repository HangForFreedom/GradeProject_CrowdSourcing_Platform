package com.gradp.dao;

import java.util.List;
import java.util.Map;

import com.gradp.bean.UserBean;
import com.gradp.util.DBUtil;

public class UserDaoImpl {
	DBUtil db = new DBUtil();
	
	/**
	 * 检查用户是否存在
	 * */
	public UserBean checkLogin(UserBean ubean) {
		String sql = "select * from user where phone=? and password=?";
		
		Object []obj = {ubean.getPhone(), ubean.getPassword()};
		
		List<Map<String, String>> lists = db.query(sql, obj);
		
		if(lists.size() != 0) {
			for(Map<String, String> map : lists) {
				UserBean ub = new UserBean();
				
				String userid = map.get("userid");
				String score = map.get("score");
				
				ub.setUserid(Integer.parseInt(userid));
				ub.setUsername(map.get("username"));
				ub.setPassword(map.get("password"));
				ub.setPhone(map.get("phone"));
				ub.setRole(map.get("role"));
				ub.setScore(Integer.parseInt(score));
				
				return ub;
			}
		}
		return null;
	}
	
	/**
	 * 用户注册
	 * */
	public int addUser(UserBean ub) {
		String sql = "insert into user value(null,?,?,?,'/img/image.png',0)";
		Object []obj = {ub.getUsername(), ub.getPassword(), ub.getPhone()};
		return db.update(sql, obj);
	}
	
	/**
	 * 用户名查重
	 * */
	public List<Map<String, String>> queryUserByUserName(String username){
		String sql = "select * from user where username=?";
		Object []obj = {username};
		return db.query(sql, obj);
	}
	
	/**
	 * 电话查重
	 * */
	public List<Map<String, String>> queryUserByPhone(String phone){
		String sql = "select * from user where phone=?";
		Object []obj = {phone};
		return db.query(sql, obj);
	}
	
	/**
	 * 忘记密码：根据电话修改密码
	 * */
	public int updatePwdByPhone(String newPwd, String phone){
		String sql = "update user set password=? where phone=?";
		Object []obj = {newPwd, phone};
		return db.update(sql, obj);
	}
}
