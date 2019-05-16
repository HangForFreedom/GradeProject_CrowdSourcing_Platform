package com.gradp.biz;

import java.util.List;
import java.util.Map;

import com.gradp.bean.UserBean;
import com.gradp.dao.UserDaoImpl;

import javax.print.DocFlavor;

public class UserBiz {
	UserDaoImpl uid = new UserDaoImpl();
	
	public UserBean checkLogin(UserBean ubean) {
		
		UserBean ub = uid.checkLogin(ubean);
		
		return ub;
	}
	
	//注册
	public int register(UserBean ub) {
		
		//用户名和手机号不能重复
		List<Map<String, String>> listp = uid.queryUserByPhone(ub.getPhone());
		List<Map<String, String>> lists = uid.queryUserByUserName(ub.getUsername());
		if ( !(lists==null || lists.size()==0) ) {
			//用户名不能重复
			return 2;
		}
		
		if ( !(listp==null || listp.size()==0) ) {
			//电话不能重复
			return 3;
		}
		
		return uid.addUser(ub);
	}

	/**
	 **根据电话修改密码
	 * */
	public int updatePwdByPhone(String newPwd, String phone){
		List<Map<String, String>> listp = uid.queryUserByPhone(phone);
		if (listp==null || listp.size()==0 ){
			//手机号不存在
			return 4;
		}
		return uid.updatePwdByPhone(newPwd, phone);
	}
}
