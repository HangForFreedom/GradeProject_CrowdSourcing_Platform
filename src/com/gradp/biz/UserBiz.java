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

    /**
     * session为空时，根据用户名查询用户所有的信息
     * */
    public UserBean queryAll(UserBean ubean){
        UserBean ub = uid.queryAll(ubean);

        return ub;
    }


	/**
	 * 登录页手机号单独查重
	 * */
	public List<Map<String, String>> queryUserByPhone(String phone){
		return uid.queryUserByPhone(phone);
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

	/**
	 * 查询用户的问题数量
	 * */
	public int queryQuestionById(int userid){
		return uid.queryQuestionById(userid);
	}

	/**
	 * 查询用户回答数量
	 * */
	public int queryAnswerById(int userid){
		return uid.queryAnswerById(userid);
	}

	/**
	 * 查询用户顶过的ansid列表
	 * */
	public List<Integer> queryAgreedAnsList(int userid){
		return uid.queryAgreedAnsList(userid);
	}

	/**
	 * 查询用户踩过的ansid列表
	 * */
	public List<Integer> queryDisagreedAnsList(int userid){
		return uid.queryDisagreedAnsList(userid);
	}

    /**
     * 根据userid查询用户积分
     * */
    public int queryUserScoreById(int userid){
        return uid.queryUserScoreById(userid);
    }

    /**
     * 根据userid更改用户积分（计算在java层做）
     * */
    public int updateUserScoreById(int score, int userid){
        return uid.updateUserScoreById(score, userid);
    }

	/**
	 * 查询用户回答过的某一问题的答案列表
	 * */
	public List<Integer> queryAswerAnsList(int userid, int queid){
		return uid.queryAswerAnsList(userid, queid);
	}

	/**
	 * 查看他人主页时，根据用户名查询所有信息
	 * */
	public UserBean queryUserByUsername(String username){
		UserBean ub = uid.queryUserByUsername(username);
		return ub;
	}
}
