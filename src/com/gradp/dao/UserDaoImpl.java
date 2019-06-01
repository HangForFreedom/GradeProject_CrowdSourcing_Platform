package com.gradp.dao;

import java.util.ArrayList;
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
     * session为空时，根据用户名查询用户所有的信息
     * */
	public UserBean queryAll(UserBean ubean){
	    String sql = "SELECT * FROM user WHERE userid=?";
		Object []obj = {ubean.getUserid()};

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
		String sql = "insert into user value(null,?,?,?,'img/image.png',100)";
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

	/**
	 * 查询用户的问题数量
	 * */
	public int queryQuestionById(int userid){
		String sql = "SELECT * FROM question WHERE userid=?";
		Object[] obj = {userid};
		List<Map<String, String>> lists = db.query(sql, obj);
		if (lists.size() == 0){
			return 0;
		}
		return lists.size();
	}

	/**
	 * 查询用户回答数量
	 * */
	public int queryAnswerById(int userid){
		String sql = "SELECT * FROM answer WHERE userid=?";
		Object[] obj = {userid};
		List<Map<String, String>> lists = db.query(sql, obj);
		if (lists.size() == 0){
			return 0;
		}
		return lists.size();
	}

	public List<Integer> queryAgreedAnsList(int userid)
	{
		List<Integer> result = new ArrayList<>();
		String sql = "SELECT ansid FROM agree WHERE userid=?";
		Object[] obj = {userid};
		List<Map<String, String>> lists = db.query(sql, obj);
		if (lists == null || lists.size() == 0){
			return result;
		}
		for (Map<String, String> list : lists) {

			for (String value : list.values()) {
				result.add(Integer.parseInt(value));
			}
		}
		return result;
	}

	/**
	 * 查询用户踩过的ansid列表
	 * */
	public List<Integer> queryDisagreedAnsList(int userid){
		List<Integer> result = new ArrayList<>();
		String sql = "SELECT ansid FROM disagree WHERE userid=?";
		Object[] obj = {userid};
		List<Map<String, String>> lists = db.query(sql, obj);
		if (lists == null || lists.size() == 0){
			return result;
		}
		for (Map<String, String> list : lists) {

			for (String value : list.values()) {
				result.add(Integer.parseInt(value));
			}
		}
		return result;
	}

	/**
	 * 查询用户回答过的某一问题的答案列表
	 * */
	public List<Integer> queryAswerAnsList(int userid, int queid){
		List<Integer> result = new ArrayList<>();
		String sql = "SELECT ansid FROM answer WHERE userid=? and queid=?";
		Object[] obj = {userid, queid};
		List<Map<String, String>> lists = db.query(sql, obj);
		if (lists == null || lists.size() == 0){
			return result;
		}
		for (Map<String, String> list : lists){
			for (String value : list.values()){
				result.add(Integer.parseInt(value));
			}
		}
		return result;
	}

	/**
     * 根据userid查询用户积分
     * */
	public int queryUserScoreById(int userid){
	    String sql = "SELECT score FROM user WHERE userid=?";
	    Object[] obj = {userid};
	    List<Map<String, String>> lists = db.query(sql, obj);
	    if (lists.size() == 0){
	        return 0;
        }
	    return Integer.parseInt(lists.get(0).get("score"));
    }

    /**
     * 根据userid更改用户积分（计算在java层做）
     * */
    public int updateUserScoreById(int score, int userid){
        String sql = "UPDATE user SET score=? WHERE userid=?";
        Object[] obj = {score, userid};
        return db.update(sql, obj);
    }

    /**
	 * 查看他人主页时，根据用户名查询所有信息
	 * */
    public UserBean queryUserByUsername(String username){
    	String sql = "SELECT * FROM user WHERE username=?";
    	Object [] obj =  {username};
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
}
